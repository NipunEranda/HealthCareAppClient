package com.healthcare.utils;

import java.util.HashMap;

public class StringSplitter {

	public static HashMap<String, String> proceed(String string) {
		String[] values = string.toString().split(",");
		HashMap<String, String> h = new HashMap<>();
		for (String x : values) {
			String v = "";
			if (x.contains("{")) {
				v = x.replace("{", "");
			} else if (x.contains("}")) {
				v = x.replace("}", "");
			} else {
				v = x;
			}
			// value = value.replaceAll("^\"|\"$", "");
			String key = v.split(":")[0].replaceAll("^\"|\"$", "");
			String value = v.split(":")[1].replaceAll("^\"|\"$", "");
			h.put(key, value);
		}
		return h;
	}
}
