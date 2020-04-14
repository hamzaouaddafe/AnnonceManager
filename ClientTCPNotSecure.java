import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientTCPNotSecure extends ClientTCP {

    public void startClient() {

        System.out.println(">> start running none encrypted tcp client.");
        try {

            Socket socket = new Socket("localhost", 1027);

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            BufferedReader entryStream = new BufferedReader(new InputStreamReader(System.in));
            String request = "";
            String response = "";

            printStream.println("connected");
            while (!request.equalsIgnoreCase("bye")) {
                response = bufferedReader.readLine();
                System.out.println(
                        response.replace(ConsolePrinter.SEPARATOR, ConsolePrinter.BREAKER));
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

    //	/* this is client to comminucate with not secured Server */
    //	public static void main(String args[]) {
    //
    //		new ClientTCPNotSecure().startClient();
    //	}
}
