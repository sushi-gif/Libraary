package space.nullpointerexception.libraary.InternetServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import space.nullpointerexception.libraary.DataUtils.BooksManager;

public class ClientConnection extends Thread {

    private boolean canRead = false;

    public ClientConnection(){
    }

    @Override
    public void run() {
        try {

            Socket socket = new Socket("173.212.209.141", 7777);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String xmlString = br.readLine();
            socket.close();

            BooksManager bm = BooksManager.getInstance();
            bm.decode(xmlString);

            canRead = true;

        } catch (IOException ignored) {}
    }

    public boolean isReadable(){
        return canRead;
    }

}

