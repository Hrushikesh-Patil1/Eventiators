package com.example.helloworldapplication;

public class CakeOrderDb {

    String cake_code,cake_name,cake_msg_print,cake_quantity,cake_date,cake_price,cake_add,cake_status,order_id,uid,payment_status;


    public CakeOrderDb() {

    }

    public CakeOrderDb(String cake_code, String cake_name, String cake_msg_print, String cake_quantity, String cake_date, String cake_price, String cake_add, String cake_status, String order_id, String uid, String payment_status) {
        this.cake_code = cake_code;
        this.cake_name = cake_name;
        this.cake_msg_print = cake_msg_print;
        this.cake_quantity = cake_quantity;
        this.cake_date = cake_date;
        this.cake_price = cake_price;
        this.cake_add = cake_add;
        this.cake_status = cake_status;
        this.order_id = order_id;
        this.uid = uid;
        this.payment_status = payment_status;
    }


    public String getCake_code() {
        return cake_code;
    }

    public String getCake_name() {
        return cake_name;
    }

    public String getCake_msg_print() {
        return cake_msg_print;
    }

    public String getCake_quantity() {
        return cake_quantity;
    }

    public String getCake_date() {
        return cake_date;
    }

    public String getCake_price() {
        return cake_price;
    }

    public String getCake_add() {
        return cake_add;
    }

    public String getCake_status() {
        return cake_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getUid() {
        return uid;
    }

    public String getPayment_status() {
        return payment_status;
    }
}
