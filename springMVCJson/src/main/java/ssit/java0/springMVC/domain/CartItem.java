package ssit.java0.springMVC.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class CartItem {
    @NotNull
    private int productid;
    @NotNull
    private int quantity;
    @NotEmpty
    private ProductType productType;

    public int getProductid() {

        return productid;
    }

    public void setProductid(int productid) {

        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
