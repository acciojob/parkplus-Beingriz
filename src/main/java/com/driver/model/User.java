package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // by db
    private String name;
    private String phoneNo;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Reservation> reservations = new ArrayList<>();

    public User() {
    }

    public User(int id, String name, String phoneNo, String password, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.password = password;
        this.reservations = reservations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
