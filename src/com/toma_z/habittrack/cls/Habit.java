package com.toma_z.habittrack.cls;

import java.util.ArrayList;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Habit implements Parcelable{

	protected String habitName = "";
	protected boolean isFixed = true;
	protected String description = "";
	protected int cumulative = 0;// 目前累積次數, default = 0
	protected int goalTimes  = 1;//  necessary
	protected ArrayList<Date> completeDates ; 


	
	protected Habit(String habitName,boolean isFixed,int goalTimes) {
		
	
	
	}
	
	
	public Habit(Parcel dest){
		this.habitName = dest.readString();
		this.isFixed = dest.readByte() != 0;
		this.description = dest.readString();
		this.cumulative = dest.readInt();
		this.goalTimes = dest.readInt();
		
		dest.readList(completeDates, null);




	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(habitName);
		dest.writeByte((byte)(isFixed? 1:0));
		dest.writeString(description);
		dest.writeInt(cumulative);
		dest.writeInt(goalTimes);
		dest.writeList(completeDates);
	}



	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getHabitName() {
		return habitName;
	}


	public void setHabitName(String habitName) {
		this.habitName = habitName;
	}


	public boolean isFixed() {
		return isFixed;
	}


	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getCumulative() {
		return cumulative;
	}


	public void setCumulative(int cumulative) {
		this.cumulative = cumulative;
	}


	public int getGoalTimes() {
		return goalTimes;
	}


	public void setGoalTimes(int goalTimes) {
		this.goalTimes = goalTimes;
	}


	public ArrayList<Date> getCompleteDates() {
		return completeDates;
	}


	public void setCompleteDates(ArrayList<Date> completeDates) {
		this.completeDates = completeDates;
	}


}
