import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.swing.JTextArea;

public class ChatClient {
    Socket socket = null;
    public void initClient(){
        try{
            socket = new Socket("lidongyangMacBook-Pro.local", 5888);
        }catch(IOException e){
            e.printStackTrace();
        }
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

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.initClient();
        ChatUI chatUI = new ChatUI(client.socket);
        JTextArea textArea = chatUI.initUI("Client" + client.socket.getPort() +"chat room");
        client.readMsg(textArea, client.socket);

    }
}
