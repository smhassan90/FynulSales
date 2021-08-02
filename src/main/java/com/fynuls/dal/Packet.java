package com.fynuls.dal;


import com.fynuls.entity.sale.Order;
import com.fynuls.entity.sale.ProductOrder;

import java.util.List;

public class Packet {
    Order order;
    List<ProductOrder> productOrders;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }
}
