import java.io.File;
import java.io.*;

import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread{

    private final Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket){

        this.clientSocket = socket;
        System.out.println("[CONNECTION] Connected with " + socket.getInetAddress().getHostAddress());

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void run(){
        try {
            String xmlFile = read();

            out.write(xmlFile);
            out.flush();

            in.close();
            out.close();
            clientSocket.close();
        }catch (Exception ignored){}
    }

    private String read(){

        try{
            File iFile = new File("books.xml");
            Scanner sc = new Scanner(iFile);

            StringBuilder sb = new StringBuilder();

            while(sc.hasNextLine()){
                sb.append(sc.nextLine());
            }

            return sb.toString();
        }catch (Exception e ){
            e.printStackTrace();
        }

        return "";
    }

}
