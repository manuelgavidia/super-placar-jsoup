package br.com.thiengo.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Team implements Parcelable {
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
    final private String name;
    final private String imageUrl;

    public Team(String name, String url) {
        this.name = name;
        this.imageUrl = url;
        if (this.imageUrl.isEmpty() || this.name.isEmpty()) {
            Log.e(this.getClass().getName(), "Empty img url for " + this.name + "in" +
            name + url);
        }
    }

    private Team(Parcel in) {
        this.name = in.readString();
        this.imageUrl = in.readString();
        if (this.imageUrl.isEmpty() || this.name.isEmpty()) {
            Log.e(this.getClass().getName(), "Empty img url for " + this.name);
        }
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
    }
}