package com.toma_z.habittrack;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.ActionBar;
import android.app.Activity;

public class MainActivity extends ActionBarActivity {

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Handle presses on the action bar items

		switch (item.getItemId()) {

		case R.id.menuAction_newTask:
			break;

		default:
			return super.onOptionsItemSelected(item);
		}

		return super.onOptionsItemSelected(item);

	}

}
