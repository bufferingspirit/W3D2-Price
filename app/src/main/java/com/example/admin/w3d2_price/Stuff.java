package com.example.admin.w3d2_price;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 8/16/2017.
 */

public class Stuff implements Parcelable {

    String one;
    String two;
    String three;
    Bitmap picture = null;


    public Stuff(Context context){
        this.one = getRandomStrings();
        this.two = getRandomStrings();
        this.three = getRandomStrings();
        try {

            // get input stream
            InputStream ims = context.getAssets().open("mypicture.jpg");
            // load image as Drawable
            this.picture = BitmapFactory.decodeStream(ims);

            Log.d("Stuff", "Makeing Stuff");

            if(this.picture != null){
                Log.d("Stuff", "Picture created");
            }else{
                Log.d("Stuff", "fail");
            }
        }
        catch(IOException ex) {
            return;
        }
    }

    public Stuff(String one, String two, String three, Bitmap picture) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.picture = picture;
    }

    protected Stuff(Parcel in) {
        one = in.readString();
        two = in.readString();
        three = in.readString();
        picture = in.readParcelable(Bitmap.class.getClassLoader());
    }


    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getRandomStrings(){
        String salt = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*(){}:;,.'";
        Random random = new Random();
        String out = "";
        int count = random.nextInt(20);
        for(int i = 0; i < count; i++){
            out = (out + (salt.charAt(random.nextInt(salt.length()-1))));
        }
        return out;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(one);
        parcel.writeString(two);
        parcel.writeString(three);
        picture.writeToParcel(parcel, 0);

    }

    public static final Creator<Stuff> CREATOR = new Creator<Stuff>() {
        @Override
        public Stuff createFromParcel(Parcel in) {
            return new Stuff(in);
        }

        @Override
        public Stuff[] newArray(int size) {
            return new Stuff[size];
        }
    };



}
