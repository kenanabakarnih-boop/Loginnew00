package com.example.loginnew;

public class RideModel {

    private String rideId;
    private String userId;
    private String startLocation;
    private String endLocation;
    private String date;
    private String time;
    private double price;
    private String status;

    public RideModel() {
        // Required empty constructor
    }

    public RideModel(String rideId, String userId, String startLocation, String endLocation,
                     String date, String time, double price, String status) {
        this.rideId = rideId;
        this.userId = userId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.date = date;
        this.time = time;
        this.price = price;
        this.status = status;
    }

    public String getRideId() { return rideId; }
    public void setRideId(String rideId) { this.rideId = rideId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getStartLocation() { return startLocation; }
    public void setStartLocation(String startLocation) { this.startLocation = startLocation; }

    public String getEndLocation() { return endLocation; }
    public void setEndLocation(String endLocation) { this.endLocation = endLocation; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

