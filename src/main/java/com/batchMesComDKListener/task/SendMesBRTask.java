package com.batchMesComDKListener.task;

import com.batchMesComDKListener.util.APIUtil;

public class SendMesBRTask extends Thread {

	/**
	 * 是否运行
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
				if(!active)//不运行了，则跳出巡回检测
					break;
				checked=false;
				Thread.sleep(5000);
				APIUtil.keepWatchOnWOFinish();
				System.out.println("巡回已完成的工单状态，发送批记录给MES........");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
