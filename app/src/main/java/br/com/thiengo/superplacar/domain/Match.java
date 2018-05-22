package br.com.thiengo.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;


public class Match implements Parcelable {
    public static final String MATCHES_KEY = "matches_key";

    private Team team1;
    private Team team2;
    private String status;
    private String start;

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
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

    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.team1, flags);
        dest.writeParcelable(this.team2, flags);
        dest.writeString(this.status);
        dest.writeString(this.start);
    }

    public Match() {
    }

    private Match(Parcel in) {
        this.team1 = in.readParcelable(Team.class.getClassLoader());
        this.team2 = in.readParcelable(Team.class.getClassLoader());
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