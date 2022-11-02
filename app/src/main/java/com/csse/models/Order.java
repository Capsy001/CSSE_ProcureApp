package com.csse.models;

public class Order {

    private String id;
    private String item;
    private String status;
    private String delivered;
    private String address; //delivery address
    private String quantity;
    private String uid;
    private String expectedDate;

    public Order(String id, String item, String status, String delivered, String address, String quantity, String uid, String expectedDate) {
        this.id = id;
        this.item = item;
        this.status = status;
        this.delivered = delivered;
        this.address = address;
        this.quantity = quantity;
        this.uid = uid;
        this.expectedDate = expectedDate;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
