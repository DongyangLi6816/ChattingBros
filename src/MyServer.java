import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer{
    ServerSocket listenServer;
    public boolean initServerSocket(int port) {
        try {
            listenServer = new ServerSocket(port);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public Socket listenClientConnection(){
        try {
            Socket clientSocket = listenServer.accept();
            return clientSocket;
        }catch(IOException e){
            e.printStackTrace();
            throw new NullPointerException("connection error");
        }
    }

    public void readMsg(Socket clientSocket){
        InputStream input;
        try {
            input = clientSocket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            int msgType = input.read();
            int msgLength = input.read();
            byte[] msgData = new byte[msgLength];
            input.read(msgData);
            if(msgType == 0){
                String msg = new String(msgData);
                System.out.println("Message: " + msg);
            }else if(msgType == 1){
                System.out.print("number received: ");
                for (int i = 0; i < msgData.length; i++){
                    int num = msgData[i + 0] << 24
                            | msgData[i + 1] << 16
                            | msgData[i + 3];
                    System.out.print(num);
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MyServer myserver = new MyServer();
        myserver.initServerSocket(5888);
        Socket socket = myserver.listenClientConnection();
        while(true){
            myserver.readMsg(socket);
        }
    }
}