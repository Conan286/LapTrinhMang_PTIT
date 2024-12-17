/*
[Mã câu hỏi (qCode): wAKCZwjj].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;73457A17”
b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;n;A1,A2,...An” , với
-	requestId là chuỗi ngẫu nhiên duy nhất
-	n là một số ngẫu nhiên nhỏ hơn 100.
-            A1, A2 ... Am (m <= n) là các giá trị ngẫu nhiên nhỏ hơn hoặc bằng n và có thể trùng nhau.
Ex: requestId;10;2,3,5,6,5
c.	Tìm kiếm các giá trị còn thiếu và gửi lên server theo định dạng “requestId;B1,B2,...,Bm”
Ex: requestId;1,4,7,8,9,10
d.	Đóng socket và kết thúc chương trình.
 */
package UDP;

import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;
import static java.lang.Integer.parseInt;
import java.math.*;

import java.rmi.registry.*;

public class UDP_DataType2 {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket sk = new DatagramSocket();
        String code = ";B21DCCN002;wAKCZwjj";
        int sP = 2207;
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        DatagramPacket dp = new DatagramPacket(code.getBytes(),code.length(),sA,sP);
        sk.send(dp);
        byte []bf = new byte[1024];
        DatagramPacket dp2 = new  DatagramPacket(bf,bf.length);
        sk.receive(dp2);
        String s = new String(dp2.getData());
        String []As = s.split(";");
        String requestId = As[0].trim();
        int n = parseInt(As[1].trim());
        String ar = As[2].trim();
        Set<Integer> a = new HashSet<>();
        StringTokenizer ss = new StringTokenizer(ar,",");
        while(ss.hasMoreTokens()) a.add(parseInt(ss.nextToken().trim()));
        ArrayList<Integer> b = new ArrayList<>();
        for(int i = 1;i<=n;i++) if(a.contains(i)!=true) b.add(i);
        String ans = requestId+";";
        for(int i = 0;i<b.size()-1;i++)
            ans+= b.get(i)+",";
        ans += b.get(b.size()-1);
        System.out.println(a);
        System.out.println(b);
        DatagramPacket dp3 = new DatagramPacket(ans.getBytes(),ans.length(),sA,sP);
        System.out.println(ans);
        sk.send(dp3);
        sk.close();
    }
}
