import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
public class Client {
    public static void main(String[] args) {
        try {
            String ipAddress;
            try {
                ipAddress = args[0];
            } catch (Exception e) {
                ipAddress = "127.0.0.1";
            }
            Socket s = new Socket(ipAddress,1000);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msgin="";
            String msgout="";
            while(!msgin.equals("chat.stop()")) {
                System.out.print(">>> ");
                msgout = br.readLine();
                dout.writeUTF(msgout);
                if(msgout.equals("chat.stop()"))
                    break;
                msgin = din.readUTF();
                System.out.println("Server: "+msgin);
                if(msgin.equals("chat.stop()"))
                    break;
            }
            s.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
