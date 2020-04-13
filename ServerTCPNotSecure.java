
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCPNotSecure extends ServerTCP {

	private int port = 1027;
	private ServerSocket server = null;
	private Socket client = null;
	private int clientCount = 0;

	public ServerTCPNotSecure() {

	}

	public ServerTCPNotSecure(int port) {
		this.port = port;
	}

	public int getClientCount() {
		return clientCount;
	}

	public void setClientCount(int clientCount) {
		this.clientCount = clientCount;
	}

	/* this is not secure version */
	public void startServer() {

		try {

			server = new ServerSocket(this.port);
			System.out.println("## Server start listening on port (" + this.port + ")");
			while (true) {
				client = server.accept();
				clientCount++;
				ServerThread serverThread = new ServerThread(client, clientCount, this);
				serverThread.start();
			}
		} catch (Exception e) {
			System.err.println("Error in startServer() : " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		ServerTCPNotSecure serverobj = new ServerTCPNotSecure(1027);
		serverobj.startServer();
	}
}
