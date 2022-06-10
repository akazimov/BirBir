import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1000);
            Socket s = ss.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msgin = "";
            String msgout = "";
            while(!msgin.equals("chat.stop()")) {
                msgin = din.readUTF();
                System.out.print("Client: ");
                System.out.println(msgin);
                if(msgin.equals("chat.stop()"))
                    break;
                System.out.print(">>> ");
                msgout = br.readLine();
                dout.writeUTF(msgout);
                dout.flush();
                if(msgout.equals("chat.stop()"))
                    break;
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
