import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class chuoiSo {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
       Socket socket = new Socket("203.162.10.109",2208);
       String code = "B21DCCN404;mfnxxU6n";
       BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
       bw.write(code); bw.newLine(); bw.flush();
       String s = br.readLine();
        System.out.println(s);
        
    }
}
