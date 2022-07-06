import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Client {
    // initializing the client
    Socket clientSocket;
    OutputStream os;
    InputStream input;
    public boolean initClient(String ip, int port){
        try {
            clientSocket = new Socket(ip, port);
            return true;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public void initIO(){
        try {
            os = clientSocket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            input = clientSocket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean send(byte[] msg, int msgType){
        try {
            os.write(msgType);
            os.write(msg.length);
            os.write(msg);
            os.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.initClient("lidongyangMacBook-Pro.local", 5888);
        client.initIO();
        client.send("Hello! I am client.".getBytes(), 0);
        int[] intmsg = {1,2,3,4,5,6,7,8,9,0};
        byte[] intmsgByte = new byte[intmsg.length*4];
        for (int i = 0; i < intmsgByte.length; i++) {
            byte[] bytes = DataTools.intToByte(intmsg[i]);
            DataTools.bytesArrayToByteArr(intmsgByte, bytes);
        }
        client.send(intmsgByte, 1);
        try (Scanner scanner = new Scanner(System.in)) {
            while(true){
                int msgint = scanner.nextInt();
                byte[] msgByteInt = DataTools.intToByte(msgint);
                client.send(msgByteInt, 1); 
            }
        }
    }
}
