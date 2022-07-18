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

package com.random.genix.WebSettings

import android.webkit.WebView
import android.webkit.WebSettings

class WebViewSettings {
	fun WebViewSettings() {
		
	}
	
	fun loadWebSettings(webView: WebView, userAgent: String) {
		val webSettings: WebSettings = webView.settings
		webSettings.setJavaScriptEnabled(true)
		webSettings.setSupportZoom(true)
		webSettings.setBuiltInZoomControls(true)
		webSettings.setDisplayZoomControls(false)
		webSettings.setJavaScriptCanOpenWindowsAutomatically(false)
		webView.setLayerType(2, null)
		webSettings.setSavePassword(true)
		webSettings.setSaveFormData(true)
		webSettings.setEnableSmoothTransition(true)
		webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS)
		webSettings.setCacheMode(1)
		webSettings.setAppCacheEnabled(true)
		webSettings.setDomStorageEnabled(true)
		webView.setSoundEffectsEnabled(true)
		webSettings.setLightTouchEnabled(true)
		webView.requestFocus()
		webSettings.setCacheMode(1)
		webSettings.setSupportMultipleWindows(true)
		webSettings.setSavePassword(true)
		webSettings.setSaveFormData(true)
		webSettings.setGeolocationEnabled(true)
		webSettings.setUserAgentString(userAgent)
		webSettings.setAllowFileAccess(true)
		webSettings.setAllowContentAccess(true)
	}
}
