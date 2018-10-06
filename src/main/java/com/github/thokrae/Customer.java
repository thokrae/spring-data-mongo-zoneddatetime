package com.github.thokrae;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;
    public ZonedDateTime created;

    public Customer() {
    }

    public Customer(String firstName, String lastName, ZonedDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", created=" + created +
                '}';
    }
}
