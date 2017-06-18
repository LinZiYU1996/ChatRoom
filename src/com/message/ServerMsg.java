package com.message;


/**
 * 
 * @author linziyu
 *服务端信息
 */
public class ServerMsg extends Msg{
	static final long serialVersionUID = 1L;
	final String user;
	final String msg;
	
	public ServerMsg(String user,String msg) {
			super();
			this.msg = msg;
			this.user = user;
	}

	public String getUser() {
		return user;
	}

	public String getMsg() {
		return msg;
	}
	
	
	
}
