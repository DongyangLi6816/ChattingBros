import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("lidongyangMacBook-Pro.local", 5888);
            // detect input
            InputStream input = socket.getInputStream();
            // detect input from server and write it to the standard output
            while(true){
                int in = input.read();
                if(in == -1){
                    break;
                }
                System.out.print((char)in);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
