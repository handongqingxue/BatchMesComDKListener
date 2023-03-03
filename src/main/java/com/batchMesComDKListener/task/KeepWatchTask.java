package com.batchMesComDKListener.task;

import com.batchMesComDKListener.util.*;

public class KeepWatchTask extends Thread {
	
	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				if(!active)
					break;
				Thread.sleep(3000);
				//APIUtil.keepWatchOnWorkOrderTest();
				System.out.println("Ñ²»Ø¹¤µ¥×´Ì¬........");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
