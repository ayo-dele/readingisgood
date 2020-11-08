package com.ayodele.readingisgood.dto;

import java.util.Set;

public class OrderResponse {

    private int orderId;
    private String customerName;
    private Set<Books> books;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Set<Books> getBooks() {
        return books;
    }

    public void setBooks(Set<Books> books) {
        this.books = books;
    }

    public static class Books {
        public String bookname;
        public int numberOfCopies;
    }
}
