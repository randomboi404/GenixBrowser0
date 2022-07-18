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
