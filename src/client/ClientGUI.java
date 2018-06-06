package client;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class ClientGUI {
	JFrame mainFrame;
	JTextField t1;
	JTextArea ta1, conversationDisplay;
	//JLabel conversationDisplay;
	JButton btn1;
	JScrollPane sp1, sp2;
	String fromuser;
	
	public ClientGUI() {
		mainGUI();
	}
	
	public static void main(String[] args)  throws IOException{
		
		ClientGUI client = new ClientGUI();
		
		if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
		
		String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        client.getConnection(hostName, portNumber);
              
	}
	
	private void getConnection(String hostName, int portNumber) {
		// TODO Auto-generated method stub
		try (
				Socket socket = new Socket(hostName, portNumber);        		
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
    				new InputStreamReader(socket.getInputStream()));
			){
				BufferedReader stdIn =
		                new BufferedReader(new InputStreamReader(System.in));
		            String fromServer;
		            		
		            while ((fromServer = in.readLine()) != null) {
		            	display("Server: " + fromServer);
		                System.out.println("Server: " + fromServer);
		                if (fromServer.equals("Bye."))
		                    break;
		                
		                btn1.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								String fromUser = ta1.getText();
								ta1.setText("");
								if (e.getSource()==btn1) {
									//conversationDisplay.setText(s1);
									//if (fromUser != null) {
				                	display("Client: " + fromUser);
				                    System.out.println("Client: " + fromUser);
				                    out.println(fromUser);
				                }
							}
						});
		                //fromUser = stdIn.readLine();
		                
		            }
		        } catch (UnknownHostException e) {
		            System.err.println("Don't know about host " + hostName);
		            System.exit(1);
		        } catch (IOException e) {
		            System.err.println("Couldn't get I/O for the connection to " +
		                hostName);
		            System.exit(1);
		        }
	}

	private void display(String string) {
		// TODO Auto-generated method stub
		fromuser += string + '\n';
		conversationDisplay.setText(fromuser);
	}

	private void mainGUI() {
		mainFrame = new JFrame("Chat Program");
		mainFrame.setSize(500, 500);
		mainFrame.setLayout(null);
		ta1 = new JTextArea();
		ta1.setLineWrap(true);
		ta1.setWrapStyleWord(true);
		conversationDisplay = new JTextArea();
		conversationDisplay.setLineWrap(true);
		conversationDisplay.setWrapStyleWord(true);
		conversationDisplay.setEditable(false);
		sp1 = new JScrollPane(ta1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp1.setBounds(40,350,300,100);
		sp2 = new JScrollPane(conversationDisplay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp2.setBounds(40,40,300,300);
		btn1 = new JButton("Enter");
		btn1.setBounds(365,375,100,50);
		//btn1.addActionListener(new ButtonClickListener());
		mainFrame.add(btn1);
		mainFrame.add(sp1);
		mainFrame.add(sp2);
		mainFrame.setVisible(true);
	}
	
	/*private class ButtonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//String s1 = ta1.getText();
			
			//if (e.getSource()==btn1) 
				//conversationDisplay.setText(s1);
		}
		
	}*/
}
