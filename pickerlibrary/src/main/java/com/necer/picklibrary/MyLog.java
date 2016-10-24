package com.necer.picklibrary;


import android.util.Log;

public class MyLog {

	private static boolean debug = true;
	//private static boolean debug = false;

	public static void v( String msg) {
		if (debug) {
			Log.v("ZHOUDAO", msg);
		
		}

	}

	public static void d(String msg) {
		if (debug) {
			Log.d("ZHOUDAO", msg);
		}

	}

	public static void i(String msg) {
		if (debug) {
			Log.i("ZHOUDAO", msg);
		}

	}

	public static void w( String msg) {
		if (debug) {
			Log.w("ZHOUDAO", msg);
		}

	}

	public static void e( String msg) {
		if (debug) {
			Log.e("ZHOUDAO", msg);
		}
	}
}
