package entity;

import java.sql.Date;

/**
 * Created by 20_ok on 08.05.2017.
 */
public class Flight {

    private int id;
    private Plane plane;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
