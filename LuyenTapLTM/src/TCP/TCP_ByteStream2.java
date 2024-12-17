/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;
import java.io.*;
import java.util.*;
import java.rmi.*;
import java.net.*;
import java.lang.*;
import java.rmi.registry.*;
public class TCP_ByteStream2 {
    public static String L(String s)
    {
    String ans = "";
    char []a = s.toCharArray();
    int n = a.length;
    int max = 1;
    for(int i = 0;i<n;i++){
    String tmp = ""+a[i];
    Set<Character> sc = new HashSet<>();
    sc.add(a[i]);
    for(int j=i+1;j<n;j++)
    {
    if(sc.contains(a[j])) break;
    sc.add(a[j]);
    tmp+= a[j];
    }
    int k = tmp.length();
    if(k>max){
    max = k;
    ans = tmp+";"+String.valueOf(k);
    }
    }
    return ans;
    }
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109",2206);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        String dp = "B21DCCN002;YaRebRFf";
        os.write(dp.getBytes());
        byte []bf = new byte[2048];
        int in = is.read(bf, 0, bf.length);
        String s = new String(bf,0,in);
        System.out.println(s);
        String ans = L(s);
        System.out.println(ans);
        os.write(ans.getBytes());
        is.close();
        os.close();
    }
}
