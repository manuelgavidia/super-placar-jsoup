package br.com.thiengo.superplacar.domain;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;


public class Team implements Parcelable {
    final private String name;
    final private String imageUrl;
    private int goals;
    final private List<Goal> goalsList;


    public Team(String name, String url){
        this.name = name;
        this.imageUrl = url;
        goalsList = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public List<Goal> getGoalsList() {
        return goalsList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeInt(this.goals);
        dest.writeTypedList(this.goalsList);
    }

    private Team(Parcel in) {
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.goals = in.readInt();
        this.goalsList = in.createTypedArrayList(Goal.CREATOR);
    }

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
}