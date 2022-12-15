package com.example.sqltask.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Directory implements Parcelable {
    int ID;
    String Title, Number, Mail;

    public Directory (int ID, String title, String number, String mail){
        this.ID = ID;
        Title = title;
        Number = number;
        Mail = mail;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    protected Directory(Parcel in){

    }

    public static final Creator<Directory> CREATOR = new Creator<Directory>() {
        @Override
        public Directory createFromParcel(Parcel in) {
            return new Directory(in);
        }

        @Override
        public Directory[] newArray(int size) {
            return new Directory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
