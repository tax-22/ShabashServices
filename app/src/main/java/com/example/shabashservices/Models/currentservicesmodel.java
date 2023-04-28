package com.example.shabashservices.Models;


public class currentservicesmodel {

    int currentimage;
    String currentservice;

    public currentservicesmodel(int currentimage, String currentservice) {
        this.currentimage = currentimage;
        this.currentservice = currentservice;
    }

    public int getCurrentimage() {
        return currentimage;
    }

    public void setCurrentimage(int currentimage) {
        this.currentimage = currentimage;
    }

    public String getCurrentservice() {
        return currentservice;
    }

    public void setCurrentservice(String currentservice) {
        this.currentservice = currentservice;
    }
}
