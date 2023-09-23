package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int noOfHours; // by client

    @ManyToOne
    @JoinColumn
    Spot spot; // by logic get the id and assign

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    Payment payment;

    @ManyToOne
    @JoinColumn
    User user;

    public Reservation() {
    }

    public Reservation(int id, int noOfHours, Spot spot, Payment payment, User user) {
        this.id = id;
        this.noOfHours = noOfHours;
        this.spot = spot;
        this.payment = payment;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoOfHours() {
        return noOfHours;
    }

    public void setNoOfHours(int noOfHours) {
        this.noOfHours = noOfHours;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
