package com.example.helloworldapplication;

public class cateringDatabase {

    String order_id,catering_type,menu_list,price_per_plate,guest_count,event_date,req_days,event_address,order_status,payment_status,uid;


    public cateringDatabase() {
    }


    public cateringDatabase(String order_id, String catering_type, String menu_list, String price_per_plate, String guest_count, String event_date, String req_days, String event_address, String order_status, String payment_status, String uid) {
        this.order_id = order_id;
        this.catering_type = catering_type;
        this.menu_list = menu_list;
        this.price_per_plate = price_per_plate;
        this.guest_count = guest_count;
        this.event_date = event_date;
        this.req_days = req_days;
        this.event_address = event_address;
        this.order_status = order_status;
        this.payment_status = payment_status;
        this.uid = uid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCatering_type() {
        return catering_type;
    }

    public String getMenu_list() {
        return menu_list;
    }

    public String getPrice_per_plate() {
        return price_per_plate;
    }

    public String getGuest_count() {
        return guest_count;
    }

    public String getEvent_date() {
        return event_date;
    }

    public String getReq_days() {
        return req_days;
    }

    public String getEvent_address() {
        return event_address;
    }

    public String getOrder_status() {
        return order_status;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public String getUid() {
        return uid;
    }
}
