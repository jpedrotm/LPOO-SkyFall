package com.jose.skyfall;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.jose.skyfall.Logic.SkyFall;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGyroscope = true;
		config.useAccelerometer = false;
		config.useCompass = false;
		initialize(new SkyFall(), config);
	}
}
