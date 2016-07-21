package com.abm.pos.com.abm.pos.dto;

/**
 * Created by asp5045 on 7/11/16.
 */
public class DashboardDto {

    private String nameOfMonth;
    private double total;
    private double cash;
    private double credit;
    private double profit;
    private double tax;
    private double discount;
    private double noOfTrans;
    private double avgBasketSize;

    public String getNameOfMonth() {
        return nameOfMonth;
    }

    public void setNameOfMonth(String nameOfMonth) {
        this.nameOfMonth = nameOfMonth;
    }



    public double getAvgBasketSize() {
        return avgBasketSize;
    }

    public void setAvgBasketSize(double avgBasketSize) {
        this.avgBasketSize = avgBasketSize;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNoOfTrans() {
        return noOfTrans;
    }

    public void setNoOfTrans(double noOfTrans) {
        this.noOfTrans = noOfTrans;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}