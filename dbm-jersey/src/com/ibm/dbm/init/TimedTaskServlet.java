package com.ibm.dbm.init;

import javax.servlet.http.HttpServlet;

public class TimedTaskServlet extends HttpServlet {

	private static final long serialVersionUID = -727239205236717319L;
	
	public void init(){
		DelployQueueSingleton.getInstance().init();
	}
	
	public void destroy(){
		System.out.println("--------------before deploy thread cancel----------------");
		DelployQueueSingleton.getInstance().destroy();
		System.out.println("--------------after deploy thread cancel----------------");
	}
}
