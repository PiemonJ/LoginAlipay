package com.lin.alipay.login;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
	
	public static final String LOGIN_URL = "https://auth.alipay.com/login/index.htm?goto=https%3A%2F%2Fmy.alipay.com%2Fportal%2Fi.htm";
	
	public static final String SUCCESS_URL = "https://my.alipay.com/portal/i.htm";
	
	public static final String LOGIN_ACCOUNT = "18820691730";//支付宝账号
	
	public static final String LOGIN_PASSWORD = "zhi18820691730";//支付宝密码
	
	public static WebDriver webDriver;
	
	private static void sleep(long sec){
		
		try{
			Thread.sleep(sec);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 登录支付宝.
	 */
	private static void login(){
		
		/**
		 * 这里打开的是Chrome浏览器
		 */
		System.setProperty("webdriver.chrome.driver", "/home/lin/chromedriver");
		webDriver = new ChromeDriver();
		
		webDriver.get(LOGIN_URL);//拿到登录页面
		
		/**
		 * 输入账号
		 */
		webDriver.findElement(By.id("J-input-user")).clear();
		webDriver.findElement(By.id("J-input-user")).sendKeys(LOGIN_ACCOUNT);
		
		sleep(500);//休眠，防止密码输入到用户名输入框中去
		
		/**
		 * 输入密码
		 */
		webDriver.findElement(By.id("password_input")).clear();
		webDriver.findElement(By.id("password_input")).sendKeys(LOGIN_PASSWORD);
		
		sleep(9000);//等待输入验证码时间
		
		webDriver.findElement(By.id("J-login-btn")).click();
		
		/**
		 * 等待登录成功
		 */
		while(!webDriver.getCurrentUrl().equals(SUCCESS_URL)){
			//判断当前URL是不是登录成功页面的URL
		}
		String page = webDriver.getPageSource();
		Document document = Jsoup.parse(page);
		
		System.out.println(fetch(document));
	}
	
	/**
	 * 抓取账户信息.
	 * @param url
	 */
	private static List<Record> fetch(Document document){
		
		Element table = document.select("#tradeRecordsIndex").get(0);
		//System.out.println(table.html());
		Elements tableRows = table.select("tr");
		
		Iterator<Element> iterator = tableRows.iterator();
		
		List<Record> records = new ArrayList<>();
		
		while(iterator.hasNext()){
			Element element = iterator.next();
			String name = element.select("td.name > p > a").text();
			String time = element.select("td.time > p").text();
			
			String amount = element.select("td.amount > span").text();
			String status = element.select("td.status > p:nth-child(1)").text();
			
			records.add(new Record(time, name, amount, status));
		}
		
		return records;
	}
	
	public static void main(String[] args) {
		
		login();
	}

}
