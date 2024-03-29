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
