package com.toma_z.habittrack.fragment.dialog;

import com.toma_z.habittrack.MainActivity;
import com.toma_z.habittrack.R;
import com.toma_z.habittrack.cls.PeriodUnit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class NewHabitDialogFragment extends DialogFragment {

	private static final String tag = "NewHabitDialogFragment";

	private View view;

	// -- user input data
	private String habitName = "";
	private boolean[] weekdayToggles = new boolean[7];
	private PeriodUnit periodUnit = PeriodUnit.WEEK;
	private boolean isFixed = true;
	private String description = "";
	private int goalTimes = 21;
	private int periodTimes = 0;

	private View createView() {

		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_new_habit, null, false);

		// -- get widgets

		final EditText txt_habitName = (EditText) view
				.findViewById(R.id.txt_newHabitName);

		Spinner spinner_plan_type = (Spinner) view
				.findViewById(R.id.spinner_plan_type);

		TextView txt_weekdayName_0Sun = (TextView) view
				.findViewById(R.id.btn_plan_week_sunday);
		TextView txt_weekdayName_1Mon = (TextView) view
				.findViewById(R.id.btn_plan_week_monday);
		TextView txt_weekdayName_2Tue = (TextView) view
				.findViewById(R.id.btn_plan_week_tuesday);
		TextView txt_weekdayName_3Wed = (TextView) view
				.findViewById(R.id.btn_plan_week_wednesday);
		TextView txt_weekdayName_4Thu = (TextView) view
				.findViewById(R.id.btn_plan_week_thursday);
		TextView txt_weekdayName_5Fri = (TextView) view
				.findViewById(R.id.btn_plan_week_friday);
		TextView txt_weekdayName_6Sat = (TextView) view
				.findViewById(R.id.btn_plan_week_saturday);

		Spinner spinner_period_unit = (Spinner) view
				.findViewById(R.id.spinner_plan_flex_unit);
		EditText txt_plan_flex_dayNum = (EditText) view
				.findViewById(R.id.txt_plan_flex_dayNum);

		final EditText txt_description = (EditText) view
				.findViewById(R.id.txt_description);
		EditText txt_goalTimes = (EditText) view
				.findViewById(R.id.txt_goalTimes);

		final TextView txt_goalUnits = (TextView) view
				.findViewById(R.id.txt_goal_units);

		final View view_plan_fixed = view.findViewById(R.id.view_plan_fixed);
		final View view_plan_flex = view.findViewById(R.id.view_plan_flexible);
		view_plan_flex.setVisibility(View.GONE);

		// -- set functions

		Resources res = getActivity().getResources();

		ArrayAdapter<CharSequence> periodUnitsAdapter = ArrayAdapter
				.createFromResource(getActivity(), R.array.period_units,
						android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> planTypesAdapter = ArrayAdapter
				.createFromResource(getActivity(), R.array.plan_types,
						android.R.layout.simple_spinner_item);

		periodUnitsAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		planTypesAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_period_unit.setAdapter(periodUnitsAdapter);
		spinner_plan_type.setAdapter(planTypesAdapter);

		// -- listeners
		spinner_plan_type
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						Log.d(tag,
								"createView spinner_plan_types onItemSelect = "
										+ position);
						switch (position) {
						case 0:
							// selected fixed
							if (view_plan_fixed.getVisibility() != View.VISIBLE) {

								view_plan_fixed.setVisibility(View.VISIBLE);
								view_plan_flex.setVisibility(View.GONE);

								txt_goalUnits
										.setText(R.string.newHabit_goalUnits);

								setFixed(true);

							}
							break;
						case 1:
							// selected flex
							if (view_plan_flex.getVisibility() != View.VISIBLE) {
								view_plan_fixed.setVisibility(View.GONE);
								view_plan_flex.setVisibility(View.VISIBLE);

								txt_goalUnits.setText(periodUnit.getTxtResId());

								setFixed(false);
							}
							break;
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// do nothing
					}

				});

		spinner_period_unit
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {

						switch (position) {

						case 0:
							// select week
							setPeriodUnit(PeriodUnit.WEEK);
							break;
						case 1:
							// select month
							setPeriodUnit(PeriodUnit.MONTH);
							break;
						case 2:
							// select year
							setPeriodUnit(PeriodUnit.YEAR);
							break;
						}

						if (!isFixed()) {
							txt_goalUnits
									.setText(getPeriodUnit().getTxtResId());

						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}

				});

		TextView[] weekBtns = new TextView[] { txt_weekdayName_0Sun,
				txt_weekdayName_1Mon, txt_weekdayName_2Tue,
				txt_weekdayName_3Wed, txt_weekdayName_4Thu,
				txt_weekdayName_5Fri, txt_weekdayName_6Sat };

		for (int i = 0; i < weekBtns.length; i++) {
			TextView tv = weekBtns[i];
			tv.setOnClickListener(new OnWeekTextClickListener(i));
		}

		txt_habitName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				setHabitName(s.toString());
				Log.d(tag, "set name to " + getHabitName());
			}
		});

		txt_description.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				setDescription(txt_description.getText().toString());
				Log.d(tag, "set description to " + getDescription());
			}
		});

		txt_plan_flex_dayNum.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				int dayNum = Integer.valueOf((s.toString()));
				setPeriodTimes(dayNum);
			}
		});

		
		txt_goalTimes.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				int times = Integer.valueOf((s.toString()));
				setGoalTimes(times);
			}
		});
		
		
		return view;

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		View view = createView();

		// -- create dialog
		AlertDialog.Builder builder = new Builder(getActivity());
		if (view == null) {
			builder.setMessage("somethings wrong");
			return builder.create();
		} else {
			builder.setView(view)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									if (saveNewHabit()) {
										((MainActivity) getActivity())
												.refreshList();
									}
								}
							}).setNegativeButton("Cancel", null)
					.setTitle(R.string.newHabit_title);
			return builder.create();
		}
	}

	private boolean saveNewHabit() {
		// TODO

		Log.d(tag,"new habit create");
		if(isFixed()){
			Log.d(tag, "Fixed habit named: "+getHabitName()+", do on "+getWeekdayToggles().toString()+" for "+getGaolTimes()+"times");
			
		}else{

			Log.d(tag, "Flexible habit named: "+getHabitName()+", do "+getPeriodTimes() + "times"+" for "+getGaolTimes()+getPeriodUnit());
			
		}
		
		
		
		
		
		return false;
	}

	// -- getter and setter

	private String getHabitName() {
		return habitName;
	}

	private void setHabitName(String habitName) {
		this.habitName = habitName;
	}

	private boolean[] getWeekdayToggles() {
		return weekdayToggles;
	}

	private void setWeekdayToggles(boolean[] weekdayToggles) {
		this.weekdayToggles = weekdayToggles;
	}

	private PeriodUnit getPeriodUnit() {
		return periodUnit;
	}

	private void setPeriodUnit(PeriodUnit periodUnit) {
		this.periodUnit = periodUnit;
	}

	private boolean isFixed() {
		return isFixed;
	}

	private void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}

	private String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	private int getGaolTimes() {
		return goalTimes;
	}

	private void setGoalTimes(int goalTimes) {
		this.goalTimes = goalTimes;
	}

	// -- support classes

	private int getPeriodTimes() {
		return periodTimes;
	}

	public void setPeriodTimes(int period) {
		this.periodTimes = period;
	}

	private class OnWeekTextClickListener implements View.OnClickListener {

		private int index;

		public OnWeekTextClickListener(int index) {
			this.index = index;
		}

		@Override
		public void onClick(View v) {

			if (getWeekdayToggles()[index]) {
				((TextView) v).setTextColor(getResources().getColor(
						R.color.black));

			} else {
				((TextView) v).setTextColor(getResources()
						.getColor(R.color.red));
			}

			getWeekdayToggles()[index] = !getWeekdayToggles()[index];
		}

	}

}
