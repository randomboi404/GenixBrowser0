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