package com.ayodele.readingisgood.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "books")
public class Books extends DateAudit {

    @NotEmpty
    @Column(name = "book_name")
    private String bookName;
    @NotNull
    @Column(name = "number_in_stock")
    private Integer numberInStock;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    private List<Orders> orders;

    public Integer getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(Integer numberInStock) {
        this.numberInStock = numberInStock;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> order) {
        this.orders = order;
    }
}
