package com.example.helloworldapplication;

public class User {
    public String name,email,phone,add,dist,uid;
    public User() {
    }


    public String getName() {
        return name;
    }
    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdd() {
        return add;
    }

    public String getDist() {
        return dist;
    }

    public User(String name, String email, String phone, String add, String dist,String uid){
        this.name=name;
        this.uid=uid;
        this.email=email;
        this.phone=phone;
        this.add=add;
        this.dist=dist;
    }

}
