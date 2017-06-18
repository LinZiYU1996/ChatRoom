package com.client;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import com.message.ClientMsg;
import com.util.ObjectChannelIniter;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * 
 * @author linziyu
 *客户端启动类
 */
public class Client implements ActionListener{

	static String host;
	static int port;
	static ChatRoomFrame chatRoomFrame;
	static Channel channel;
	static boolean connected;
	static Client client;
	
	public void connect() {
		 final EventLoopGroup workerGroup = new NioEventLoopGroup();
	        Bootstrap b = new Bootstrap();
	        b.group(workerGroup);
	        b.channel(NioSocketChannel.class);
	        b.option(ChannelOption.SO_KEEPALIVE, true);
	        b.handler(new ObjectChannelIniter(ClientHandler.class));
	        final ChannelFuture ch = b.connect(host, port);
	        ch.addListener(new ChannelFutureListener(){
	        	/*
	        	 * 对结果监听
	        	 */
	            @Override
	            public void operationComplete(ChannelFuture f) throws Exception {
	            	/*
	            	 * 连接服务端成功
	            	 */
	                if(f.isSuccess()){
	                    chatRoomFrame.textArea.setText(null);
	                    chatRoomFrame.entry.addActionListener(client);
	                    channel = ch.channel();
	                    connected = true;
	                }else{
	                	/*
	                	 * 连接服务端失败
	                	 */
	                    chatRoomFrame.textArea.setText("Couldn't connect to the server");
	                    connected = false;
	                    workerGroup.shutdownGracefully();
	                }
	            }
	        });
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		  if(connected){
	            String text = chatRoomFrame.entry.getText();
	            if(!text.equalsIgnoreCase("") && text != null){
	                channel.writeAndFlush(new ClientMsg(text));
	            }
	            chatRoomFrame.entry.setText(null);
	        }
	}









	public static void main(String[] args) {
			 host = "127.0.0.1";
			 port = 4455;
			 chatRoomFrame = new ChatRoomFrame();
			 client = new Client();
			client.connect(); 
	}
	
	
	
}
