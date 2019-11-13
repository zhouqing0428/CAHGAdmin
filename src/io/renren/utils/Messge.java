package io.renren.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Messge {

	public static void main(String[] args) {
		int msg = Messge.send("", "", "","","cs");
		System.out.println("msg=" + msg);
	}
	
	public static int send(String CorpID, String LoginName, String passwd,String mobile, String contexts) {

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");// 可以方便地修改日期格式
		String timeStamp = dateFormat.format(now);
		MD5 md5 = new MD5();
		String strPwsd = md5.getMD5ofStr(CorpID + passwd + timeStamp);
		//String mobile = "";
		String urls = "", txt = "";
		try {
		    contexts = java.net.URLEncoder.encode(contexts, "GBK");
			String baseUrl = "sms3.mobset.com";// 企业CorpID开头数字是3，对应地址是sm3.mobset.com
			urls = "http://" + baseUrl + "/SDK2/Sms_Send.asp?CorpID=" + CorpID
					+ "&LoginName=" + LoginName + "&TimeStamp=" + timeStamp
					+ "&passwd=" + strPwsd + "&send_no=" + mobile
					+ "&Timer=&LongSms=1&msg=" + contexts + "";
			txt = SMSsend(urls);
			String[]  strs= txt.split(",");
			String one=strs[0];
			int result=Integer.parseInt(one);
			return result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	static public String SMSsend(String url) {
		String result = "";
		try {

			URL U = new URL(url);
			URLConnection connection = U.openConnection();
			connection.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
		} catch (Exception e) {
			System.out.println("没有结果！" + e);
			result = "产生异常";
		}
		return result;
	}
}
