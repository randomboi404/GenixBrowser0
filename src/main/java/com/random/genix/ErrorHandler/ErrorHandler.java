package com.random.genix.ErrorHandler;

import com.random.genix.MainActivity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ErrorHandler {
	public ErrorHandler() {

	}

	public String getErrorPageUrl(int errorCode, WebView webView) {
		String message = "";
		if (errorCode == WebViewClient.ERROR_AUTHENTICATION) {
			message = "User authentication failed on server.";
		} else if (errorCode == WebViewClient.ERROR_TIMEOUT) {
			message = "The server is taking too much time to communicate. Try again later.";
		} else if (errorCode == WebViewClient.ERROR_TOO_MANY_REQUESTS) {
			message = "Too many requests during this load.";
		} else if (errorCode == WebViewClient.ERROR_UNKNOWN) {
			message = "Generic error.";
		} else if (errorCode == WebViewClient.ERROR_BAD_URL) {
			message = "Check entered URL..";
		} else if (errorCode == WebViewClient.ERROR_CONNECT) {
			message = "Failed to connect to the server.";
		} else if (errorCode == WebViewClient.ERROR_FAILED_SSL_HANDSHAKE) {
			message = "Failed to perform SSL handshake.";
		} else if (errorCode == WebViewClient.ERROR_HOST_LOOKUP) {
			message = "Server or proxy hostname lookup failed. Please check your internet connection.";
		} else if (errorCode == WebViewClient.ERROR_PROXY_AUTHENTICATION) {
			message = "User authentication failed on proxy.";
		} else if (errorCode == WebViewClient.ERROR_REDIRECT_LOOP) {
			message = "Too many redirects.";
		} else if (errorCode == WebViewClient.ERROR_UNSUPPORTED_AUTH_SCHEME) {
			message = "Unsupported authentication scheme (not basic or digest).";
		} else if (errorCode == WebViewClient.ERROR_UNSUPPORTED_SCHEME) {
			message = "Unsupported scheme.";
		} else if (errorCode == WebViewClient.ERROR_FILE) {
			message = "Generic file error.";
		} else if (errorCode == WebViewClient.ERROR_FILE_NOT_FOUND) {
			message = "File not found.";
		} else if (errorCode == WebViewClient.ERROR_IO) {
			message = "The server failed to communicate. Try again later.";
		} else {
			message = "Internet Disconnected.";
		}
		return message;
	}

	public String getStatusCode(int errorCode, WebView webView) {
		String code = "";
		if (errorCode == WebViewClient.ERROR_REDIRECT_LOOP) {
			code = "REDIRECT_LOOP";
		} else if (errorCode == WebViewClient.ERROR_UNSUPPORTED_AUTH_SCHEME) {
			code = "UNSUPPORTED_AUTH_SCHEME";
		} else if (errorCode == WebViewClient.ERROR_FILE_NOT_FOUND) {
			code = "404";
		} else {
			code = ":/";
		}
		return code;
	}
}