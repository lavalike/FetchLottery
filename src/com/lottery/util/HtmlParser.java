package com.lottery.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.lottery.bean.LotteryHistory;

/**
 * HTML解析器 返回最终json格式数据
 * 
 * @author wangzhen
 */
public class HtmlParser {

	/**
	 * 解析html 返回json
	 * 
	 * @param html
	 */
	public static String parse(String html) {
		List<LotteryHistory> result = new ArrayList<>();
		LotteryHistory lotteryHistory;
		Document doc = Jsoup.parse(html);
		// parent > child: 查找某个父元素下的直接子元素，比如：可以用div.content > p 查找 p 元素，也可以用body
		// > * 查找body标签下所有直接子元素
		Element tbody = doc.select("table.historylist > tbody").first();
		// 查找tbody下所有直接tr元素
		Elements trList = tbody.select(">tr");
		Elements tdList;
		List<String> listRedBall;
		List<String> listBlueBall;
		for (Element element : trList) {
			// 查找直接td元素
			tdList = element.select(">td");
			// 期号
			String number = tdList.get(0).text();
			// 开奖日期
			String date = tdList.get(1).text();

			Element balls = element.select("[class=balls]").first();
			// 红球
			Elements redBalls = balls.getElementsByClass("redBalls").first().select("em");
			listRedBall = new ArrayList<>();
			for (Element elementRed : redBalls) {
				listRedBall.add(elementRed.text());
			}
			// 蓝球
			Elements blueBalls = balls.getElementsByClass("blueBalls").first().select("em");
			listBlueBall = new ArrayList<>();
			for (Element elementBlue : blueBalls) {
				listBlueBall.add(elementBlue.text());
			}
			lotteryHistory = new LotteryHistory();
			lotteryHistory.setNumber(number);
			lotteryHistory.setDate(date);
			lotteryHistory.setRedBalls(listRedBall);
			lotteryHistory.setBlueBalls(listBlueBall);
			result.add(lotteryHistory);
		}
		return new Gson().toJson(result);
	}
}
