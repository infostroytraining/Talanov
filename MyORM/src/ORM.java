import jpa.plagiat.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

public class ORM {

    private static ORM ormInstance;
    private final String user;
    private final String password;
    private final String url;

    private ORM(String driver, String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ORM getInstance() throws IOException {
        Properties properties = new Properties();
        if (ormInstance == null) {
            try (FileInputStream reader = new FileInputStream("postgre.properties")) {
                properties.load(reader);
                String driver = properties.getProperty("postgre.driver");
                String url = properties.getProperty("postgre.url");
                String user = properties.getProperty("postgre.user");
                String password = properties.getProperty("postgre.password");
                ormInstance = new ORM(driver, url, user, password);
                return ormInstance;
            }
        } else {
            return ormInstance;
        }
    }

    public static void createTable(Class<?> entityClass) throws IOException, SQLException {
        ORM orm = ORM.getInstance();
        String query = orm.createQuery(entityClass);
        try(Connection connection = orm.getConnection()){
            try(Statement statement = connection.createStatement()) {
                statement.execute(query);
            }
        }
    }

    private String createQuery(Class<?> entityClass) {
        StringBuilder query = new StringBuilder();
        Arrays.asList(entityClass.getAnnotations()).forEach(annotation -> {
            if (annotation.annotationType().equals(Table.class)) {
                Table table = entityClass.getAnnotation(Table.class);
                query.append("CREATE TABLE public.");
                query.append(table.name());
                query.append("(");
                query.append("\n");
                Arrays.asList(entityClass.getDeclaredFields()).forEach(field -> {
                    if (field.getAnnotation(Id.class) != null) {
                        AutoIncrement autoIncrement = field.getAnnotation(AutoIncrement.class);
                        Column column = field.getAnnotation(Column.class);
                        if (column != null) {
                            query.append(column.name());
                        } else {
                            query.append(field.getName());
                        }
                        if (autoIncrement != null) {
                            query.append(" SERIAL");
                        } else {
                            if (field.getType().equals(Integer.class)) {
                                query.append(" int4");
                            } else if (field.getType().equals(String.class)) {
                                query.append(" varchar(255)");
                            }
                        }
                        query.append(",");
                        query.append("\n");
                    } else {
                        Column column = field.getAnnotation(Column.class);
                        if (column != null) {
                            query.append(column.name());
                        } else {
                            query.append(field.getName());
                        }

                        if (field.getType().equals(Integer.class)) {
                            query.append(" int4");
                        } else if (field.getType().equals(String.class)) {
                            query.append(" varchar(255)");
                        }

                        if (field.getAnnotation(NotNull.class) != null) {
                            query.append(" NOT NULL,");
                        }
                        query.append("\n");
                    }
                });
                query.append(");");
            }
        });
        return query.toString();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
