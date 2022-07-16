package com.random.genix.DarkMode;

import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebSettings;
import androidx.annotation.RequiresApi;
import androidx.webkit.WebViewFeature;

public class DarkMode {
	public DarkMode() {

	}

	@RequiresApi(Build.VERSION_CODES.Q)
	public void setDarkMode(WebView webView) {
		if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
			webView.getSettings().setForceDark(WebSettings.FORCE_DARK_ON);
		}
	}
}
