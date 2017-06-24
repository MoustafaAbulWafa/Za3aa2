package com.example.abuelwafa_.za3a2.AidClases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Abu El Wafa ^_^ on 15/06/2017.
 */

public class Model implements Parcelable{
    public Model(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    protected Model(Parcel in) {
        lng = in.readDouble();
        lat = in.readDouble();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    double lng,lat;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(lng);
        parcel.writeDouble(lat);
    }
}
