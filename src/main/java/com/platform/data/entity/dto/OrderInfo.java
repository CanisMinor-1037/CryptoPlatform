package com.platform.data.entity.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
class Order{
    private long lastWeek;
    private long thisYear;
    private long total;
    private long department;
    public Order(){
    }
    public Order(long lastWeek,long thisYear,long total,long department){
        this.lastWeek = lastWeek;
        this.thisYear = thisYear;
        this.total = total;
        this.department = department;
    }
}
@Data
public class OrderInfo {
    private Order order;
    private ArrayList<Long> barData;
    public OrderInfo(){
        this.order = new Order();
    }
    public void setLastWeek(long lastWeek) {
        this.order.setLastWeek(lastWeek);
    }
    public void setThisYear(long thisYear) {
        this.order.setThisYear(thisYear);
    }
    public void setTotal(long total) {
        this.order.setTotal(total);
    }
    public void setDepartment(long department) {
        this.order.setDepartment(department);
    }
}
