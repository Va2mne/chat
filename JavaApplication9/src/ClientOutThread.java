import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
/**
 * Created by user on 10.02.2018.
 */
public class ClientOutThread implements Runnable{
    DataOutputStream out;
    String nick;
    public ClientOutThread(Socket socket, String nick) throws IOException {
        out = new DataOutputStream(socket.getOutputStream());
        this.nick = nick;
    }
    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        while (true){
            String msg = nick+ ": "+scan.nextLine();
            try {
                out.writeUTF(msg);
                Thread.sleep(10);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
