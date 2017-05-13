package entity;

import javax.persistence.*;

/**
 * Created by Valeria on 01.05.2017.
 */
@Entity
@Table(name = "coffee")
@NamedQueries({
        @NamedQuery(name= "get all", query="SELECT e FROM CoffeeUnit e"),
        @NamedQuery(name = "get by id", query = "select e from CoffeeUnit e where e.id=:id")
})
public class CoffeeUnit {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String grade;
    private int price;

    public CoffeeUnit() {
    }

    public CoffeeUnit(String grade, int prise) {
        this.grade = grade;
        this.price = prise;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int prise) {
        this.price = prise;
    }
}
