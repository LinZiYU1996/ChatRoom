package com.server;


import com.util.ObjectChannelIniter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * 
 * @author linziyu
 *服务端启动类
 */
public class Server {
	 public static void main(String[] args) throws Exception {
	        int port = 4455;
	      
	        EventLoopGroup bossGroup = new NioEventLoopGroup();
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup)
	             .channel(NioServerSocketChannel.class)
	             .childHandler(new ObjectChannelIniter(ServerHandler.class))
	             .option(ChannelOption.SO_BACKLOG, 128)
	             .childOption(ChannelOption.SO_KEEPALIVE, true);
	            ChannelFuture f = b.bind(port).sync();
	            System.out.println("ChatServer.main()");
	            f.channel().closeFuture().sync();
	        } finally {
	            workerGroup.shutdownGracefully();
	            bossGroup.shutdownGracefully();
	        }
	    }
}
