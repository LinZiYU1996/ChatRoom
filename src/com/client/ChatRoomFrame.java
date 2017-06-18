package com.client;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

/*
 * 创建聊天室的GUI
 */
public class ChatRoomFrame extends JFrame{
	  static final long serialVersionUID = 1L;
	   JTextField entry;
	   JTextArea textArea;
	   JScrollPane jScrollPane1;
	
	   public ChatRoomFrame() {
		   
		   super("Chat");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        entry = new JTextField();
	        textArea = new JTextArea();
	        textArea.setText("Loading...");
	 
	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        setSize(600, 600);
	 
	        textArea.setColumns(20);
	        textArea.setLineWrap(true);
	        textArea.setRows(5);
	        textArea.setWrapStyleWord(true);
	        textArea.setEditable(false);
	        jScrollPane1 = new JScrollPane(textArea);
	        
	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        
	        ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	        SequentialGroup h1 = layout.createSequentialGroup();
	        ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
	        h1.addContainerGap();
	        h2.addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
	        SequentialGroup h3 = layout.createSequentialGroup();
	        h3.addComponent(entry, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE);
	        h2.addGroup(h3);
	        h1.addGroup(h2);
	        h1.addContainerGap();
	        hGroup.addGroup(GroupLayout.Alignment.TRAILING, h1);
	        layout.setHorizontalGroup(hGroup);
	         
	        ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
	        SequentialGroup v1 = layout.createSequentialGroup();
	        v1.addContainerGap();
	        ParallelGroup v2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
	        v2.addComponent(entry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
	        v1.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE);
	        v1.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED);
	        v1.addGroup(v2);
	        v1.addContainerGap();
	        vGroup.addGroup(v1);
	        layout.setVerticalGroup(vGroup);
	        pack();
	        
	        setVisible(true);
	        
	        entry.requestFocus();
		   
	}
	   
	
}
