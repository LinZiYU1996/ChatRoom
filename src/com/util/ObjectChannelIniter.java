package com.util;


import com.message.Msg;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectChannelIniter extends ChannelInitializer<SocketChannel>{
	Class<? extends Handler> handler;
	public ObjectChannelIniter(Class<? extends Handler> handler) {
			super();
			this.handler = handler;
	}
	
	
	
	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		arg0.pipeline().addLast("encoder", new ObjectEncoder());
        arg0.pipeline().addLast("decoder", new ObjectDecoder(ClassResolvers.softCachingResolver(Msg.class.getClassLoader())));
        arg0.pipeline().addLast(handler.newInstance());
		
	}
		
	
	
}
