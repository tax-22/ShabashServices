package com.example.shabashservices.Models;

public class serviceprovidermodel {
    int img;
    String serviceprovidername;
    float rating;

    public serviceprovidermodel(int img, String serviceprovidername, float rating) {
        this.img = img;
        this.serviceprovidername = serviceprovidername;
        this.rating = rating;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getServiceprovidername() {
        return serviceprovidername;
    }

    public void setServiceprovidername(String serviceprovidername) {
        this.serviceprovidername = serviceprovidername;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
