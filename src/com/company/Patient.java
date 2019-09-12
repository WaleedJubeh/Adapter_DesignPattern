package com.company;

public class Patient {
    private String firstName;
    private String lastName;



    public  Patient(String firstName,String lastName)
    {
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
