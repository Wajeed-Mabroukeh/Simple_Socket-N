package UDP;

import TCP.TCPServer;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(5824);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            char [] Exit = new char[4] ;
            sentence.getChars(0,4,Exit,0);
            String re = String.valueOf(Exit);
            if(re.equals("Exit"))
            {
                System.out.println("Client Request: " + re);
                String res = FindCourses(sentence);
                sendData = res.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
                return;
            }
            else {
                System.out.println("Client Request: " + sentence);
                String res = FindCourses(sentence);
                sendData = res.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        }

    }


    public static String  FindCourses(String Key)
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

                if(arr[i].equals(Key.trim()))
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
