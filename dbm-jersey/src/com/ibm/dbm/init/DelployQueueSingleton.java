package com.ibm.dbm.init;

import java.util.Vector;

public class DelployQueueSingleton {
	private static DelployQueueSingleton dqs = new DelployQueueSingleton();
	//private Queue q = new ConcurrentLinkedQueue();
	
	private Vector<ShellBean> q = new Vector<ShellBean>();
	
	private static int deployingNumber = 0;
	
	private volatile boolean threadStopFlag;
	
	private DelployQueueSingleton(){
	}
	
	public static DelployQueueSingleton getInstance(){
		return dqs;
	}
	
	private volatile Thread lThread;
	public void init(){
		lThread = new Thread(new Listener());
		lThread.start();
	}
	
	public void destroy(){
		threadStopFlag = true;
		if(lThread != null){
			lThread.interrupt();
		}
	}
	
	public void add(ShellBean sb){
		synchronized(q){
			q.addElement(sb);
			q.size();
		}
	}
	
	public ShellBean poll(){
		ShellBean sb = null;
		synchronized(q) {
			if(q.size() >= 1){
				sb = (ShellBean)q.elementAt(0);
				q.remove(0);
			}
		}
		return sb;
	}
	
	private class Listener implements Runnable{
		public void run() {
			threadStopFlag = false;
			System.out.println("------------Deploy Queue Singleton thread start--------------");
			while(!threadStopFlag){
				if(deployingNumber == 0){
					ShellBean shellBean = poll();
					if(shellBean != null){
						deployingCount(1);
						String command   = shellBean.getCommand();
						Operation op      = shellBean.getOperation();
						
						ShellThread st = new ShellThread(command, op);
						st.start();
					}
				}
				
				try {
					Thread.sleep(1000*10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("------------Deploy Queue Singleton thread stop------------");
		}
	}
	
	public static synchronized void deployingCount(int number){
		deployingNumber = deployingNumber + number;
	}
	
	public int getDeployingNumber(){
		return deployingNumber;
	}
	
	public int getDelployQueueSize(){
		return q.size();
	}
}
