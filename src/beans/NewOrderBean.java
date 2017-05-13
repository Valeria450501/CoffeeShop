package beans;

import entity.CoffeeOrder;
import entity.CoffeeOrderManager;
import entity.CoffeeUnit;
import entity.CoffeeUnitManager;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.javafx.logging.PulseLogger.addMessage;

/**
 * Created by Valeria on 30.04.2017.
 */
@ManagedBean(name="newOrderBean")
@SessionScoped
public class NewOrderBean {
    @NotNull(message = "Please, choose grade")
    private String console;
    private boolean delivery;
    //@Pattern(regexp = "[0-9]*\\\\.?[0-9]", message = "Wrong value")
    @Min(value = 100, message = "Please, order more 100")
    private String quantity;
    private int price;
    private double cost = 0;
    @NotNull(message = "Please, choose day")
    private Date dayDate;
    @NotNull(message = "Please, choose from date")
    private Date fromDate;
    @NotNull(message = "Please, choose to date")
    private Date toDate;
    private String currentDate;
    private List<SelectItem> selectItemsCoffeeGrade;

    @PostConstruct
    public void init() {
        this.cost = 0;
        this.price = 0;
        this.quantity = "100";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Date temp = new Date();
        temp.setTime(temp.getTime() + 24*60*60*1000);
        this.currentDate = dateFormat.format(temp);
    }

    public Date getDay() {
        return dayDate;
    }

    public void setDay(Date day) {
        this.dayDate = day;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public List<SelectItem> getSelectItemsCoffeeGrade() {
        this.selectItemsCoffeeGrade = new ArrayList<SelectItem>();
        CoffeeUnitManager coffeeUnitManager = new CoffeeUnitManager();

        this.selectItemsCoffeeGrade.clear();

        for (CoffeeUnit coffee : coffeeUnitManager.getCoffeeUnitList()) {
            SelectItem selectItem = new SelectItem(coffee.getId(), coffee.getGrade());
            this.selectItemsCoffeeGrade.add(selectItem);
        }

        return selectItemsCoffeeGrade;
    }



    public void setSelectItemsCoffeeGrade(List<SelectItem> selectItemsCoffeeGrade) {
        this.selectItemsCoffeeGrade = selectItemsCoffeeGrade;
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

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void buttonAction(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        if(this.console != null) {
            if(this.dayDate != null) {
            } else {
                context.addMessage(null, new FacesMessage("Please, choose day"));
                return;
            }

            this.setData();
            CoffeeUnitManager coffeeUnitManager = new CoffeeUnitManager();
            CoffeeOrder coffeeOrder = new CoffeeOrder(coffeeUnitManager.getGrade(Integer.parseInt(this.console)), this.delivery, this.fromDate, this.toDate, Integer.parseInt(this.quantity), this.cost);

            CoffeeOrderManager coffeeOrderManager = new CoffeeOrderManager();
            coffeeOrderManager.createNewOrder(coffeeOrder);
            context.addMessage(null, new FacesMessage("Successful"));
        }
    }

    public void calculatePrice() {
        int deliveryCost = 0;
        if(this.delivery && !"".equals(this.console))
            deliveryCost = 1;

        this.cost = deliveryCost + Integer.parseInt(this.quantity) * this.price / 1000;
    }

    public void getPrice() {
        if(!"".equals(this.console)) {
            CoffeeUnitManager coffeeUnitManager = new CoffeeUnitManager();
            this.price = coffeeUnitManager.getPrice(Integer.parseInt(this.console));
        }
        this.calculatePrice();
    }

    public void setData() {
        this.fromDate.setYear(dayDate.getYear());
        this.fromDate.setMonth(dayDate.getMonth());
        this.fromDate.setDate(dayDate.getDate());


        this.toDate.setYear(dayDate.getYear());
        this.toDate.setMonth(dayDate.getMonth());
        this.toDate.setDate(dayDate.getDate());
    }

    public void validateData(FacesContext context, UIComponent inputComponent, Object value) throws ValidatorException {
        if(value == null || this.fromDate==null) {
            return;
        }

        Date toDate = (Date) value;

        if(!toDate.after(this.fromDate)) {
            FacesMessage msg = new FacesMessage("Wrong time period");
            throw new ValidatorException(msg);
        }
    }
}
