
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClientTCP {

	public void startClient() {
		System.out.println(">> start running encrypted tcp client.");
		try {
			/*
			 * // for security we are using SSL
			 * System.setProperty("javax.net.ssl.trustStore", "client.jsk");
			 * System.setProperty("javax.net.ssl.trustStorePassword", "123456");
			 * SSLSocketFactory socketFactory = (SSLSocketFactory)
			 * SSLSocketFactory.getDefault();
			 * 
			 * SSLSocket socket = (SSLSocket) socketFactory.createSocket("localhost", 1027);
			 * socket.setEnabledCipherSuites(socketFactory.getDefaultCipherSuites());
			 * 
			 */

			System.setProperty("javax.net.ssl.trustStore", "client.jsk");
			System.setProperty("javax.net.ssl.trustStorePassword", "123456");
			SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket socket = (SSLSocket) socketFactory.createSocket("localhost", 1027);

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream printStream = new PrintStream(socket.getOutputStream());
			BufferedReader entryStream = new BufferedReader(new InputStreamReader(System.in));
			String request = "";
			String response = "";

			printStream.println("connected");
			while (!request.equalsIgnoreCase("bye")) {
				response = bufferedReader.readLine();
				System.out.println(response.replace(ConsolePrinter.SEPARATOR, ConsolePrinter.BREAKER));
				System.out.print(">> Commande ? : ");
				request = entryStream.readLine();
				printStream.println(request);
			}
			socket.close();
			bufferedReader.close();
			printStream.close();
			entryStream.close();
			System.out.println("Connection ended by client");
		} catch (Exception e) {

			System.err.println("Error in Client() : " + e.getMessage());
		}
	}
	public static void main(String args[]) {

		if(args.length == 0) {

			new ClientTCP().startClient();
		}
		else if(args.length == 1) {
			if("no-encryption".equals(args[0])) {
				new ClientTCPNotSecure().startClient();
			}
		}
		else System.out.println("## bad arguments : java ClientTCP <option 'no-encryption'>");
	}
}
