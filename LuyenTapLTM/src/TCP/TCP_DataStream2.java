/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;
import java.net.*;
import java.rmi.*;
import java.util.*;
import java.io.*;
import java.math.*;
import java.rmi.registry.*;

public class TCP_DataStream2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109",2207);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        String code = "B21DCCN002;D5OIIvBN";
        dos.writeUTF(code);
        dos.flush();
        int n = dis.readInt();
        System.out.println(n);
        int []a = new int[n];
        int sum = 0;
        float adv = 0;
        float ps = 0;
        for(int i = 0;i<n;i++){
            a[i] = dis.readInt();
            System.out.print(a[i] + " ");
            sum += a[i];
        }
        adv = (float) ((float) sum*1.0/n);
        for(int i = 0;i<n;i++){
            float k = (float)a[i] - adv;
            ps += k*k;
        }
        ps = ps/(float)n;
        System.out.println(sum);
        System.out.println(adv);
        
        System.out.println(ps);
        dos.writeInt(sum);
        dos.writeFloat(adv);
        dos.writeFloat(ps);
        dos.close();
        dis.close();
    }
}
