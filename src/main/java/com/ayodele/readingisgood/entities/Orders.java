package com.ayodele.readingisgood.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders extends DateAudit {

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "order_books",
            joinColumns = {@JoinColumn(name = "books_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private List<Books> books;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}
