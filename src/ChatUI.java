import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ChatUI extends JFrame{
    Socket socket;
    OutputStream outputStream;

    public ChatUI(Socket socket){
        this.socket = socket;
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JTextArea initUI(String title){
        this.setTitle(title);
        this.setSize(430, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        JTextField textField = new JTextField();
        JButton button = new JButton("Send");


        textArea.setBounds(10,10,380,300);
        textField.setBounds(10,320,300,30);
        button.setBounds(320,320,80,30);

        this.add(textArea);
        this.add(textField);
        this.add(button);
        this.setVisible(true);

        ChatListen chatListen = new ChatListen(textField, textArea, outputStream);
        button.addActionListener(chatListen);

        return textArea;

    }

//     public static void main(String[] args) {
//         new ChatUI().initUI("Chat Room");
//     }
}

class ChatListen implements ActionListener{

    JTextField textField;
    JTextArea textArea;
    OutputStream ous;

    public ChatListen(JTextField textField, JTextArea textArea, OutputStream ous){
        this.textField = textField;
        this.textArea = textArea;
        this.ous = ous;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = textField.getText();
        try {
            ous.write(msg.getBytes().length);
            ous.write(msg.getBytes());
            ous.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        textArea.append("Me: " + msg + "\n");
    }
    
}
