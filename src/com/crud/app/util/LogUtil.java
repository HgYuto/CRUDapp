package com.crud.app.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.annotation.WebListener;




@WebListener
public class LogUtil {
	 //ログ設定プロパティファイルのファイル名
 	 protected static final String LOGGING_PROPERTIES= "C://project//workspace//CRUDapp/WebContent/WEB-INF/log4j.properties";

 	 //private static Logger log = Logger.getLogger(LogUtil.class);

	 public static void initialized(String a) {

		 final Properties prop = new Properties();
		 InputStream inStream = null;

		 try {
			 inStream = new BufferedInputStream(new FileInputStream(LOGGING_PROPERTIES));
	         prop.load(inStream);
	         System.out.println(prop.getProperty("log4j.appender.Appender2"));


	         //final String value = prop.getProperty("log4j.appender.Appender2");

	         //プロパティファイルを読み込む
	         //PropertyConfigurator.configure(prop.getProperty("LOGGING_PROPERTIES"));

	         //Appenderインスタンスの生成
	        //Appender appender = new FileAppender();

			// ログレベルが WARN 以上の場合にログイベントが発生するように設定
			//log.setLevel(Level.INFO);

			//log.addAppender(appender);

			//System.out.println(value);
			//ログ開始日付
			String yyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
			System.setProperty("day", yyMMdd);

			//log.info("出力された！");
		 }
		catch(IOException e){
			e.printStackTrace();
		 }
		 finally {
	            try {
	                if (inStream != null) {
	                    inStream.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	 }

	 public void infoLog(String a) {
		 //log.info(a);
	 }

	 public void wornLog(String a) {
		 //log.warn(a);
	 }

	 public void errorLog(String a) {
		 //log.error(a);
	 }


}
