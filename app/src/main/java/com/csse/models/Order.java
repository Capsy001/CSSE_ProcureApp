package com.csse.models;

public class Order {

    private String id;
    private String item;
    private String status;
    private Boolean delivered;
    private String address; //delivery address

    public Order(String id, String item, String status, Boolean delivered, String address) {
        this.id = id;
        this.item = item;
        this.status = status;
        this.delivered = delivered;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
