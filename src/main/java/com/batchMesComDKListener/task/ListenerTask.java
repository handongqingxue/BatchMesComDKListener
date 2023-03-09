package com.batchMesComDKListener.task;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
				boolean isChecked = keepWatchTask.isChecked();//���Ѳ����̵ļ���ʶ
				System.out.println("isChecked1==="+isChecked);
				if(!isChecked) {//��û�б�������˵���м������һֱ�����У��޸ļ���ʶΪ�Ѽ��
					keepWatchTask.setChecked(true);
					System.out.println("isChecked2==="+isChecked);
					unCheckCount=0;//δ����������
				}
				else {//���м���ļ���ʶ���Ѽ�⣬˵��ֹͣ�����ˣ��͵��ۼ�δ�������������Ƿ����ֹͣ����
					unCheckCount++;
				}
				System.out.println("unCheckCount==="+unCheckCount);
				
				if(unCheckCount>3) {//δ�������ۼ��������ϣ�˵���м�����ֹͣ�����ˣ���Ҫ�ٴ������м��
					System.out.println("����.....");
					stopDKJavaRunner();//��ֹͣ�м������
					startDKJavaRunner();//�ٿ����м�����̣�����ռ���ڴ���Դ
					unCheckCount=0;//δ����������
					System.out.println("isChecked2==="+isChecked);
				}
				Thread.sleep(3000);//ÿ��������һ���м��
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ����java�м������
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
	 * ֹͣjava�м������
	 */
	private void stopDKJavaRunner() {
		keepWatchTask.stop();
		keepWatchTask.setActive(false);
		System.out.println("ddddddddddddddddd");
	}
	
	public void initJFrame() {
		//https://blog.csdn.net/wxbbbbb/article/details/118855938
		System.out.println("11111keepWatchTask==="+keepWatchTask);
		JFrame jf=new JFrame("BatchϵͳͨѶ�������");
		jf.setBounds(0, 0, 440, 510);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.show();
		
		JPanel jp=new JPanel(null);
		jp.setBackground(new Color(240, 240, 240));
		jf.add(jp);
		
		JLabel jl=new JLabel();
		jl.setBackground(new Color(165, 42, 42));
		jl.setBounds(50, 300, 100, 40);
		jl.setOpaque(true);
		jp.add(jl);
		
		JLabel jl2=new JLabel();
		jl2.setBackground(Color.GREEN);
		jl2.setBounds(50, 350, 100, 40);
		jl2.setOpaque(true);
		jp.add(jl2);
		
		jp.add(initStartJButton());
		
		jp.add(initStopJButton());
		
		Graphics g = jf.getGraphics();
		g.drawLine(0, 0, 200, 200);
	}
	
	private JButton initStartJButton() {
		startJb=new JButton("����");
		startJb.setBorder(BorderFactory.createLineBorder(new Color(191, 191, 191)));
		startJb.setBackground(new Color(253, 253, 253));
		startJb.setBounds(250, 300, 100, 30);
		startJb.addActionListener(this);
		return startJb;
	}
	
	private JButton initStopJButton() {
		stopJb=new JButton("ֹͣ");
		stopJb.setBorder(BorderFactory.createLineBorder(new Color(191, 191, 191)));
		stopJb.setBackground(new Color(253, 253, 253));
		stopJb.setBounds(250, 360, 100, 30);
		stopJb.addActionListener(this);
		return stopJb;
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
