package com.example.loginnew;

public class RideModel {
    public String rideId;
    public String startLocation;
    public String destination;
    public String price;

    public RideModel() {}

    public RideModel(String rideId, String startLocation, String destination, String price) {
        this.rideId = rideId;
        this.startLocation = startLocation;
        this.destination = destination;
        this.price = price;
    }
}

