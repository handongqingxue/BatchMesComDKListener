package com.batchMesComDKListener.task;

import com.batchMesComDKListener.util.APIUtil;

public class SendMesBRTask extends Thread {

	/**
	 * �Ƿ�����
	 */
	private boolean active;
	private boolean checked;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				if(!active)//�������ˣ�������Ѳ�ؼ��
					break;
				checked=false;
				Thread.sleep(5000);
				APIUtil.keepWatchOnWOFinish();
				System.out.println("Ѳ������ɵĹ���״̬����������¼��MES........");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
