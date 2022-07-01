package TCP;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPServer {

    public static void main(String argv[]) throws Exception
    {
        String request="", response="";


        ServerSocket Socket = new ServerSocket(5824);
        Socket connectionSocket = Socket.accept();
        DataInputStream dataInput = new DataInputStream(connectionSocket.getInputStream());
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());


        while(true) {
            request=dataInput.readUTF();
            if(request.equals("Exit"))
              {
               return;
            }
            else
                {
                System.out.println("Client Request: " + request);
                response = FindCourse(request);
                outToClient.writeUTF(response);
                outToClient.flush();
            }
        }
    }

    public static String  FindCourse(String Key)
    {
        try
        {
            File op =  new File("Net.txt");
            Scanner sc = new Scanner(op);
            int i = 0;
            while (sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] arr = line.split(" ", 5);
                if(arr [i].equals(Key))
                {
                    return arr [++i];
                }
            }
            sc.close();
        }
        catch (Exception hh)
        {
            System.out.println("An error occurred.");
            hh.printStackTrace();
        }
        return "Error 404";
    }
}

