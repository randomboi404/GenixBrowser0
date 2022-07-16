package com.random.genix.HandleInput;

import com.random.genix.HomePageUrl.HomePage;

import android.content.Context;
import android.webkit.WebView;
import android.widget.EditText;

public class HandleBox {
	public HandleBox() {

	}

	public boolean handleInput(String input, EditText editText, WebView webView, Context context) {
		boolean handled = false;
		if (!(input == null) || !(input.replace(" ", "") == "")) {
			if (input.startsWith("http://") || input.startsWith("https://")) {
				webView.loadUrl(input);
				editText.setText(webView.getUrl());
			} else if (input.startsWith("file:///")) {
				webView.loadUrl(input);
				editText.setText("Assets");
			} else if (input.startsWith("data:text")) {
				webView.loadUrl(input);
				editText.setText("Text Mode");
			} else {
				if (!input.contains(" ")) {
					if (input.endsWith(".com") || input.endsWith(".org") || input.endsWith(".edu")
							|| input.endsWith(".gov") || input.endsWith(".uk") || input.endsWith(".net")
							|| input.endsWith(".in") || input.endsWith(".tk") || input.endsWith(".cf")
							|| input.startsWith("www.")) {
						webView.loadUrl("https://" + input);
						editText.setText(webView.getUrl());
						return true;
					}
				}
				if (new HomePage().getHomePageUrl(context).contains("duckduckgo")) {
					webView.loadUrl(new HomePage().getHomePageUrl(context) + "?q=" + input.replace(" ", "+"));
				} else {
					webView.loadUrl(new HomePage().getHomePageUrl(context) + "search?q=" + input.replace(" ", "+"));
				}
				editText.setText(webView.getUrl());
			}
		} else {
			editText.setText(webView.getUrl());
		}
		handled = true;
		return handled;
	}
}