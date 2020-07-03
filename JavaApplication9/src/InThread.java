import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by user on 10.02.2018.
 */
public class InThread implements Runnable {
    DataInputStream in;
    OutThread out,out2;

    public InThread(Socket socket) throws IOException {
        in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = in.readUTF();
                synchronized (out.message) {
                    if (!msg.equals("")) {
                        out.message = msg;
                        out.newmessage = true;
                    }
                }
                 synchronized (out2.message) {
                    if (!msg.equals("")) {
                        out2.message = msg;
                        out2.newmessage = true;
                    }
              }
                Thread.sleep(10);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
