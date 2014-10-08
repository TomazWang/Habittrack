package com.toma_z.habittrack.cls;

import android.os.Parcel;

public class FixedHabit extends Habit {

	protected boolean[] cycleToggle;

	public FixedHabit(String habitName, int goalTimes, boolean[] toggles) {
		super(habitName, true, goalTimes);
	}

	public FixedHabit(String habitName, int goalTimes, boolean[] toggles,
			String description) {
		this(habitName, goalTimes, toggles);
		setDescription(description);

	}

	public FixedHabit(Parcel dest) {
		super(dest);
		dest.readBooleanArray(cycleToggle);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		super.writeToParcel(dest, flags);
		dest.writeBooleanArray(cycleToggle);
	}

	public boolean[] getCycleToggle() {
		return cycleToggle;
	}

	public void setCycleToggle(boolean[] cycleToggle) {
		this.cycleToggle = cycleToggle;
	}

}
