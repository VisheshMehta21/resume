package com.example.myapplication;

public class personaldetailspojo {

    public String selected_state,selected_city,Name,Address,Mobile_No;
    public personaldetailspojo(String selected_city, String selected_state,String Name,String Address,String Mobile_No)
    {
        this.selected_city=selected_city;
        this.selected_state=selected_state;
        this.Name=Name;
        this.Address=Address;
        this.Mobile_No=Mobile_No;
    }
}
