package com.user;

import java.util.ArrayList;
import java.util.List;

import com.message.Msg;
import com.message.ServerMsg;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author linziyu
 *连接进来的每个用户以及信息的处理
 */
public class User {
	
	static final List<User> users;//用户列表
	 final Channel channel;
	String username = null; //用户名字
	static {
		users = new ArrayList<>();
	}
	
	
	public static User getInstance(ChannelHandlerContext ctx) {
		return User.getInstance(ctx.channel());
		
	}
	
	/*
	 * 创建channel对应的用户
	 */
	public static User getInstance(Channel channel) {
		User user = getUser(channel.remoteAddress().toString());
		if (user==null) {
			user = new User(channel);
			users.add(user);
			return user;
		} else {
			return user;
		}
		
	}
	
	public User(Channel channel) {
			this.channel = channel;
	}
	
	public Channel channel() {
		return channel;
	}
	
	public void writeMsg(Msg msg) {
		channel.writeAndFlush(msg);
	}
	
	public void writeChat(User user,String msg) {
		String name;
		if (user.getUsername()==null) {
			name = user.getAddress().toString();
		} else {
			name = user.getUsername();
		}
		writeChat(name, msg);
	}
	
	/*
	 * 把服务端信息发送到客户端
	 */
	public void writeChat(String user,String msg) {
		channel.writeAndFlush(new ServerMsg(user, msg));
	}
	
	public void writeServerMsg(String msg) {
			writeChat("Server", msg);
	}
	
	
	
	/*
	 * 当用户断开连接后从用户列表中移除并且关闭通道
	 */
	public void disconnect() {
		users.remove(this);
		channel.close();
	}
	
	/**
	 * 获取通道地址并且以字符串形式返回
	 * @return
	 */
	public String getAddress() {
		return channel.remoteAddress().toString();
	}
	
	/*
	 * 由地址参数返回对应的用户
	 */
	public static User getUser(String address) {
			for(int i=0;i<users.size();i++) {
				if (users.get(i).getAddress().equalsIgnoreCase(address)) {
					return users.get(i);
				}
			}
		return null;
	}
	/*
	 * 有通道channel返回对应用户
	 */
	public static User getUser(Channel channel) {
		return getUser(channel.remoteAddress().toString());
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * 检查新的用户名是否重复
	 * @param username
	 * @return
	 */
	public static boolean usernameTaken(String username) {
		for(int i=0;i<users.size();i++) {
			if (users.get(i).getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 查看用户是否存在
	 * @param address
	 * @return
	 */
	public static boolean userExits(String address) {
		if (getUser(address)!=null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
}
