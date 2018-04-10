package com.example.shaheed.takeaclimbchallenge;

/**
 * Created by shaheed on 4/7/18.
 */

public class Profile {
    String name, address;
    int age;

    public Profile (String name, String address, int age){
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
