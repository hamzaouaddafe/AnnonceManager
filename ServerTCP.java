
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
	private String password = "415263";

	public ServerTCP() {

	}

	public ServerTCP(int port) {
		this.port = port;
	}

	public int getClientCount() {
		return clientCount;
	}

	public void setClientCount(int clientCount) {
		this.clientCount = clientCount;
	}

	public void startServer() {
		System.out.println(">> start running encyrted tcp server.");
		try {
			/*
			 * System.setProperty("javax.net.ssl.keyStore", "server.jsk");
			 * System.setProperty("javax.net.ssl.keyStorePassword" , "123456");
			 * SSLServerSocketFactory serverSocketFactory = (SSLServerSocketFactory)
			 * SSLServerSocketFactory.getDefault(); server =
			 * (SSLServerSocket)serverSocketFactory.createServerSocket(1027);
			 * server.setEnabledCipherSuites(serverSocketFactory.getDefaultCipherSuites());
			 */

			System.setProperty("javax.net.ssl.keyStore", "server.jsk");
			System.setProperty("javax.net.ssl.keyStorePassword", "123456");
			SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

			SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(1027);

			// server = new ServerSocket(this.port);
			System.out.println("## Server start listening on port (" + this.port + ")");
			while (true) {
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
		if (args.length == 0) {

			ServerTCP serverobj = new ServerTCP(1027);
			serverobj.startServer();
		} else if (args.length == 1) {
			if ("no-encryption".equals(args[0])) {
				ServerTCP serverTCP = new ServerTCPNotSecure(1027);
				serverTCP.startServer();
			}
		} else
			System.out.println("## bad arguments : java ServerTCP <option 'no-encryption'>");
	}
}
