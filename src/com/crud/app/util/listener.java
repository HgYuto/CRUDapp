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

         // TODO Auto-generated method stub
  		 ServletContext context = sce.getServletContext();

  		 Integer count = 0;

  		 context.setAttribute("count", count);

		System.out.println("LISTENNER通過...");

  		 LogUtil.initialized("システム起動");






  	 //properties = new Properties();
  	 //properties.getProperty(LOGGING_PROPERTIES);

  }


}
