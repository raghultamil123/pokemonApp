package com.example.pokemonapp.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Result implements Serializable, Parcelable {
    private String name;
    private String url;

    public Result(){

    }
    public Result(Parcel in){
        name = in.readString();
        url = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);

    }
    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>()
    {
        public Result createFromParcel(Parcel in)
        {
            return new Result(in);
        }
        public Result[] newArray(int size)
        {
            return new Result[size];
        }
    };
}
