package com.toma_z.habittrack;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;

public class MainActivity extends Activity {

	private ActionBar actionBar;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_main);
		initActionBar();
		initWidgets();
	}

	private void initWidgets() {
		// TODO Auto-generated method stub

	}

	private void initActionBar() {
		actionBar = getActionBar();
	}
}
