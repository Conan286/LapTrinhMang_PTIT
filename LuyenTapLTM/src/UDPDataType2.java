
import java.util.*;
import java.io.*;
import java.net.*;
import java.math.*;
import java.lang.*;

public class UDPDataType2 {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        String ptit = ";B21DCCN441;toFVhCmr";
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        DatagramPacket g1 = new DatagramPacket(ptit.getBytes(), ptit.length(), sA, sP);
        socket.send(g1);
        byte[] buffer = new byte[1024];
        DatagramPacket g2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(g2);
        String s = new String(g2.getData());
        System.out.println(s);
        String[] sp = s.split(";");
        String r = sp[0];
        String num = sp[1];
        StringTokenizer ss = new StringTokenizer(num, ",");
        ArrayList<Integer> a = new ArrayList<>();
        while (ss.hasMoreTokens()) {
            int kk = Integer.valueOf(ss.nextToken().trim());
            a.add(kk);
        }
        Collections.sort(a);
        r = r + ";" + String.valueOf(a.get(a.size() - 2)) + "," + String.valueOf(a.get(1));
        System.out.println(r);
        DatagramPacket g3 = new DatagramPacket(r.getBytes(), r.length(), sA, sP);
        socket.send(g3);
        socket.close();
    }
}
