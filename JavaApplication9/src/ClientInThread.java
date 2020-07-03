import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by user on 10.02.2018.
 */
public class ClientInThread implements Runnable {
    DataInputStream in;


    public ClientInThread(Socket socket) throws IOException {
        in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = in.readUTF();
                System.out.println(msg);
                Thread.sleep(10);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
