package entity;

import jpa.plagiat.annotations.*;

@Table(name = "car")
public class Car {

    @Id
    @AutoIncrement
    Integer id;

    @NotNull
    String car_name, color;

    @Column(name = "speed")
    String max_speed;
}
