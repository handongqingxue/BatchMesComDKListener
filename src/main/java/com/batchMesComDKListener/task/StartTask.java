package com.batchMesComDKListener.task;

public class StartTask {
	
	static KeepWatchTask keepWatchTask;
	static SendMesBRTask sendMesBRTask;
	static ListenerTask listenerTask;
	
	public static void main(String[] args) {
		//´ò°üexe:https://blog.csdn.net/qq_40298902/article/details/114489753
		keepWatchTask=new KeepWatchTask();
		keepWatchTask.setActive(true);
		
		sendMesBRTask=new SendMesBRTask();
		sendMesBRTask.setActive(true);
		
		listenerTask=new ListenerTask();
		listenerTask.initMainJFrame();
		
		//keepWatchTask.start();
		sendMesBRTask.start();
		listenerTask.start();
	}
}
