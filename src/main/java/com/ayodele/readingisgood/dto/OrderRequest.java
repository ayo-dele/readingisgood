package com.ayodele.readingisgood.dto;

import com.sun.istack.NotNull;

import java.util.Map;

public class OrderRequest {

    @NotNull
    private Integer customerId;

    @NotNull
    private Map<String, Integer> bookIdNumberOfCopies;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getBookIdNumberOfCopies() {
        return bookIdNumberOfCopies;
    }

    public void setBookIdNumberOfCopies(Map<String, Integer> bookIdNumberOfCopies) {
        this.bookIdNumberOfCopies = bookIdNumberOfCopies;
    }
}
