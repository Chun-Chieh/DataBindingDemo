package com.chunchiehliang.networktest.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.chunchiehliang.networktest.BR;

/**
 * @author Chun-Chieh Liang on 7/17/18.
 */
public class User extends BaseObservable{
    private String firstName;
    private String lastName;
    private String email;

    public User() {

    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }



    @Bindable
    public String getFirstName() {
        return this.firstName;
    }

    @Bindable
    public String getLastName() {
        return this.lastName;
    }

    @Bindable
    public String getEmail() {
        return email;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }


}
