package ssit.java0.springMVC.domain;

public class Information {
    private  String message;
    private int value;

    public Information(String message, int value) {
        this.message = message;
        this.value = value;
    }

    public Information(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
