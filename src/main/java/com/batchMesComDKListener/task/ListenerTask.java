package com.batchMesComDKListener.task;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.batchMesComDKListener.util.*;

public class ListenerTask extends Thread implements ActionListener {
	
	private KeepWatchTask keepWatchTask;
	private SendMesBRTask sendMesBRTask;
	private JButton saveJb,startJb,stopJb;
	private JLabel startLightJLabel,stopLightJLabel;
	private int unCheckCountKWT;
	private int unCheckCountSMBRT;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			changeLightJLabelStyle(true);
			
			keepWatchTask=StartTask.keepWatchTask;
			System.out.println("keepWatchTask==="+keepWatchTask);
			
			sendMesBRTask=StartTask.sendMesBRTask;
			System.out.println("sendMesBRTask==="+sendMesBRTask);
			
			while (true) {
				/*
				boolean isCheckedKWT = keepWatchTask.isChecked();//获得巡检进程的检测标识
				System.out.println("isCheckedKWT1==="+isCheckedKWT);
				if(!isCheckedKWT) {//若没有被检测过，说明中间件进程一直在运行，修改检测标识为已检测
					keepWatchTask.setChecked(true);
					System.out.println("isCheckedKWT2==="+isCheckedKWT);
					unCheckCountKWT=0;//未检测次数清零
				}
				else {//若中间件的检测标识是已检测，说明停止运行了，就得累加未检测次数，看看是否真的停止运行
					unCheckCountKWT++;
				}
				System.out.println("unCheckCountKWT==="+unCheckCountKWT);
				
				if(unCheckCountKWT>3) {//未检测次数累计三次以上，说明中间件真的停止运行了，需要再次启动中间件
					System.out.println("复活.....");
					stopDKJavaRunner();//先停止中间件进程
					startDKJavaRunner();//再开启中间件进程，以免占用内存资源
					unCheckCountKWT=0;//未检测次数归零
					System.out.println("isCheckedKWT2==="+isCheckedKWT);
				}
				

				boolean isCheckedSMBRT = sendMesBRTask.isChecked();
				System.out.println("isCheckedSMBRT1==="+isCheckedSMBRT);
				if(!isCheckedSMBRT) {//若没有被检测过，说明中间件进程一直在运行，修改检测标识为已检测
					sendMesBRTask.setChecked(true);
					System.out.println("isCheckedSMBRT2==="+isCheckedSMBRT);
					unCheckCountSMBRT=0;//未检测次数清零
				}
				else {//若中间件的检测标识是已检测，说明停止运行了，就得累加未检测次数，看看是否真的停止运行
					unCheckCountSMBRT++;
				}
				System.out.println("unCheckCountSMBRT==="+unCheckCountSMBRT);
				
				if(unCheckCountSMBRT>3) {//未检测次数累计三次以上，说明中间件真的停止运行了，需要再次启动中间件
					System.out.println("复活.....");
					stopDKJavaBRRunner();//先停止中间件进程
					startDKJavaBRRunner();//再开启中间件进程，以免占用内存资源
					unCheckCountSMBRT=0;//未检测次数归零
					System.out.println("isCheckedSMBRT2==="+isCheckedSMBRT);
				}
				*/
				
				
				
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
	
	private void startDKJavaBRRunner() {
		sendMesBRTask=new SendMesBRTask();
		sendMesBRTask.setActive(true);
		sendMesBRTask.setChecked(true);
		sendMesBRTask.start();
		System.out.println("isActive==="+sendMesBRTask.isActive());
		System.out.println("bbbbbbbbbbbbbb");
	}
	
	private void stopDKJavaBRRunner() {
		sendMesBRTask.stop();
		sendMesBRTask.setActive(false);
		System.out.println("ddddddddddddddddd");
	}
	
	/**
	 * 初始化主窗口
	 */
	public void initMainJFrame() {
		//https://mbd.baidu.com/ug_share/mbox/4a83aa9e65/share?product=smartapp&tk=06559b750ffdd64b94081e8e3c1fc3d6&share_url=https%3A%2F%2Fsa93g4.smartapps.baidu.com%2Fpages%2Fsquestion%2Fsquestion%3Fqid%3D562121822%26rid%3D1410602677%26_swebfr%3D1%26_swebFromHost%3Dbaiduboxapp&domain=mbd.baidu.com
		System.out.println("11111keepWatchTask==="+keepWatchTask);
		JFrame jf=new JFrame(Constant.MAIN_JFRAME_TITLE);
		jf.setBounds(Constant.MAIN_JFRAME_X, Constant.MAIN_JFRAME_Y, Constant.MAIN_JFRAME_WIDTH, Constant.MAIN_JFRAME_HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		JPanel jp=new JPanel(null);
		LineBorder lb = new LineBorder(Color.BLACK, 1, false);
		jp.setBorder(BorderFactory.createTitledBorder(lb, "欢迎进入", TitledBorder.LEFT, TitledBorder.TOP));
		jp.setBackground(new Color(240, 240, 240));
		jf.add(jp);
		*/
		jf.add(initMainJPanel());
		
		//jf.show();
		jf.setVisible(true);//一切都加载完之后才显示主窗口
	}
	
	/**
	 * 初始化主面板
	 * @return
	 */
	private JPanel initMainJPanel() {
		JPanel jp=new JPanel(null);
		jp.setSize(Constant.MAIN_JPANEL_WIDTH, Constant.MAIN_JPANEL_HEIGHT);
		jp.add(initConnectJPanel());
		jp.add(initServerJPanel());
		jp.add(initWatchStateMsgJLabel());
		return jp;
	}
	
	/**
	 * 初始化连接区域子面板
	 * @return
	 */
	private JPanel initConnectJPanel() {
		JPanel jp=new JPanel(null);
		LineBorder lb=new LineBorder(new Color(Constant.CONNECT_JPANEL_BORDER_COLOR_R,Constant.CONNECT_JPANEL_BORDER_COLOR_G,Constant.CONNECT_JPANEL_BORDER_COLOR_B), 1, false);
		jp.setBorder(BorderFactory.createTitledBorder(lb, Constant.CONNECT_JPANEL_BORDER_TITLE, TitledBorder.LEFT, TitledBorder.TOP));
		jp.setBounds(Constant.CONNECT_JPANEL_X, Constant.CONNECT_JPANEL_Y, Constant.CONNECT_JPANEL_WIDTH, Constant.CONNECT_JPANEL_HEIGHT);
		
		jp.add(initIPAddressPortJLabel());
		jp.add(initIPAddressPortJTextField());
		
		jp.add(initDBConnectJLabel());
		jp.add(initDBConnectJTextField());
		
		jp.add(initSaveJButton());
		
		return jp;
	}
	
	/**
	 * 初始化ip地址和端口号标签
	 * @return
	 */
	private JLabel initIPAddressPortJLabel() {
		JLabel jl=new JLabel();
		jl.setText(Constant.IP_ADDRESS_PORT_JLABEL_TEXT);
		jl.setBounds(Constant.IP_ADDRESS_PORT_JLABEL_X, Constant.IP_ADDRESS_PORT_JLABEL_Y, Constant.IP_ADDRESS_PORT_JLABEL_WIDTH, Constant.IP_ADDRESS_PORT_JLABEL_HEIGHT);
		//jl.setBackground(Color.RED);
		//jl.setOpaque(true);
		
		return jl;
	}
	
	/**
	 * 初始化ip地址和端口号文本框
	 * @return
	 */
	private JTextField initIPAddressPortJTextField() {
		//JTextField显示不了解决方案:https://www.zzzyk.com/show/a99aeecdee6fb83a.htm
		JTextField jtf=new JTextField();
		jtf.setBounds(Constant.IP_ADDRESS_PORT_JTEXTFIELD_X, Constant.IP_ADDRESS_PORT_JTEXTFIELD_Y, Constant.IP_ADDRESS_PORT_JTEXTFIELD_WIDTH, Constant.IP_ADDRESS_PORT_JTEXTFIELD_HEIGHT);
		
		return jtf;
	}
	
	/**
	 * 初始化数据库连接标签
	 * @return
	 */
	private JLabel initDBConnectJLabel() {
		JLabel jl=new JLabel();
		jl.setText(Constant.DB_CONNECT_JLABEL_TEXT);
		jl.setBounds(Constant.DB_CONNECT_JLABEL_X, Constant.DB_CONNECT_JLABEL_Y, Constant.DB_CONNECT_JLABEL_WIDTH, Constant.DB_CONNECT_JLABEL_HEIGHT);
		//jl.setBackground(Color.RED);
		//jl.setOpaque(true);
		
		return jl;
	}
	
	/**
	 * 初始化数据库连接文本框
	 * @return
	 */
	private JTextField initDBConnectJTextField() {
		JTextField jtf=new JTextField();
		jtf.setBounds(Constant.DB_CONNECT_JTEXTFIELD_X, Constant.DB_CONNECT_JTEXTFIELD_Y, Constant.DB_CONNECT_JTEXTFIELD_WIDTH, Constant.DB_CONNECT_JTEXTFIELD_HEIGHT);
		
		return jtf;
	}
	
	/**
	 * 初始化保存按钮
	 * @return
	 */
	private JButton initSaveJButton() {
		saveJb=new JButton(Constant.SAVE_JBUTTON_TEXT);
		saveJb.setBorder(BorderFactory.createLineBorder(new Color(Constant.SAVE_JBUTTON_BORDER_COLOR_R, Constant.SAVE_JBUTTON_BORDER_COLOR_G, Constant.SAVE_JBUTTON_BORDER_COLOR_B)));
		saveJb.setBackground(new Color(Constant.SAVE_JBUTTON_BG_COLOR_R, Constant.SAVE_JBUTTON_BG_COLOR_R, Constant.SAVE_JBUTTON_BG_COLOR_R));
		saveJb.setBounds(253, 130, 120, 30);
		saveJb.addActionListener(this);
		return saveJb;
	}
	
	/**
	 * 初始化服务区域子面板
	 * @return
	 */
	private JPanel initServerJPanel() {
		JPanel jp=new JPanel(null);
		LineBorder lb=new LineBorder(new Color(222,222,222), 1, false);
		jp.setBorder(BorderFactory.createTitledBorder(lb, "服务", TitledBorder.LEFT, TitledBorder.TOP));
		jp.setBounds(10, 250, 400, 180);
		//jp.setBackground(Color.RED);
		jp.add(initLightJPanel());
		jp.add(initStartJButton());
		jp.add(initStopJButton());
		
		return jp;
	}
	
	/**
	 * 初始化指示灯面板
	 * @return
	 */
	private JPanel initLightJPanel() {
		JPanel jp=new JPanel(null);
		jp.setBorder(BorderFactory.createLineBorder(new Color(191, 191, 191)));
		//jp.setBackground(Color.RED);
		jp.setBounds(70, 40, 90, 120);
		
		jp.add(initStartLightJLabel());
		jp.add(initStopLightJLabel());
		
		return jp;
	}
	
	/**
	 * 初始化开始指示灯标签
	 * @return
	 */
	private JLabel initStartLightJLabel() {
		startLightJLabel=new JLabel();
		startLightJLabel.setBounds(10, 10, 70, 40);
		startLightJLabel.setOpaque(true);

		return startLightJLabel;
	}
	
	/**
	 * 初始化结束指示灯标签
	 * @return
	 */
	private JLabel initStopLightJLabel() {
		stopLightJLabel=new JLabel();
		stopLightJLabel.setBounds(10, 65, 70, 40);
		stopLightJLabel.setOpaque(true);

		return stopLightJLabel;
	}
	
	/**
	 * 改变指示灯标签样式
	 * @param start
	 */
	private void changeLightJLabelStyle(boolean start) {
		if(start) {
			startLightJLabel.setBackground(new Color(165, 42, 42));
			stopLightJLabel.setBackground(new Color(38, 139, 11));
			
			startJb.setEnabled(false);
			stopJb.setEnabled(true);
		}
		else {
			startLightJLabel.setBackground(Color.RED);
			stopLightJLabel.setBackground(Color.GREEN);
			
			startJb.setEnabled(true);
			stopJb.setEnabled(false);
		}
	}
	
	private JButton initStartJButton() {
		startJb=new JButton("启动");
		startJb.setBorder(BorderFactory.createLineBorder(new Color(191, 191, 191)));
		startJb.setBackground(new Color(253, 253, 253));
		startJb.setBounds(253, 55, 120, 30);
		startJb.addActionListener(this);
		return startJb;
	}
	
	private JButton initStopJButton() {
		stopJb=new JButton("停止");
		stopJb.setBorder(BorderFactory.createLineBorder(new Color(191, 191, 191)));
		stopJb.setBackground(new Color(253, 253, 253));
		stopJb.setBounds(253, 110, 120, 30);
		stopJb.addActionListener(this);
		return stopJb;
	}
	
	private JLabel initWatchStateMsgJLabel() {
		JLabel jl=new JLabel();
		jl.setText("WATCH DOG自侦测功能运行中 心跳变量正常");
		jl.setBounds(135, 430, 250, 30);
		//jl.setBackground(Color.RED);
		//jl.setOpaque(true);
		
		return jl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton)e.getSource();
		if(source==startJb) {
			System.out.println("aaaaaaaaaaaaaa");
			//startDKJavaRunner();
			//startDKJavaBRRunner();
			changeLightJLabelStyle(true);
		}
		else if(source==stopJb) {
			System.out.println("bbbbbbbbbbbbb");
			//stopDKJavaRunner();
			//stopDKJavaBRRunner();
			changeLightJLabelStyle(false);
		}
	}

}
