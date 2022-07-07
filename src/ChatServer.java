import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;
public class ChatServer {
    ServerSocket serverScocket;
    public void initServer(int port){
        try{
            serverScocket = new ServerSocket(port);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Socket listenClient(){
        try{
            System.out.println("Listening....");
            return serverScocket.accept();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void readMsg(JTextArea textArea, Socket client){
        InputStream in = null;
        try {
            in = client.getInputStream();
            while(true){
                int msglen = in.read();
                byte[] msgbytes = new byte[msglen];
                in.read(msgbytes);
                String msg = new String(msgbytes);
                System.out.println(client.getPort()+ "sent: " + msg);

                textArea.append(client.getPort()+ "sent: " + msg + "\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Socket client){

    }
    public static void main(String[] args) {
        ChatServer cs = new ChatServer();
        cs.initServer(5888);
        Socket client = cs.listenClient();
        ChatUI chatUI = new ChatUI(client);
        JTextArea textArea = chatUI.initUI("Server" + 5888 + " chat room");
        cs.readMsg(textArea, client);
    }
}
