package beans;

import entity.CoffeeOrder;
import entity.CoffeeOrderManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Valeria on 05.07.2017.
 */
@ManagedBean(name="CoffeeOrdersViewBean")
@ViewScoped
public class CoffeeOrdersViewBean {
    private List<CoffeeOrder> orders;

    @PostConstruct
    public void init() {
        CoffeeOrderManager coffeeOrderManager = new CoffeeOrderManager();
        this.orders = coffeeOrderManager.getCoffeeOrderList();
    }
    public List<CoffeeOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CoffeeOrder> orders) {
        this.orders = orders;
    }
}
