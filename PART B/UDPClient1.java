import java.io.*;
import java.net.*;

public class UDPClient1
{
   public static void main(String args [ ]) throws Exception
   {
       DatagramSocket clientSocket = new DatagramSocket();
       InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
       byte[] sendData =  new byte[1024];
       byte[] receiveData = new byte[1024];
       String sentence = "Hi,I am a clinet.Ping me a message";
       sendData = sentence.getBytes();
       
       DatagramPacket sendPacket =  new DatagramPacket(sendData,sendData.length,IPAddress,1111);
       clientSocket.send(sendPacket);
       
       DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
       clientSocket.receive(receivePacket);
       
       String reply =  new String(receivePacket.getData());
       System.out.println("Server From="+ reply);
       
       clientSocket.close();
   }
}
