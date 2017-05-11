package com.lottery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.lottery.util.HtmlParser;
import com.lottery.util.NetUtils;

/**
 * 抓近三年彩票中奖数据
 * 
 * @author wangzhen
 *
 */
public class Client {

	public static void main(String[] args) {
		String fetch = NetUtils.fetch();
		if (null == fetch || fetch.equals("")) {
			System.out.println("数据请求失败");
			return;
		}
		String result = HtmlParser.parse(fetch);
		createJsonFile(result);
	}

	/**
	 * 创建json文件
	 * 
	 * @param result
	 */
	private static void createJsonFile(String result) {
		try {
			File file = new File("lottery_history.json");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(result);
			bufferedWriter.close();
			System.out.println("json文件创建成功，路径：" + file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
