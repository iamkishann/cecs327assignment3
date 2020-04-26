import java.net.*;
import java.io.*;

public class UDPServer{
    public static void main(String args[]){  //args[0] is port number (ex. 1234)

        // Exit if incorrect number of arguements were passed in
        if(args.length != 1)
        {
            System.out.println("Proper Usage is: java UDPServer port");
            System.exit(0);
        }

        DatagramSocket aSocket = null;
        try{
            // Create websocket at the port passed in from args
            aSocket = new DatagramSocket(Integer.parseInt(args[0]));
            byte[] buffer = new byte[1000];

            // Infinitely loop to listen for requests and echo them back to the sender
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                System.out.println("Echoing message: " + new String(request.getData()));
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }
        catch(SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }
        catch(IOException e){
            System.out.println("IO: " + e.getMessage());
        }
        finally{
            if(aSocket != null) aSocket.close();
        }
    }
}