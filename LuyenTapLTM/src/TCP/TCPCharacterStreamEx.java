package TCP;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class TCPCharacterStreamEx {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
       Socket socket = new Socket("203.162.10.109",2208);
       String code = "B21DCCN441;mfnxxU6n";
       BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
       bw.write(code); bw.newLine(); bw.flush();
       String s = br.readLine();
       StringTokenizer ss = new StringTokenizer(s,",");
       String ans = "";
       while(ss.hasMoreTokens())
       {
       String k = ss.nextToken().trim();
       if(k.contains(".edu"))
       {
           System.out.println(k);
           ans += k+", ";
       }
       }
       ans = ans.substring(0, ans.length()-2);
        System.out.println(ans);
       bw.write(ans);
       
       bw.flush();
       br.close();
       bw.close();
    }
}
