package com.random.genix.IntentHandler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.random.genix.MainActivity;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SmsIntentHandler {
	public void handleSms(String url, Context context) {
		Intent intent = new Intent(Intent.ACTION_SENDTO);

		String phoneNumber = url.split("[:?]")[1];

		if (!TextUtils.isEmpty(phoneNumber)) {
			intent.setData(Uri.parse("smsto:" + phoneNumber));
		} else {
			intent.setData(Uri.parse("smsto:"));
		}
		
		if (url.contains("body=")) {
			String smsBody = url.split("body=")[1];

			try {
				smsBody = URLDecoder.decode(smsBody, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (!TextUtils.isEmpty(smsBody)) {
				intent.putExtra("sms_body", smsBody);
			}
		}

		if (intent.resolveActivity(context.getPackageManager()) != null) {
			context.startActivity(intent);
		} else {
			Toast.makeText(context, "No SMS app found.", Toast.LENGTH_LONG).show();
		}
	}
}