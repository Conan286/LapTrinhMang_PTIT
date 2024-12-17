package UDP;


import java.io.*;
import java.net.*;
import java.util.*;

public class UDPDataType {

    public static void main(String[] args) throws IOException {
        System.out.println("hello");
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        int sP = 2207;
        //a.
        String code = ";B21DCCN441;QSqXW2sL";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte[] buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        String s = new String(dpNhan.getData());
        System.out.println(s);
        String[] aa = s.split(";");
        String r = aa[0];
        String nn = aa[1];
        System.out.println(r);
        System.out.println(nn);
        String[] num = nn.split(",");
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            int kk = Integer.valueOf(num[i].trim());
            System.out.println(kk);
            a.add(kk);
        }
        Collections.sort(a);
        int ma = a.get(a.size() - 1);
        int mi = a.get(0);
        r = r + ";" + String.valueOf(ma) + "," + String.valueOf(mi);
        System.out.println("request:" + r);
        DatagramPacket dp = new DatagramPacket(r.getBytes(), r.length(), sA, sP);
        socket.send(dp);
        socket.close();
    }
}
