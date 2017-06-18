package com.server;

import java.util.ArrayList;

import java.util.List;

import com.user.User;
import com.util.Handler;

public class ServerHandler extends Handler{
	 static final List<User> users = new ArrayList<User>();//创建用户列表
	 	
	 
	 
	 /**
	  * 处理新的用户连接进来
	  */
	@Override
	public void userConnect(com.user.User user) {
		System.out.println("服务器处理新的用户"+"----ServerHandler");
		user.writeChat("Server", "Please enter a username:");//提示用户输入具体名字
	}
	
	
	/**
	 * 处理用户断开连接
	 */
	@Override
	public void disconnetc(com.user.User user) {
		if(users.contains(user)){
            users.remove(user);
            user.disconnect();
            broadcastMessage(user.getUsername() + " left");
        }else{
            System.out.println("User not in users");
        }
	}
	
	/**
	 * 处理客户端传递过来的信息
	 */
	@Override
	public void clientMsg(com.user.User user, String msg) {
		System.out.println("处理客户端的用户名字"+"----ServerHandler");
		  if(user.getUsername()==null){
	            if(msg.contains(" ")){
	                user.writeServerMsg("Usernames can't contain spaces!");
	            }else if(msg == null || msg.length()==0 || msg.length()>11){
	                user.writeServerMsg("Usernames must be less than 11 characters long!");
	            }else{
	                user.setUsername(msg);
	                users.add(user);
	                broadcastMessage(user.getUsername() + " joined");//广播有新的用户加入
	            }
	        }else{
	            broadcastChatMessage(user, msg);//用户已经存在，广播该用户发表的内容
	        }
	}

	/*
	 * 向其他用户广播有新的别的客户端发出的信息
	 */
	 public void broadcastChatMessage(User user, String msg){
	        for(User u : users){
	            u.writeChat(user, msg);
	        }
	    }
	    /*
	     * 向其他用户关闭来自服务端的信息
	     */
	    public void broadcastMessage(String msg){
	        for(User u : users){
	            u.writeServerMsg(msg);
	        }
	    }
	 
	
	
	
}
