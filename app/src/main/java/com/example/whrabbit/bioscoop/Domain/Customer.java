package com.example.whrabbit.bioscoop.Domain;

/**
 * Created by Mika Krooswijk on 3-4-2017.
 */

public class Customer {

    private String firstName, lastName, city, street, gender, postalCode;
    private int age, customerID;

    public Customer(String firstName, String lastName, String city, String street, String gender, int age, int customerID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.gender = gender;
        this.age = age;
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
