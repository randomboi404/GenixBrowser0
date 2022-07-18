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

package com.random.genix;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.webkit.WebViewFeature;
import com.random.genix.IntentHandler.SmsIntentHandler;
import com.random.genix.UserAgent.UserAgent;
import com.random.genix.DarkMode.DarkMode;
import com.random.genix.HandleInput.HandleBox;
import com.random.genix.HomePageUrl.HomePage;
import com.random.genix.WebSettings.WebViewSettings;
import com.random.genix.ErrorHandler.ErrorHandler;
import com.random.genix.FirstLaunch;

import android.annotation.TargetApi;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Message;
import android.annotation.SuppressLint;
import androidx.appcompat.view.menu.MenuBuilder;
import android.webkit.JsResult;
import android.content.DialogInterface;
import android.content.ActivityNotFoundException;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import androidx.appcompat.app.AlertDialog;
import android.view.ViewGroup;
import android.icu.util.Calendar;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.ProgressBar;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.transition.Visibility;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Environment;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;
import android.util.Log;
import android.webkit.WebViewClient;
import android.os.Build;
import android.webkit.WebChromeClient;
import android.content.res.Configuration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import android.graphics.Bitmap;
import android.view.View;
import android.os.Handler;
import android.view.inputmethod.EditorInfo;
import android.view.KeyEvent;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
	public SwipeRefreshLayout refreshLayout;
	public WebView webView;
	public EditText editText;
	public ProgressBar progressBar;
	public static String pkgName;
	public SharedPreferences prefs;
	public WebSettings webSettings;
	public String userAgent;
	public String url;
	public String inspectElement;
	public String history;
	public String errorUrl;
	public String code;
	public String title;
	public String info;
	public String prefsUrl;
	public int i;
	public boolean isIncTab;
	public boolean isDeskMode;
	public boolean isFullScreen;
	public Context context;
	private ValueCallback<Uri> mUploadMessage;
	public ValueCallback<Uri[]> uploadMessage;
	public static final int REQUEST_SELECT_FILE = 100;
	private final static int FILECHOOSER_RESULTCODE = 1;

	public void initializeVariables() {
		webView = new WebView(getApplicationContext());
		refreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
		refreshLayout.addView(webView);
		webView.setMinimumHeight(0);
		webView.setMinimumWidth(0);
		webView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
		webView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
		editText = (EditText) findViewById(R.id.edittext1);
		progressBar = (ProgressBar) findViewById(R.id.progressbar1);
		pkgName = getApplicationContext().getPackageName();
		prefs = getSharedPreferences(pkgName, MODE_PRIVATE);
		webSettings = webView.getSettings();
		userAgent = new UserAgent().getUserAgent();
		i = 0;
		url = "";
		isIncTab = isDeskMode = isFullScreen = false;
		inspectElement = "javascript:(function () {     var script =  document.createElement('script');    script.src=\"//cdn.jsdelivr.net/npm/eruda\";     document.body.appendChild(script);    script.onload = function () {         eruda.init()     } })();";
		history = "history.txt";
		context = getApplicationContext();
		code = title = info = errorUrl = prefsUrl = "";
	}

	public void setDarkMode() {
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
			try {
				new DarkMode().setDarkMode(webView);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Log.e("Dark Mode Off", "Not supported for your current webview version.");
			Toast.makeText(getApplicationContext(), "Dark Mode isn't supported on your device.", Toast.LENGTH_LONG)
					.show();
		}
	}

	public void loadWebViewSettings() {

		// Load main webView settings from WebViewSettings class.

		new WebViewSettings().loadWebSettings(webView, userAgent);

		// Save cookies and other form data.

		CookieManager.getInstance().setAcceptCookie(true);

		// Other settings goes here.

		webSettings.setUserAgentString(userAgent);
		getWindow().setFlags(16777216, 16777216);
		webSettings.setAppCachePath(
				new StringBuilder(String.valueOf(getApplicationContext().getFilesDir().getAbsolutePath()))
						.append("/cache").toString());
		webSettings.setDatabasePath(
				new StringBuilder(String.valueOf(getApplicationContext().getFilesDir().getAbsolutePath()))
						.append("/databases").toString());
		try {
			webSettings.setSafeBrowsingEnabled(true);
		} catch (Exception e) {
			Log.e("Safe Mode Off", "Android Not Supported.");
		}
		if (android.os.Build.VERSION.SDK_INT >= 19) {
			webView.setLayerType(2, null);
		} else {
			webView.setLayerType(1, null);
		}

		setDarkMode();

		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("The page says");
				builder.setMessage(message);
				builder.setCancelable(false);
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int w) {
						dialog.cancel();
					}
				});
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
				result.cancel();
				return true;
			}

			@Override
			public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
				WebView.HitTestResult result = view.getHitTestResult();
				String data = result.getExtra();
				webView.loadUrl(data);
				return false;
			}

			@Override
			public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback,
					WebChromeClient.FileChooserParams fileChooserParams) {
				if (uploadMessage != null) {
					uploadMessage.onReceiveValue(null);
					uploadMessage = null;
				}

				uploadMessage = filePathCallback;

				Intent intent = fileChooserParams.createIntent();
				try {
					startActivityForResult(intent, REQUEST_SELECT_FILE);
				} catch (ActivityNotFoundException e) {
					uploadMessage = null;
					Toast.makeText(getApplicationContext(), "Cannot Open File Chooser.", Toast.LENGTH_LONG).show();
					return false;
				}
				return true;
			}
		});
		webView.setWebViewClient(new GenixClient());

		registerForContextMenu(webView);
		getWindow().setFlags(16777216, 16777216);
		webView.setDownloadListener(new DownloadListener() {
			BroadcastReceiver onComplete = new BroadcastReceiver() {

				public void onReceive(Context context, Intent intent) {
					Toast.makeText(MainActivity.this.getApplicationContext(), "Download finished.", 0);
				}

			};

			public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
				DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
				request.setTitle(URLUtil.guessFileName(str, str3, str4));
				request.setDescription("Downloading file...");
				request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
				request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
						URLUtil.guessFileName(str, str3, str4));
				((DownloadManager) MainActivity.this.getSystemService("download")).enqueue(request);
				Toast.makeText(MainActivity.this.getApplicationContext(), "Downloading...", 0).show();
				MainActivity.this.registerReceiver(this.onComplete,
						new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
			}

		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			if (requestCode == REQUEST_SELECT_FILE) {
				if (uploadMessage == null)
					return;
				uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
				uploadMessage = null;
			}
		} else if (requestCode == FILECHOOSER_RESULTCODE) {
			if (null == mUploadMessage)
				return;
			Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
			mUploadMessage.onReceiveValue(result);
			mUploadMessage = null;
		} else
			Toast.makeText(getApplicationContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
	}

	public void handleBoxInput(String input) {
		new HandleBox().handleInput(input, editText, webView, context);
	}

	public String getHomePageUrl() {
		return new HomePage().getHomePageUrl(context);
	}

	private class GenixClient extends WebViewClient {
		@Override
		public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
			progressBar.setVisibility(View.VISIBLE);
			i = 1;
			while (i != 100) {
				progressBar.setProgress(i);
				i++;
			}
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView webView, String url) {
			if (url.startsWith("intent://")) {
				webView.stopLoading();
				try {
					Context context = webView.getContext();
					Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);

					if (intent != null) {
						webView.stopLoading();

						PackageManager packageManager = context.getPackageManager();
						if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
							ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_ALL);
						} else {
							ResolveInfo info = packageManager.resolveActivity(intent,
									PackageManager.MATCH_DEFAULT_ONLY);
						}
						if (info != null) {
							context.startActivity(intent);
						} else {
							String fallbackUrl = intent.getStringExtra("browser_fallback_url");
							webView.loadUrl(fallbackUrl);
						}

						return true;
					}
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			} else if (url.startsWith("tel:")) {
				webView.getContext().startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(url)));
			} else if (url.startsWith("sms:")) {
				new SmsIntentHandler().handleSms(url, context);
			} else {
				webView.loadUrl(url);
				if (url.startsWith("file:///")) {
					if (url.contains("inc_tab")) {
						editText.setText("Asset File");
					}
				} else if (url.startsWith("data:text")) {
					editText.setText("Text Mode");
				} else {
					editText.setText(url);
				}
			}
			return true;
		}

		@Override
		public void onPageFinished(WebView webView, String url) {
			progressBar.setVisibility(View.GONE);
			i = 0;
			progressBar.setProgress(i);
			url = webView.getUrl();
			if (url.startsWith("file:///")) {
				if (url.contains("file:///android_asset")) {
					editText.setText("Asset File");
				}
			} else if (url.startsWith("data:text")) {
				editText.setText("Text Mode");
			} else {
				prefsUrl = webView.getUrl();
				editText.setText(url);
			}
			editText.clearFocus();
		}

		@TargetApi(android.os.Build.VERSION_CODES.M)
		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			info = new ErrorHandler().getErrorPageUrl(errorCode, webView);
			title = "Webpage failed to load.";
			code = new ErrorHandler().getStatusCode(errorCode, webView);
			errorUrl = "file:///android_asset/error_pages/error.html?code=" + code + "&title=" + title + "&info="
					+ info;
			view.loadUrl(errorUrl);
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initializeVariables();
		loadWebViewSettings();

		String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
		int requestCode = 1;
		if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
		}

		if (getIntent().getData() != null) {
			webView.loadUrl(this.getIntent().getData().toString());
		} else if (getSharedPreferences("prefs", MODE_APPEND).getString("url", "") != null) {
			webView.loadUrl(getSharedPreferences("prefs", MODE_APPEND).getString("url", ""));
		} else {
			webView.loadUrl(getHomePageUrl());
		}
		if (webView.getUrl() == "" || webView.getUrl() == null) {
			webView.loadUrl(getHomePageUrl());
		}
		editText.setSelectAllOnFocus(true);

		editText.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_ACTION_SEND) {
					handled = true;
					handleBoxInput(editText.getText().toString());
				}
				return handled;
			}
		});

		refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				webView.reload();
				refreshLayout.setRefreshing(false);
			}
		});
	}

	@Override
	public void onBackPressed() {
		if (webView.canGoBack()) {
			webView.goBack();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		webView.setTag(null);
		webView.clearHistory();
		webView.removeAllViews();
		webView.clearView();
		webView.destroy();
		webView = null;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (prefs.getBoolean("firstrun", true)) {
			Toast.makeText(getApplicationContext(),
					"Please grant all required permissions and kindly let the app be restarted as first run takes a restart.",
					3000).show();
			prefs.edit().putBoolean("firstrun", false).commit();
			Intent firstRunIntent = new Intent(MainActivity.this, FirstLaunch.class);
			MainActivity.this.startActivity(firstRunIntent);
			finish();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		prefs = getSharedPreferences("prefs", MODE_PRIVATE);
		prefs.edit().putString("url", prefsUrl).commit();
	}

	@SuppressLint("RestrictedApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.option_menu, menu);

		if (menu instanceof MenuBuilder) {
			MenuBuilder m = (MenuBuilder) menu;
			m.setOptionalIconsVisible(true);
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case R.id.back:
			if (webView.canGoBack()) {
				webView.goBack();
			}
			return true;
		case R.id.forward:
			if (webView.canGoForward()) {
				webView.goForward();
			}
			return true;
		case R.id.reload:
			webView.reload();
			return true;
		case R.id.item1:
			if (isIncTab) {
				webView.loadUrl("file:///android_asset/inc_tab.html");
			} else {
				webView.loadUrl(getHomePageUrl());
			}
			return true;
		case R.id.item4:
			if (isIncTab) {
				CookieManager.getInstance().setAcceptCookie(true);
				this.webView.getSettings().setAppCacheEnabled(true);
				webView.clearCache(false);
				webView.getSettings().setSavePassword(true);
				this.webView.getSettings().setSaveFormData(true);
				webSettings.setDomStorageEnabled(true);
				isIncTab = false;
				this.webView.loadUrl(getHomePageUrl());
				setTitle("Genix Browser 0");
				menuItem.setChecked(false);
			} else {
				setTitle("Incognito Tab");
				CookieManager.getInstance().setAcceptCookie(false);
				webView.getSettings().setCacheMode(2);
				this.webView.getSettings().setAppCacheEnabled(false);
				webView.clearHistory();
				webView.clearCache(true);
				webView.clearFormData();
				webSettings.setSavePassword(false);
				webSettings.setSaveFormData(false);
				webSettings.setDomStorageEnabled(false);
				webView.loadUrl("file:///android_asset/inc_tab.html");
				isIncTab = true;
				menuItem.setChecked(true);
			}
			return true;
		case R.id.item5:
			if (isDeskMode) {
				webSettings.setUserAgentString(userAgent);
				webSettings.setLoadWithOverviewMode(false);
				webSettings.setUseWideViewPort(false);
				webView.reload();
				isDeskMode = false;
				menuItem.setChecked(false);
			} else {
				webSettings.setUserAgentString(
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36");
				webSettings.setLoadWithOverviewMode(true);
				webSettings.setUseWideViewPort(true);
				webView.reload();
				isDeskMode = true;
				menuItem.setChecked(true);
				if (isIncTab) {
					setTitle("Incognito Tab");
				} else {
					setTitle("Genix Browser 0");
				}
			}
			return true;
		case R.id.item6:
			if (isFullScreen) {
				editText.setVisibility(View.VISIBLE);
				isFullScreen = false;
				menuItem.setChecked(false);
			} else {
				editText.setVisibility(View.GONE);
				isFullScreen = true;
				menuItem.setChecked(true);
				setTitle("Fullscreen Mode");
			}
			return true;
		case R.id.item7:
			webView.loadUrl(inspectElement);
			return true;
		case R.id.share:
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			return true;
		case R.id.item11:
			Intent changeSE = new Intent(MainActivity.this, Settings.class);
			MainActivity.this.startActivity(changeSE);
			return true;
		default:
			return super.onOptionsItemSelected(menuItem);
		}
	}
}
