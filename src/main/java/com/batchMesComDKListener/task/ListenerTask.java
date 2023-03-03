package com.batchMesComDKListener.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ListenerTask extends Thread implements ActionListener {
	
	private KeepWatchTask keepWatchTask;
	private JButton startJb,stopJb;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		keepWatchTask=StartTask.keepWatchTask;
		System.out.println("keepWatchTask==="+keepWatchTask);
		
	}
	
	private void startDKJavaRunner() {
		keepWatchTask=new KeepWatchTask();
		keepWatchTask.setActive(true);
		keepWatchTask.start();
		System.out.println("isActive==="+keepWatchTask.isActive());
		System.out.println("bbbbbbbbbbbbbb");
	}
	
	private void stopDKJavaRunner() {
		keepWatchTask.stop();
		keepWatchTask.setActive(false);
		System.out.println("ddddddddddddddddd");
	}
	
	public void initJFrame() {
		System.out.println("11111keepWatchTask==="+keepWatchTask);
		JFrame jf=new JFrame("¼àÌý");
		jf.setBounds(0, 0, 800, 600);
		jf.show();
		
		JPanel jp=new JPanel(null);
		jf.add(jp);
		
		startJb=new JButton("Æô¶¯");
		startJb.setBounds(0, 0, 100, 50);
		startJb.addActionListener(this);
		jp.add(startJb);
		
		stopJb=new JButton("Í£Ö¹");
		stopJb.setBounds(0, 100, 100, 50);
		stopJb.addActionListener(this);
		jp.add(stopJb);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton)e.getSource();
		if(source==startJb) {
			System.out.println("aaaaaaaaaaaaaa");
			startDKJavaRunner();
		}
		else if(source==stopJb) {
			System.out.println("bbbbbbbbbbbbb");
			stopDKJavaRunner();
		}
	}

}
