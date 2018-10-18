package ssit.java0.springMVC.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class DeleteObject {
    @NotEmpty
    private String prod;

    public String getProductId() {
    return prod;
    }

    public void setProductId(int productid) {
    //    this.prod = productid;
    }
}
