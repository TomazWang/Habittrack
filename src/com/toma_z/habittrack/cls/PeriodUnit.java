package com.toma_z.habittrack.cls;

import com.toma_z.habittrack.R;

public enum PeriodUnit {
	WEEK(R.string.period_unit_week),MONTH(R.string.period_unit_week),YEAR(R.string.period_unit_week);
	
	
	
	private int txtResId;
	
	private PeriodUnit(int txtResId) {
		this.setTxtResId(txtResId);
	}

	public int getTxtResId() {
		return txtResId;
	}

	private void setTxtResId(int txtResId) {
		this.txtResId = txtResId;
	}
	
	
	
	
	
}
