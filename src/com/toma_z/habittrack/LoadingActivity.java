package com.toma_z.habittrack;

import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

public class LoadingActivity extends Activity {

	private final int delayMillis = 1000; // delay between fade_in and nextPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_loading);
		showtitles();
	}

	private void nextPage() {
		startActivity(new Intent().setClass(LoadingActivity.this,
				MainActivity.class));
	}

	private void showtitles() {
		final Animation fade_in = AnimationUtils.loadAnimation(this,
				anim.fade_in);

		fade_in.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				Handler timeDelay = new Handler();
				timeDelay.postDelayed(new Runnable() {

					@Override
					public void run() {
						nextPage();
					}
				}, delayMillis);
			}
		});

		View view = this.findViewById(android.R.id.content);
		view.setAlpha(0f);
		view.startAnimation(fade_in);

	}
}
