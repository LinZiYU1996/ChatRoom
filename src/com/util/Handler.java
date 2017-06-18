package com.util;

import com.message.ClientMsg;
import com.message.Msg;
import com.message.ServerMsg;
import com.user.User;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;


/*
 * 为连接进来的客户端创建具体对象以及对下线，上线的处理
 */
public class Handler extends ChannelInboundHandlerAdapter{
	
	
	/**
	 * 当有新的客户端连接时，为该客户端创建对应的User对象
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("为新的连接创建对应用户"+"-----handler");
		userConnect(User.getInstance(ctx));
	}

	
	/**
	 * 用户下线对应的后续操作
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		disconnetc(User.getInstance(ctx));
	}
	
	/**
	 * 对信息进行分类处理
	 */
	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
		
				if (msg instanceof Msg) {
					Msg  message = (Msg) msg;
						if (message instanceof ClientMsg) {
							System.out.println("对客户端的信息进行处理"+"handler");
							ClientMsg cMsg = (ClientMsg) message;
							clientMsg(User.getInstance(ctx),cMsg.getMsg() );
						} else if (message instanceof ServerMsg) {
							System.out.println("对服务端信息进行处理"+"handler");
							ServerMsg sMsg = (ServerMsg) message;
							serverMsg(sMsg.getUser(),sMsg.getMsg());
						}
				} else {
					System.err.println();
				}
	}

	
	/**
	 * 错误处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
	}
		/*
		 * 用户连接
		 */
	public void userConnect(User user) { }
	
	/*
	 * 用户断开连接
	 */
	public void disconnetc(User user) {}
	
	/**
	 * 处理客户端信息	
	 */
	public void clientMsg(User user,String msg) {}
	
	/**
	 * 处理服务端信息
	 */
	public void serverMsg(String user,String msg) {}
		
	
	
	
	
}
