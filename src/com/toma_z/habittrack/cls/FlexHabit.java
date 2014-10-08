package com.toma_z.habittrack.cls;

import android.os.Parcel;

public class FlexHabit extends Habit {

	protected PeriodUnit periodUnit;
	protected int periodTimes;

	
	public FlexHabit(String habitName,int goalTimes,int periodTimes,PeriodUnit periodUnit){
		super(habitName,false,goalTimes);
		this.setPeriodTimes(periodTimes);
		this.periodUnit = periodUnit;
	}
	
	public FlexHabit(String habitName,int goalTimes,int periodTimes,PeriodUnit periodUnit,String description){
		this(habitName,goalTimes,periodTimes,periodUnit);
		setDescription(description);
	}
	
	public FlexHabit(Parcel dest) {
		super(dest);
		
	}

	
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		super.writeToParcel(dest, flags);
	
	}

	public int getPeriodTimes() {
		return periodTimes;
	}

	public void setPeriodTimes(int periodTimes) {
		this.periodTimes = periodTimes;
	}
	
}
