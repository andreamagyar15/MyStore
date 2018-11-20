package ssit.java0.springMVC.dto;

import ssit.java0.springMVC.domain.ProductType;

import java.util.Date;

public class UpdateProductRequest {
    private String title;
    private  String description;
    private double price;
    private int amount;
    private  int size;
    private Date arrival;
    private ProductType prodType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public ProductType getProdType() {
        return prodType;
    }

    public void setProdType(ProductType prodType) {
        this.prodType = prodType;
    }
}
