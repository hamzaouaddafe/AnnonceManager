import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

 
public class ServerTCP {

    private int port;
    private SSLServerSocket server = null;
    private SSLSocket client = null; 
    private int clientCount = 0;
	
    
    public ServerTCP() {
    	
	} 
    
    public ServerTCP(int port){
        this.port = port; 
    }

    
    public int getClientCount() {
		return clientCount;
	}

	public void setClientCount(int clientCount) {
		this.clientCount = clientCount;
	}
	public void startServer() {
        
    	try {
    		System.setProperty("javax.net.ssl.keyStore", "server.jsk");
    		System.setProperty("javax.net.ssl.keyStorePassword" , "123456");
    	

    		SSLServerSocketFactory serverSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault(); 
    		server = (SSLServerSocket)serverSocketFactory.createServerSocket(1027);
    		server.setEnabledCipherSuites(serverSocketFactory.getDefaultCipherSuites());
    		
    		// server = new ServerSocket(this.port);
            System.out.println("## Server start listening on port (" + this.port + ")");
            while(true) {
                client = (SSLSocket) server.accept(); 
            	clientCount++;
                ServerThread serverThread = new ServerThread(client, clientCount, this); 
                serverThread.start();
            }
		} catch (Exception e) {
			System.err.println("Error in startServer() : " + e.getMessage());
		}
    } 
    
    public static void main(String[] args) throws IOException {
        ServerTCP serverobj = new ServerTCP(1027);
        serverobj.startServer();
    }
}
