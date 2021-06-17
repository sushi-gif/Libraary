import java.net.ServerSocket;

public class Server {

    private ServerSocket serverSocket;

    public Server(){

    }

    public void start(int port){

        try {
            System.out.println("[INFO] Starting the server on port " + port);
            serverSocket = new ServerSocket(port);
            System.out.println("[INFO] Server started ");
        } catch (Exception e) {
            System.err.println("[ERROR] Server Class constructor : Unable to start the server on port " + port + ". Aborting.");
            System.exit(-1);
        }

        while(true){
            try {
                new ClientHandler(serverSocket.accept()).start();
            } catch (Exception e) {
                System.err.println();
            }
        }

    }

}