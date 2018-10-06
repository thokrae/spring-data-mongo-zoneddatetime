package com.github.thokrae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith", ZonedDateTime.now()));
        repository.save(new Customer("Bob", "Smith", ZonedDateTime.now()));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}