/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package UDP;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.net.*;
import java.io.*;
public class UDP_String2 {

    public static void main(String args[]) throws SocketException, UnknownHostException, IOException {
        // TODO code application logic here
        DatagramSocket socket = new DatagramSocket();
        String code = ";B21DCCN002;vSgxl3HQ";
        int sP = 2208;
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        DatagramPacket dp = new DatagramPacket(code.getBytes(),code.length(),sA,sP);
        socket.send(dp);
        byte bf[] = new byte[1024];
        DatagramPacket dp1 = new DatagramPacket(bf,bf.length);
        socket.receive(dp1);
        String s = new String(dp1.getData());
        String []as = s.split(";");
        String ans = as[0]+";";
        System.out.println(as[1]);
        char []data = as[1].trim().toCharArray();
        Map<Character,Integer> mp = new HashMap<>();
        String k = "";
        ArrayList<Character>ac = new ArrayList<>();
        for(char i: data)
        {
        if(mp.containsKey(i)) mp.put(i, mp.get(i)+1);
        else {
            mp.put(i, 1);
            ac.add(i);
        }
        }
        for(char i:ac) {
        k +=  String.valueOf(mp.get(i)) + i;
        }
        ans += k;
        System.out.println(ans);
        DatagramPacket dp3 = new DatagramPacket(ans.getBytes(),ans.length(),sA,sP);
        socket.send(dp3);
        socket.close();
        }
    }

