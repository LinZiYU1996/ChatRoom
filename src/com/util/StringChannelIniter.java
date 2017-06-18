package com.util;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class StringChannelIniter extends ChannelInitializer<SocketChannel>{

	Class<? extends ChannelInboundHandlerAdapter> handler;
	public StringChannelIniter(Class<? extends ChannelInboundHandlerAdapter> handler) {
		super();
		 this.handler = handler;
	}
	
	
	
	
	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		  arg0.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
	        arg0.pipeline().addLast("decoder", new StringDecoder());
	        arg0.pipeline().addLast("encoder", new StringEncoder());
	        arg0.pipeline().addLast(handler.newInstance());
		
		
	}
		
	
	
	
}
