import java.io.*;
import java.net.*;

public class UDPServer1
{
    public static void main(String args [ ]) throws Exception
    {
        BufferedReader inFromUser =  new BufferedReader (new InputStreamReader(System.in));
        DatagramSocket serverSocket = new DatagramSocket(11111);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while(true)
        {
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String message = inFromUser.readLine();
            sendData = message.getBytes();
            
            DatagramPacket sendPacket =  new DatagramPacket(sendData,sendData.length,IPAddress,port);
            serverSocket.send(sendPacket);
        }
    }
}
