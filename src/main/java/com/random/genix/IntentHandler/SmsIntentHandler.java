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
