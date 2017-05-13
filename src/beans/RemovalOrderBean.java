package beans;

import entity.CoffeeOrder;
import entity.CoffeeOrderManager;
import entity.CoffeeUnit;
import entity.CoffeeUnitManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valeria on 10.05.2017.
 */
@ManagedBean(name="removalOrderBean")
@SessionScoped
public class RemovalOrderBean {
    private String console;
    private String coffeeGrade;
    private String coffeeDelivery;
    private String coffeeToDate;
    private String coffeeFromDate;
    private String coffeePrice;
    private String coffeeQuantity;
    private List<SelectItem> selectItemsCoffeeOrder;

    @PostConstruct
    public void init(){
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public List<SelectItem> getSelectItemsCoffeeOrder() {
        this.selectItemsCoffeeOrder = new ArrayList<SelectItem>();
        CoffeeOrderManager coffeeOrderManager = new CoffeeOrderManager();

        this.selectItemsCoffeeOrder.clear();

        for (CoffeeOrder coffee : coffeeOrderManager.getCoffeeOrderList()) {
            SelectItem selectItem = new SelectItem(coffee.getId(), coffee.toString());
            this.selectItemsCoffeeOrder.add(selectItem);
        }

        return selectItemsCoffeeOrder;
    }

    public void setSelectItemsCoffeeOrder(List<SelectItem> selectItemsCoffeeOrder) {
        this.selectItemsCoffeeOrder = selectItemsCoffeeOrder;
    }

    public void updateField() {
        if(this.console == null)
            return;

        CoffeeOrderManager coffeeOrderManager = new CoffeeOrderManager();
        coffeeOrderManager.foundById(Integer.parseInt(this.console));
        CoffeeOrder temp = coffeeOrderManager.getCoffeeOrder();

        this.coffeeGrade = temp.getGrade();
        this.coffeeDelivery = Boolean.toString(temp.isDelivery());
        this.coffeeToDate = temp.getToDate().toString();
        this.coffeeFromDate = temp.getFromDate().toString();
        this.coffeePrice = Double.toString(temp.getCost());
        this.coffeeQuantity = Double.toString(temp.getQuantity());
    }

    public String getCoffeeGrade() {
        return coffeeGrade;
    }

    public void setCoffeeGrade(String coffeeGrade) {
        this.coffeeGrade = coffeeGrade;
    }

    public String getCoffeeDelivery() {
        return coffeeDelivery;
    }

    public void setCoffeeDelivery(String coffeeDelivery) {
        this.coffeeDelivery = coffeeDelivery;
    }

    public String getCoffeeToDate() {
        return coffeeToDate;
    }

    public void setCoffeeToDate(String coffeeToDate) {
        this.coffeeToDate = coffeeToDate;
    }

    public String getCoffeeFromDate() {
        return coffeeFromDate;
    }

    public void setCoffeeFromDate(String coffeeFromDate) {
        this.coffeeFromDate = coffeeFromDate;
    }

    public String getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(String coffeePrice) {
        this.coffeePrice = coffeePrice;
    }

    public String getCoffeeQuantity() {
        return coffeeQuantity;
    }

    public void setCoffeeQuantity(String coffeeQuantity) {
        this.coffeeQuantity = coffeeQuantity;
    }
}
