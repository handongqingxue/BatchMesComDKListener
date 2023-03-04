package com.batchMesComDKListener.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartTask {
	
	static KeepWatchTask keepWatchTask;
	static ListenerTask listenerTask;
	
	public static void main(String[] args) {
		//���exe:https://blog.csdn.net/qq_40298902/article/details/114489753
		keepWatchTask=new KeepWatchTask();
		keepWatchTask.setActive(true);
		listenerTask=new ListenerTask();
		listenerTask.initJFrame();
		
		keepWatchTask.start();
		listenerTask.start();
	}
}