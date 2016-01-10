import filter.*;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Filter filter = null;
        Calendar calendar = Calendar.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Искать по имени файла? (0/1)");
        if(scanner.nextInt() == 1){
            System.out.println("Введите имя файла: ");
            System.out.print("==> ");
            filter = new NameFilter(null, scanner.next());
        }

        System.out.println("Искать по расширению файла? (0/1)");
        if(scanner.nextInt() == 1){
            System.out.println("Введите расширение файла: ");
            System.out.print("==> ");
            filter = new ExtentionFilter(filter, scanner.next());
        }

        System.out.println("Искать по размеру файла? (0/1)");
        if(scanner.nextInt() == 1){
            System.out.println("Введите размеры файла файла: ");
            System.out.print("from ==> ");
            long from = scanner.nextInt();
            System.out.print("to ==> ");
            long to = scanner.nextInt();
            filter = new SizeRangeFilter(filter, from, to);
        }

        System.out.println("Искать по дате изменения файла? (0/1)");
        if(scanner.nextInt() == 1){
            System.out.println("Введите размеры файла файла: ");

            System.out.println("from: ");
            System.out.print("Year ==> ");
            int year = scanner.nextInt();
            System.out.print("Month ==> ");
            int month = scanner.nextInt() - 1;
            System.out.print("Day ==> ");
            int day = scanner.nextInt();
            calendar.set(year, month, day);
            long from = calendar.getTimeInMillis();

            System.out.println("to: ");
            System.out.print("Year ==> ");
            year = scanner.nextInt();
            System.out.print("Month ==> ");
            month = scanner.nextInt() - 1;
            System.out.print("Day ==> ");
            day = scanner.nextInt();
            calendar.set(year, month, day);
            long to = calendar.getTimeInMillis();

            filter = new DateRangeFilter(filter, from, to);
        }

        File file = new File("C:\\filter");
        List<File> files = Arrays.asList(file.listFiles());

        System.out.println(files);
        List<File> result = new ArrayList<>();
        for(File current : files){
            if(filter.accept(current)){
                result.add(current);
            }
        }
        System.out.println(result);
    }
}