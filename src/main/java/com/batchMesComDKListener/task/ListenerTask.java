package com.batchMesComDKListener.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ListenerTask extends Thread implements ActionListener {
	
	private KeepWatchTask keepWatchTask;
	private JButton startJb,stopJb;
	private int unCheckCount;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			keepWatchTask=StartTask.keepWatchTask;
			System.out.println("keepWatchTask==="+keepWatchTask);
			while (true) {
				boolean isChecked = keepWatchTask.isChecked();
				System.out.println("isChecked1==="+isChecked);
				if(!isChecked) {
					keepWatchTask.setChecked(true);
					System.out.println("isChecked2==="+isChecked);
					unCheckCount=0;
				}
				else {
					unCheckCount++;
				}
				System.out.println("unCheckCount==="+unCheckCount);
				
				if(unCheckCount>3) {
					System.out.println("¸´»î.....");
					stopDKJavaRunner();
					startDKJavaRunner();
					unCheckCount=0;
					System.out.println("isChecked2==="+isChecked);
				}
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void startDKJavaRunner() {
		keepWatchTask=new KeepWatchTask();
		keepWatchTask.setActive(true);
		keepWatchTask.setChecked(true);
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
