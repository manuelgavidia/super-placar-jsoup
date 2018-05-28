package br.com.thiengo.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;


public class Match implements Parcelable {
    public static final String MATCHES_KEY = "matches_key";

    final private Team home;
    final private Team away;
    private String status;
    private String start;

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) { this.start = start; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.home, flags);
        dest.writeParcelable(this.away, flags);
        dest.writeString(this.status);
        dest.writeString(this.start);
    }

    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
    }

    private Match(Parcel in) {
        this.home = in.readParcelable(Team.class.getClassLoader());
        this.away = in.readParcelable(Team.class.getClassLoader());
        this.status = in.readString();
        this.start = in.readString();
    }

    public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel source) {
            return new Match(source);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };
}