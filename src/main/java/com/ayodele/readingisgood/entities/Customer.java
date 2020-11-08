package com.ayodele.readingisgood.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "customer")
public class Customer extends DateAudit {

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty
    @Column(name = "email")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
