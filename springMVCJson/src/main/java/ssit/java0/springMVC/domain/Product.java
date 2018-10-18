package ssit.java0.springMVC.domain;

import java.util.Date;

public class Product {
    private int id;
    private String title;
    private  String description;
    private double price;
    private int amount;
    private  int size;
    private String imgUrl;
    private Date arrival;

    public Product(){

    }
    public Product(int id, String title, String description, double price, int amount, int size, String imgUrl, Date arrival) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.size = size;
        this.imgUrl = imgUrl;
        this.arrival = arrival;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", size=" + size +
                ", imgUrl='" + imgUrl + '\'' +
                ", arrival='" + arrival + '\'' +
                '}';
    }
}
