package com.example.helloworldapplication;

public class AppointPhotographerDatabase {

    String phtographer_type,photographer_code,photographer_price,photographer_req,photographer_date,photographer_add,status,photographer_package,order_id,payment_status,uid;


    public AppointPhotographerDatabase() {
    }

    public AppointPhotographerDatabase(String phtographer_type, String photographer_code, String photographer_price, String photographer_req, String photographer_date, String photographer_add, String status, String photographer_package, String order_id, String payment_status, String uid) {

        this.phtographer_type = phtographer_type;
        this.photographer_code = photographer_code;
        this.photographer_price = photographer_price;
        this.photographer_req = photographer_req;
        this.photographer_date = photographer_date;
        this.photographer_add = photographer_add;
        this.status = status;
        this.photographer_package = photographer_package;
        this.order_id = order_id;
        this.payment_status = payment_status;
        this.uid = uid;
    }

    public String getPhtographer_type() {
        return phtographer_type;
    }

    public String getPhotographer_code() {
        return photographer_code;
    }

    public String getPhotographer_price() {
        return photographer_price;
    }

    public String getPhotographer_req() {
        return photographer_req;
    }

    public String getPhotographer_date() {
        return photographer_date;
    }

    public String getPhotographer_add() {
        return photographer_add;
    }

    public String getStatus() {
        return status;
    }

    public String getPhotographer_package() {
        return photographer_package;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public String getUid() {
        return uid;
    }
}
