package com.client;



public class ClientHandler extends ChatClientHandler{

	@Override
	public void print(String msg) {
		if(Client.connected){
            Client.chatRoomFrame.textArea.append(msg + "\n");
        }
	}
		
}
