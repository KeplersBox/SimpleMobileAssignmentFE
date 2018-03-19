package com.keplersBox.assignment.model;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kibrom on 3/15/18.
 */

public class Email implements Parcelable{

    private String from;

    private String subject;

    private String body;

    private Bitmap senderPhoto;

    public Email (){}

    public Email(Parcel in) {

        this.from = in.readString();

        this.subject = in.readString();

        this.body = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.from);

        dest.writeString(this.subject);

        dest.writeString(this.body);

    }

    public static final Parcelable.Creator<Email> CREATOR = new Parcelable.Creator<Email>() {

        public Email createFromParcel(Parcel in) {
            return new Email(in);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Bitmap getSenderPhoto() {
        return senderPhoto;
    }

    public void setSenderPhoto(Bitmap senderPhoto) {
        this.senderPhoto = senderPhoto;
    }


}
