package TCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    public static void main(String argv[]) throws Exception
    {

        Socket clientSocket = new Socket("localhost", 5824);
        DataInputStream din = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader Br = new BufferedReader(new InputStreamReader(System.in));

        String cline="", response="";
       while(true)
        {
            System.out.println("Enter Key: ");
            cline=Br.readLine();
            if(cline.equals("Exit"))
            {
                dout.writeUTF(cline);
                dout.flush();
                return;
            }
            else {
                dout.writeUTF(cline);
                dout.flush();
                response = din.readUTF();
                System.out.println("Server Response: " + response);
            }
       }

    }
}
