package com.crud.app.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {
	//ログ設定プロパティファイルのファイル名
	 static final private String LOGGING_PROPERTIES= "C://project//workspace//CRUDapp/WebContent/WEB-INF/log4j.properties";

	ClassLoader loader = Thread.currentThread().getContextClassLoader();
	 URL url = loader.getResource("log4j.properties");




	 public static void initialized() {

		 //⇓log4j使用
		 Logger log = Logger.getLogger(LogUtil.class.getName());

		//⇓logging使用
		 //Logger log = Logger.getLogger(LogUtil.class.getName());


// * log4j
 		//プロパティファイルを読み込む
		 PropertyConfigurator.configure(LOGGING_PROPERTIES);


		 final Properties prop = new Properties();
		 InputStream inStream = null;


		 try {
/*	logging使用
			 Handlerを生成しloggerに登録
	         FileHandler fHandler = new FileHandler("C://project//log/app.log", true);
	         fHandler.setFormatter(new SimpleFormatter());
	         log.addHandler(fHandler);

	      * LogManagerのインスタンスを生成しlogging.propertiesを読み込む
	            LogManager manager = LogManager.getLogManager();
	            manager.readConfiguration(new FileInputStream("logging.properties"));
*/
	         // ログレベルの設定
	         log.setLevel(Level.INFO);

	         //ログ出力
	         log.info("システム開始");

 //* log4j
 			inStream = new BufferedInputStream(new FileInputStream(LOGGING_PROPERTIES));
	         prop.load(inStream);




 // log4j
 			//System.out.println(prop.getProperty("log4j.appender.Appender"));

	         //log.setName(LOG_NAME);
	         //log.setLayout();
	         //log.setEncoding("UTF-8");
	         //log.setFile(prop.getProperty("log4j.appender.Appender"));
	         //log.setImmediateFlush(true);
	         //log.setAppend(true);
	         //log.setThreshold(inStream);
	         //log.activateOptions();


	         //final String value = prop.getProperty("log4j.appender.Appender2");



	         //Appenderインスタンスの生成
	        //Appender appender = new FileAppender();

			// ログレベルが WARN 以上の場合にログイベントが発生するように設定
			//log.setLevel(Level.INFO);

			//log.addAppender(appender);

			//System.out.println(value);
			//ログ開始日付
			//String yyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
			//System.setProperty("day", yyMMdd);


		 }
		catch(Throwable e){
			e.printStackTrace();
		 }
/*
 * log4j
		 finally {
	            try {
	                if (inStream != null) {
	                    inStream.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
*/
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
