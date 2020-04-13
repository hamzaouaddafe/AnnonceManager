

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
 
public class ServerThread extends Thread {
        
    private ServerTCP serverTCP = null;
    private Socket client = null;
    private BufferedReader bufferedReader;
    private PrintStream printStream;
    private int id;
    private String message;
    private Action action;
    private boolean flag = true;
        
    public ServerThread(Socket client, int count, ServerTCP serverTCP){
        try {

        	this.action = new Action(this);
            this.client = client;
            this.serverTCP = serverTCP;
            this.id = count;
            System.out.println("## Connection (" + id + ") established with client from " + client.getLocalAddress() + " : " + client.getPort());
 		} catch (Exception e) {
			System.err.println("Error in ServerThread() : " + e.getMessage());
		}
    }
 
    public void run() {

         try {
	         while(serverTCP.getClientCount() > 0){  
	            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
	            printStream = new PrintStream(client.getOutputStream());
        		message = bufferedReader.readLine();
        		/*if(message != null) {
        			System.out.println("Client(" + id + ") : " + message); 
        		}*/

        		if ("bye".equalsIgnoreCase(message)) {

            	   	this.serverTCP.setClientCount(this.serverTCP.getClientCount() - 1); 
                    System.out.println("Connection ended by server");
                }
        		else {

                	message = action.handleRequest(message);
                } 
                	
        		printStream.println(message);   
        		flag = false;
			} 

            bufferedReader.close();
            client.close();
        	printStream.close(); 
			System.out.println( "Server cleaning up." );
			System.exit(0);
         } 
         catch(Exception ex){
             System.out.println("Error in run() : " + ex.getMessage());
         }
	}

	public boolean isFlag() {
		return flag;
	}

} 
