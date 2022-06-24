import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer{
    public static void main(String[] args) {
        try {
            // find connection
            ServerSocket serversocket = new ServerSocket(5888);
            System.out.println("Connecting");
            Socket socket = serversocket.accept();
            System.out.println(socket.getPort() + " is connected");

            // reveive & send message
            OutputStream os = socket.getOutputStream();
            os.write("Hello World!".getBytes());
            Scanner sc = new Scanner(System.in);

            // use a while loop to send message to client
            while(true){
                String msg = sc.nextLine();
                // if detected "exit", end the connection
                if(msg == "exit"){
                    os.close();
                    socket.close();
                    break;
                }
                os.write((msg+"\n").getBytes());
                os.flush();   
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}