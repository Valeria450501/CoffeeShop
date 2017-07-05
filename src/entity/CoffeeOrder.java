package entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Valeria on 30.04.2017.
 */
@Entity
@Table
@NamedQueries({
        @NamedQuery(name="get all", query="SELECT e FROM CoffeeOrder e"),
        @NamedQuery(name = "get by id", query = "select e from CoffeeOrder e where e.id=:id")
})
public class CoffeeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double cost;
    private boolean delivery;
    private Date fromDate;
    private Date toDate;
    private String grade;
    private int quantity;

    public CoffeeOrder(String grade, boolean delivery, Date fromDate, Date toDate, int quantity, double cost) {
        this.grade = grade;
        this.delivery = delivery;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.quantity = quantity;
        this.cost = cost;
    }

    public CoffeeOrder() {
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  grade + " " + quantity + "(g) - " + cost + "$";
    }

    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String time = "from "+ dateFormat.format(this.toDate).toString();
        dateFormat = new SimpleDateFormat("HH:mm MM/dd/yyyy");
        time += " to "+ dateFormat.format(this.toDate).toString();
        return time;
    }
}
