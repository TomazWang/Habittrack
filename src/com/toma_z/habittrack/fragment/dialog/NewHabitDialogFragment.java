package com.toma_z.habittrack.fragment.dialog;

import com.toma_z.habittrack.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog.Builder;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NewHabitDialogFragment extends DialogFragment {

	private View view;

	private View createView() {

		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_new_habit, null, false);

		// -- get widgets

		EditText txt_habitName = (EditText) view
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

		View view_plan_fixed = view.findViewById(R.id.view_plan_fixed);
		View view_plan_flex = view.findViewById(R.id.view_plan_flexible);

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
			builder.setView(view).setPositiveButton("OK", null)
					.setNegativeButton("Cancel", null)
					.setTitle(R.string.newHabit_title);
			return builder.create();
		}
	}
}
