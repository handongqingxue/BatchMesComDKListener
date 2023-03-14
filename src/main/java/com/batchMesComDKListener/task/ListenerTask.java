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

public class ListenerTask extends Thread implements ActionListener {
	
	private KeepWatchTask keepWatchTask;
	private SendMesBRTask sendMesBRTask;
	private JButton saveJb,startJb,stopJb;
	private int unCheckCountKWT;
	private int unCheckCountSMBRT;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			keepWatchTask=StartTask.keepWatchTask;
			System.out.println("keepWatchTask==="+keepWatchTask);
			
			sendMesBRTask=StartTask.sendMesBRTask;
			System.out.println("sendMesBRTask==="+sendMesBRTask);
			
			while (true) {
				boolean isCheckedKWT = keepWatchTask.isChecked();//���Ѳ����̵ļ���ʶ
				System.out.println("isCheckedKWT1==="+isCheckedKWT);
				if(!isCheckedKWT) {//��û�б�������˵���м������һֱ�����У��޸ļ���ʶΪ�Ѽ��
					keepWatchTask.setChecked(true);
					System.out.println("isCheckedKWT2==="+isCheckedKWT);
					unCheckCountKWT=0;//δ����������
				}
				else {//���м���ļ���ʶ���Ѽ�⣬˵��ֹͣ�����ˣ��͵��ۼ�δ�������������Ƿ����ֹͣ����
					unCheckCountKWT++;
				}
				System.out.println("unCheckCountKWT==="+unCheckCountKWT);
				
				if(unCheckCountKWT>3) {//δ�������ۼ��������ϣ�˵���м�����ֹͣ�����ˣ���Ҫ�ٴ������м��
					System.out.println("����.....");
					stopDKJavaRunner();//��ֹͣ�м������
					startDKJavaRunner();//�ٿ����м�����̣�����ռ���ڴ���Դ
					unCheckCountKWT=0;//δ����������
					System.out.println("isCheckedKWT2==="+isCheckedKWT);
				}
				

				/*
				boolean isCheckedSMBRT = sendMesBRTask.isChecked();
				System.out.println("isCheckedSMBRT1==="+isCheckedSMBRT);
				if(!isCheckedSMBRT) {//��û�б�������˵���м������һֱ�����У��޸ļ���ʶΪ�Ѽ��
					sendMesBRTask.setChecked(true);
					System.out.println("isCheckedSMBRT2==="+isCheckedSMBRT);
					unCheckCountSMBRT=0;//δ����������
				}
				else {//���м���ļ���ʶ���Ѽ�⣬˵��ֹͣ�����ˣ��͵��ۼ�δ�������������Ƿ����ֹͣ����
					unCheckCountSMBRT++;
				}
				System.out.println("unCheckCountSMBRT==="+unCheckCountSMBRT);
				
				if(unCheckCountSMBRT>3) {//δ�������ۼ��������ϣ�˵���м�����ֹͣ�����ˣ���Ҫ�ٴ������м��
					System.out.println("����.....");
					stopDKJavaBRRunner();//��ֹͣ�м������
					startDKJavaBRRunner();//�ٿ����м�����̣�����ռ���ڴ���Դ
					unCheckCountSMBRT=0;//δ����������
					System.out.println("isCheckedSMBRT2==="+isCheckedSMBRT);
				}
				*/
				
				
				
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
	 * ��ʼ��������
	 */
	public void initMainJFrame() {
		//https://mbd.baidu.com/ug_share/mbox/4a83aa9e65/share?product=smartapp&tk=06559b750ffdd64b94081e8e3c1fc3d6&share_url=https%3A%2F%2Fsa93g4.smartapps.baidu.com%2Fpages%2Fsquestion%2Fsquestion%3Fqid%3D562121822%26rid%3D1410602677%26_swebfr%3D1%26_swebFromHost%3Dbaiduboxapp&domain=mbd.baidu.com
		System.out.println("11111keepWatchTask==="+keepWatchTask);
		JFrame jf=new JFrame("BatchϵͳͨѶ�������");
		jf.setBounds(0, 0, 440, 510);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		JPanel jp=new JPanel(null);
		LineBorder lb = new LineBorder(Color.BLACK, 1, false);
		jp.setBorder(BorderFactory.createTitledBorder(lb, "��ӭ����", TitledBorder.LEFT, TitledBorder.TOP));
		jp.setBackground(new Color(240, 240, 240));
		jf.add(jp);
		*/
		jf.add(initMainJPanel());
		
		//jf.show();
		jf.setVisible(true);//һ�ж�������֮�����ʾ������
	}
	
	/**
	 * ��ʼ�������
	 * @return
	 */
	private JPanel initMainJPanel() {
		JPanel jp=new JPanel(null);
		jp.setSize(400, 510);
		jp.add(initConnectJPanel());
		jp.add(initServerJPanel());
		jp.add(initWatchStateMsgJLabel());
		return jp;
	}
	
	/**
	 * ��ʼ���������������
	 * @return
	 */
	private JPanel initConnectJPanel() {
		JPanel jp=new JPanel(null);
		LineBorder lb=new LineBorder(new Color(222,222,222), 1, false);
		jp.setBorder(BorderFactory.createTitledBorder(lb, "����", TitledBorder.LEFT, TitledBorder.TOP));
		jp.setBounds(10, 30, 400, 180);
		
		jp.add(initIPAddressPortJLabel());
		jp.add(initIPAddressPortJTextField());
		
		jp.add(initDBConnectJLabel());
		jp.add(initDBConnectJTextField());
		
		jp.add(initSaveJButton());
		
		return jp;
	}
	
	/**
	 * ��ʼ��ip��ַ�Ͷ˿ںű�ǩ
	 * @return
	 */
	private JLabel initIPAddressPortJLabel() {
		JLabel jl=new JLabel();
		jl.setText("IP��ַ�˿�");
		jl.setBounds(35, 30, 70, 30);
		jl.setBackground(Color.RED);
		jl.setOpaque(true);
		
		return jl;
	}
	
	/**
	 * ��ʼ��ip��ַ�Ͷ˿ں��ı���
	 * @return
	 */
	private JTextField initIPAddressPortJTextField() {
		//JTextField��ʾ���˽������:https://www.zzzyk.com/show/a99aeecdee6fb83a.htm
		JTextField jtf=new JTextField();
		jtf.setBounds(135, 30, 240, 30);
		
		return jtf;
	}
	
	/**
	 * ��ʼ�����ݿ����ӱ�ǩ
	 * @return
	 */
	private JLabel initDBConnectJLabel() {
		JLabel jl=new JLabel();
		jl.setText("���ݿ�����");
		jl.setBounds(35, 80, 70, 30);
		jl.setBackground(Color.RED);
		jl.setOpaque(true);
		
		return jl;
	}
	
	/**
	 * ��ʼ�����ݿ������ı���
	 * @return
	 */
	private JTextField initDBConnectJTextField() {
		JTextField jtf=new JTextField();
		jtf.setBounds(135, 80, 240, 30);
		
		return jtf;
	}
	
	/**
	 * ��ʼ�����水ť
	 * @return
	 */
	private JButton initSaveJButton() {
		saveJb=new JButton("����");
		saveJb.setBorder(BorderFactory.createLineBorder(new Color(191, 191, 191)));
		saveJb.setBackground(new Color(253, 253, 253));
		saveJb.setBounds(253, 130, 120, 30);
		saveJb.addActionListener(this);
		return saveJb;
	}
	
	/**
	 * ��ʼ���������������
	 * @return
	 */
	private JPanel initServerJPanel() {
		JPanel jp=new JPanel(null);
		LineBorder lb=new LineBorder(new Color(222,222,222), 1, false);
		jp.setBorder(BorderFactory.createTitledBorder(lb, "����", TitledBorder.LEFT, TitledBorder.TOP));
		jp.setBounds(10, 250, 400, 180);
		//jp.setBackground(Color.RED);
		jp.add(initLightJPanel());
		jp.add(initStartJButton());
		jp.add(initStopJButton());
		
		return jp;
	}
	
	/**
	 * ��ʼ��ָʾ�����
	 * @return
	 */
	private JPanel initLightJPanel() {
		JPanel jp=new JPanel(null);
		jp.setBorder(BorderFactory.createLineBorder(new Color(191, 191, 191)));
		//jp.setBackground(Color.RED);
		jp.setBounds(70, 40, 100, 120);
		
		jp.add(initStartLightJLabel());
		jp.add(initStopLightJLabel());
		
		return jp;
	}
	
	/**
	 * ��ʼ����ʼָʾ�Ʊ�ǩ
	 * @return
	 */
	private JLabel initStartLightJLabel() {
		JLabel jl=new JLabel();
		jl.setBackground(new Color(165, 42, 42));
		jl.setBounds(10, 10, 60, 40);
		jl.setOpaque(true);

		return jl;
	}
	
	/**
	 * ��ʼ������ָʾ�Ʊ�ǩ
	 * @return
	 */
	private JLabel initStopLightJLabel() {
		JLabel jl2=new JLabel();
		jl2.setBackground(Color.GREEN);
		jl2.setBounds(10, 60, 60, 40);
		jl2.setOpaque(true);

		return jl2;
	}
	
	private JButton initStartJButton() {
		startJb=new JButton("����");
		startJb.setBorder(BorderFactory.createLineBorder(new Color(191, 191, 191)));
		startJb.setBackground(new Color(253, 253, 253));
		startJb.setBounds(253, 60, 120, 30);
		startJb.addActionListener(this);
		return startJb;
	}
	
	private JButton initStopJButton() {
		stopJb=new JButton("ֹͣ");
		stopJb.setBorder(BorderFactory.createLineBorder(new Color(191, 191, 191)));
		stopJb.setBackground(new Color(253, 253, 253));
		stopJb.setBounds(253, 100, 120, 30);
		stopJb.addActionListener(this);
		return stopJb;
	}
	
	private JLabel initWatchStateMsgJLabel() {
		JLabel jl=new JLabel();
		jl.setText("Watch Dog����⹦�������� ������������");
		jl.setBounds(135, 430, 250, 30);
		jl.setBackground(Color.RED);
		jl.setOpaque(true);
		
		return jl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton source = (JButton)e.getSource();
		if(source==startJb) {
			System.out.println("aaaaaaaaaaaaaa");
			startDKJavaRunner();
			startDKJavaBRRunner();
		}
		else if(source==stopJb) {
			System.out.println("bbbbbbbbbbbbb");
			stopDKJavaRunner();
			stopDKJavaBRRunner();
		}
	}

}
