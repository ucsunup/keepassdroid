/*
 * Copyright 2014-2016 Brian Pellin.
 *     
 * This file is part of KeePassDroid.
 *
 *  KeePassDroid is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  KeePassDroid is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePassDroid.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.ucsunup.keepass.compat;

import java.lang.reflect.Field;

import android.os.Build;

public class BuildCompat {
	private static Field manufacturer;
	private static String manuText;
	
	public static final int VERSION_CODE_GINGERBREAD = 9;
	public static final int VERSION_CODE_ICE_CREAM_SANDWICH = 14;
	public static final int VERSION_CODE_JELLY_BEAN = 16;
	public static final int VERSION_CODE_JELLY_BEAN_MR2 = 18;
	public static final int VERSION_KITKAT = 19;

	private static Field versionSDK;
	private static int versionInt;
	
	static {
		// MANUFACTURER is only available in API version 4 and later
		try {
			manufacturer = Build.class.getField("MANUFACTURER");
			manuText = (String) manufacturer.get(null);
		} catch (Exception e) {
			manuText = "";
		}
		
		// SDK is only available in API version 4 and later
		try {
			versionSDK = Build.VERSION.class.getField("SDK_INT");
			versionInt = versionSDK.getInt(null);
		} catch (Exception e) {
			try {
                versionInt = Integer.parseInt(Build.VERSION.SDK);
			} catch (Exception nfe) {
				versionInt = -1;
			}
		}
	}
	
	public static String getManufacturer() {
		return manuText;
	}
	
	public static int getSdkVersion() {
		return versionInt;
	}

}
