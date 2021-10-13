package com.crud.app.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class listener
 *
 */
@WebListener
public class listener implements ServletContextListener {

	//ログ設定プロパティファイルのファイル名
	// protected static final String LOGGING_PROPERTIES= "C://project//workspace//CRUDapp/WebContent/WEB-INF/log4j.properties";
	//static protected Logger log = Logger.getLogger( LogUtil.class );

    /**
     * Default constructor.
     */
    public listener() {
        // TODO Auto-generated constructor stub
    }

  	/**
       * @see ServletContextListener#contextDestroyed(ServletContextEvent)
       */
      public void contextDestroyed(ServletContextEvent sce)  {
           // TODO Auto-generated method stub
      	System.out.println("destroy()の実行");
      }

  	/**
       * @see ServletContextListener#contextInitialized(ServletContextEvent)
       */
      public void contextInitialized(ServletContextEvent sce)  {

         try {
    	 // TODO Auto-generated method stub
  		 ServletContext context = sce.getServletContext();

  		 Integer count = 0;

  		 context.setAttribute("count", count);

		System.out.println("LISTENNER通過...");

		//LogUtil.initialized();
		//properties = new Properties();
		//properties.getProperty(LOGGING_PROPERTIES);

         } catch(Exception e) {
             e.printStackTrace();
         }
      }
}
