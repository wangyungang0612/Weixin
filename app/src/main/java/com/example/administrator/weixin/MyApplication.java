package com.example.administrator.weixin;

import android.content.Intent;


import com.example.administrator.weixin.weixin.ContactBean;

import java.util.List;

public class MyApplication extends android.app.Application {

	
	private List<ContactBean> contactBeanList;
	
	public List<ContactBean> getContactBeanList() {
		return contactBeanList;
	}
	public void setContactBeanList(List<ContactBean> contactBeanList) {
		this.contactBeanList = contactBeanList;
	}

	public void onCreate() {
		
		System.out.println("项目启动");
		
		Intent startService = new Intent(MyApplication.this, T9Service.class);
		startService(startService);
	}
}
