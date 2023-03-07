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
				boolean isChecked = keepWatchTask.isChecked();//获得巡检进程的检测标识
				System.out.println("isChecked1==="+isChecked);
				if(!isChecked) {//若没有被检测过，说明中间件进程一直在运行，修改检测标识为已检测
					keepWatchTask.setChecked(true);
					System.out.println("isChecked2==="+isChecked);
					unCheckCount=0;//未检测次数清零
				}
				else {//若中间件的检测标识是已检测，说明停止运行了，就得累加未检测次数，看看是否真的停止运行
					unCheckCount++;
				}
				System.out.println("unCheckCount==="+unCheckCount);
				
				if(unCheckCount>3) {//未检测次数累计三次以上，说明中间件真的停止运行了，需要再次启动中间件
					System.out.println("复活.....");
					stopDKJavaRunner();//先停止中间件进程
					startDKJavaRunner();//再开启中间件进程，以免占用内存资源
					unCheckCount=0;//未检测次数归零
					System.out.println("isChecked2==="+isChecked);
				}
				Thread.sleep(3000);//每隔三秒检测一次中间件
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 开启java中间件进程
	 */
	private void startDKJavaRunner() {
		keepWatchTask=new KeepWatchTask();
		keepWatchTask.setActive(true);
		keepWatchTask.setChecked(true);
		keepWatchTask.start();
		System.out.println("isActive==="+keepWatchTask.isActive());
		System.out.println("bbbbbbbbbbbbbb");
	}
	
	/**
	 * 停止java中间件进程
	 */
	private void stopDKJavaRunner() {
		keepWatchTask.stop();
		keepWatchTask.setActive(false);
		System.out.println("ddddddddddddddddd");
	}
	
	public void initJFrame() {
		System.out.println("11111keepWatchTask==="+keepWatchTask);
		JFrame jf=new JFrame("监听");
		jf.setBounds(0, 0, 800, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.show();
		
		JPanel jp=new JPanel(null);
		jf.add(jp);
		
		startJb=new JButton("启动");
		startJb.setBounds(0, 0, 100, 50);
		startJb.addActionListener(this);
		jp.add(startJb);
		
		stopJb=new JButton("停止");
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
