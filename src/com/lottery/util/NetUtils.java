package com.lottery.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 读取历史彩票数据
 * 
 * @author wangzhen
 *
 */
public class NetUtils {

	public static final String URL_LOTTERY = "http://baidu.lecai.com/lottery/draw/list/50?type=range_date&start=2014-02-11&end=2017-05-11";

	public static String fetch() {
		URL url;
		try {
			url = new URL(URL_LOTTERY);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
			connection.connect();
			if (connection.getResponseCode() == 200) {
				InputStream is = connection.getInputStream();
				return getString(is);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getString(InputStream is) {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return builder.toString();
	}

}
