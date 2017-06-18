package com.message;



/**
 * 
 * @author linziyu
 *客户端信息
 */
public class ClientMsg extends Msg{
	static final long serialVersionUID = 1L;
	final String msg;
	
	public ClientMsg(String msg) {
		super();
			this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
	
	
	
}
