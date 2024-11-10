import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.net.*;

public class UDPDataType4 {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        String st = ";B21DCCN441;YJXN6h4C";
        DatagramPacket gt1 = new DatagramPacket(st.getBytes(),st.length(),InetAddress.getByName("203.162.10.109"),2207);
        socket.send(gt1);
        byte []bt = new byte[1024];
        DatagramPacket gt2 = new DatagramPacket(bt,bt.length);
        socket.receive(gt2);
        String s = new String(gt2.getData());
        String []as = s.split(";");
        String re = as[0];
        String num = as[1];
        StringTokenizer ss = new StringTokenizer(num,",");
        ArrayList<Integer> a = new ArrayList<>();
        while(ss.hasMoreTokens()){
        a.add(Integer.valueOf(ss.nextToken().trim()));
        }
        Collections.sort(a);
        re += ";" + String.valueOf(a.get(a.size()-2))+","+String.valueOf(a.get(1));
        System.out.println(re);
        DatagramPacket g3 = new DatagramPacket(re.getBytes(),re.length(),InetAddress.getByName("203.162.10.109"),2207);
        socket.send(g3);
        socket.close();
        }
}
