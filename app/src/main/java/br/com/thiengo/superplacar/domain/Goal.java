package br.com.thiengo.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;


public class Goal implements Parcelable {
    public static final Parcelable.Creator<Goal> CREATOR = new Parcelable.Creator<Goal>() {
        @Override
        public Goal createFromParcel(Parcel source) {
            return new Goal(source);
        }

        @Override
        public Goal[] newArray(int size) {
            return new Goal[size];
        }
    };
    private String name;
    private String team;

    public Goal() {
    }

    private Goal(Parcel in) {
        this.name = in.readString();
        this.team = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.team);
    }
}