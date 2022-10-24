package com.example.sqlwithapi;

import android.os.Parcel;
import android.os.Parcelable;

public class Mask implements Parcelable {
    private int ID;
    private String AirlineName;
    private String AirlineWebSite;
    private String Image;

    public  Mask(int ID, String airlineName, String airlineWebSite, String image){
        this.ID = ID;
        AirlineName = airlineName;
        AirlineWebSite = airlineWebSite;
    }
    protected Mask(Parcel in) {
        ID = in.readInt();
        AirlineName = in.readString();
        AirlineWebSite = in.readString();
        Image = in.readString();
    }

    public static final Creator<Mask> CREATOR = new Creator<Mask>() {
        @Override
        public Mask createFromParcel(Parcel in) {
            return new Mask(in);
        }

        @Override
        public Mask[] newArray(int size) {
            return new Mask[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeString(AirlineName);
        parcel.writeString(AirlineWebSite);
        parcel.writeString(Image);
    }
}
