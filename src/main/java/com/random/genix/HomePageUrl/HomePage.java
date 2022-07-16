package com.random.genix.HomePageUrl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class HomePage {
	public HomePage() {

	}

	public String getHomePageUrl(Context context) {
		String home = "";
		if (context.getSharedPreferences("prefs", context.MODE_APPEND).getString("engine", "") != null) {
			home = context.getSharedPreferences("prefs", context.MODE_APPEND).getString("engine", "");
		}
		if (home == "") {
			home = "https://www.google.com/";
		}
		return home;
	}
}