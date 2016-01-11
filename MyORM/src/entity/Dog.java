package entity;

import jpa.plagiat.annotations.*;

@Table(name = "dog")
public class Dog {

    @Id
    @AutoIncrement
    public Integer id;

    @Column(name = "name")
    @NotNull
    String name;
    Integer age;
}
