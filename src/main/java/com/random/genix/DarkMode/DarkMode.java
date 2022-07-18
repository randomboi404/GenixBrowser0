/*
Copyright (C) 2022 GenixBrowser0

This file is part of GenixBrowser0.

GenixBrowser0 is free software:
you can redistribute it and/or modify it under the terms of
the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

GenixBrowser0 is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with GenixBrowser0.
If not, see <https://www.gnu.org/licenses/>.
*/

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
