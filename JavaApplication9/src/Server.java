import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * Created by user on 10.02.2018.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        int port = 40000;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
        OutThread out = new OutThread(socket, "Server");
        InThread in = new InThread(socket);

        Socket socket2 = serverSocket.accept();
        OutThread out2 = new OutThread(socket2, "Server");
        InThread in2 = new InThread(socket2);
        
        Socket socket3 = serverSocket.accept();
        OutThread out3 = new OutThread(socket3, "Server");
        InThread in3 = new InThread(socket3);

        in.out=out2;
        in.out2=out3;
                      
        in2.out = out;
        in2.out2 = out3;
        
        in3.out = out;
        in3.out2 = out2;
        

        new Thread(out).start();
        Thread tr = new Thread(in);
        tr.start();
        new Thread(out2).start();
        Thread tr2 = new Thread(in2);
        tr2.start();
        new Thread(out3).start();
        Thread tr3 = new Thread(in3);
        tr3.start();
    }
}
class Client{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 40000);
        System.out.println("Connected to server");
//        DataInputStream in = new DataInputStream(socket.getInputStream());
//        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//        out.writeUTF("Hello bruda");
//        out.writeUTF("Do you know da way?");
//        Scanner scan = new Scanner(System.in);
//        while (true){
//            out.writeUTF(scan.nextLine());
//            System.out.println(in.readUTF());
//        }
        ClientOutThread out = new ClientOutThread(socket, "Client");
        new Thread(out).start();
        new Thread(new ClientInThread(socket)).start();

    }
}