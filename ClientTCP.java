import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClientTCP {

    public static void main(String args[]) {

        try {
    		// for security we are using SSL
			System.setProperty("javax.net.ssl.trustStore", "client.jsk");
            System.setProperty("javax.net.ssl.trustStorePassword", "123456");
    		SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault(); 

    		SSLSocket socket = (SSLSocket) socketFactory.createSocket("localhost", 1027);
            socket.setEnabledCipherSuites(socketFactory.getDefaultCipherSuites());
            
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
}
