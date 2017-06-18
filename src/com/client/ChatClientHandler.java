package com.client;

import com.user.User;
import com.util.Handler;

public class ChatClientHandler extends Handler{

	@Override
	public void userConnect(User user) {
		print("成功连接服务器");
	}

	@Override
	public void disconnetc(User user) {
        print("与服务器断开");
        System.exit(1);

	}

	@Override
	public void serverMsg(String user, String msg) {
		 print("<" + user + "> " + msg);
	}

	public void print(String msg) {
		System.out.println(msg);
	}
	
	
}
