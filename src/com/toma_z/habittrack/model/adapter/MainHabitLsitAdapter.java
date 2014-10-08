package com.toma_z.habittrack.model.adapter;

import java.util.List;

import com.toma_z.habittrack.cls.Habit;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MainHabitLsitAdapter extends ArrayAdapter<Habit> {

	public MainHabitLsitAdapter(Context context, int resource,
			List<Habit> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

}
