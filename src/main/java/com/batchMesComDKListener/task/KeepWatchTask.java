package com.batchMesComDKListener.task;

import com.batchMesComDKListener.util.*;

public class KeepWatchTask extends Thread {
	
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
				if(!active)
					break;
				checked=false;
				Thread.sleep(3000);
				APIUtil.keepWatchOnWorkOrderTest();
				System.out.println("Ñ²»Ø¹¤µ¥×´Ì¬........");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
