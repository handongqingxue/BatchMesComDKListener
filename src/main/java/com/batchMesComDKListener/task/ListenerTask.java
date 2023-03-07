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
		System.out.println("11111keepWatchTask==="+keepWatchTask);
		JFrame jf=new JFrame("����");
		jf.setBounds(0, 0, 800, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.show();
		
		JPanel jp=new JPanel(null);
		jf.add(jp);
		
		startJb=new JButton("����");
		startJb.setBounds(0, 0, 100, 50);
		startJb.addActionListener(this);
		jp.add(startJb);
		
		stopJb=new JButton("ֹͣ");
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
