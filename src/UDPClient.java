import org.jetbrains.annotations.NotNull;

import java.net.*;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

class UDPClient {
    public static void main( String[] args) {  //args should be IP address (127.0.0.1) and port number (ex. 1234)

        // Exit if incorrect number of arguements were passed in
        System.out.println(args.length);
        System.out.println(args);
        if(args.length != 2)
        {
            System.out.println("Proper Usage is: java UDPClient IP port");
            System.exit(0);
        }

        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();

            // user input for message
            Scanner scan = new Scanner(System.in);
            System.out.print("Input message: ");
            byte[] msg = scan.nextLine().getBytes();
            scan.close();

            // get IP and port from args
            InetAddress aHost = InetAddress.getByName(args[0]);
            int serverPort = Integer.parseInt(args[1]);

            // construct and send datagram w/ message to send to server
            DatagramPacket request = new DatagramPacket(msg, msg.length, aHost,serverPort);
            aSocket.send(request);

            // prepare to recieve reply from server
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));

        }
        catch(SocketException e){System.out.println("Socket: " + e.getMessage());}
        catch(IOException e){System.out.println("IO: " + e.getMessage());}
        finally{if(aSocket != null) aSocket.close();}
    }
}