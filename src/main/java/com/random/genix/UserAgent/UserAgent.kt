package com.random.genix.UserAgent

import android.os.Build
import kotlin.String

class UserAgent {
	fun UserAgent() {
		
	}
    fun getUserAgent(): String {
		val version = Build.VERSION.RELEASE
		val model = Build.MODEL
		val ua = "Mozilla/5.0 (Linux; Android " + version + "; " + model + ") AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.41 Mobile Safari/537.36"
		return ua
	}
}