package server.entities;

import java.sql.Date;

public class Abonne {
    public final static long MILLISECONDS_IN_A_YEAR = 1000 * 60 * 60 * 24 * 365; // do not take into account leap years
    int id;
    String name;
    Date birthdate;

    public Abonne(int id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    };

    public long getAge() {
        return (birthdate.getTime() - new java.util.Date().getTime()) / MILLISECONDS_IN_A_YEAR;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
