package com.batchMesComDKListener.task;

public class StartTask {
	
	static KeepWatchTask keepWatchTask;
	static ListenerTask listenerTask;
	
	public static void main(String[] args) {
		//´ò°üexe:https://blog.csdn.net/qq_40298902/article/details/114489753
		keepWatchTask=new KeepWatchTask();
		keepWatchTask.setActive(true);
		listenerTask=new ListenerTask();
		listenerTask.initJFrame();
		
		keepWatchTask.start();
		listenerTask.start();
	}
}
