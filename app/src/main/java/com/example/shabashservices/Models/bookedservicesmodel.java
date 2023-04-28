package com.example.shabashservices.Models;

public class bookedservicesmodel {
    String booked_service;
    String date;
    int bookedimage;

    public bookedservicesmodel(String booked_service, String date,int bookedimage) {
        this.booked_service = booked_service;
        this.date = date;
        this.bookedimage = bookedimage;
    }

    public int getBookedimage() {
        return bookedimage;
    }

    public void setBookedimage(int bookedimage) {
        this.bookedimage = bookedimage;
    }

    public String getBooked_service() {
        return booked_service;
    }

    public void setBooked_service(String booked_service) {
        this.booked_service = booked_service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
