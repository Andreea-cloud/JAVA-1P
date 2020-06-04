package Model;

import lombok.*;

import java.time.LocalTime;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Flights {
    int id;
    String sursa;
    String destinatie;
    LocalTime oraPlecare;
    LocalTime oraSosire;
    String zile;
    int pret;

}
