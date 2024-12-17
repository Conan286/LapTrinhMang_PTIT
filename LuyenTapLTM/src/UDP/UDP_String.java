package UDP;

//[Mã câu hỏi (qCode): mIiyaVSa].  [Loại bỏ ký tự đặc biệt và ký tự trùng giữ nguyên thứ tự xuất hiện]
//Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208 . Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode”. Ví dụ: ";B15DCCN001;B34D51E0"
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;str1;str2".
//•	requestId là chuỗi ngẫu nhiên duy nhất
//•	str1,str2 lần lượt là chuỗi thứ nhất và chuỗi thứ hai
//c.	Loại bỏ các ký tự trong chuỗi thứ nhất mà xuất hiện trong chuỗi thứ hai, giữ nguyên thứ tự xuất hiện. Gửi thông điệp là một chuỗi lên server theo định dạng "requestId;strOutput", trong đó chuỗi strOutput là chuỗi đã được xử lý ở trên.
//d.	Đóng socket và kết thúc chương trình.

import java.io.*;
import java.lang.*;
import java.util.*;
import java.net.*;

public class UDP_String {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        int port = 2208;
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        String code = ";B21DCCN441;mIiyaVSa";
        DatagramPacket dP = new DatagramPacket(code.getBytes(), code.length(), sA, port);
        socket.send(dP);
        byte[] bf = new byte[1024];
        DatagramPacket dP1 = new DatagramPacket(bf, bf.length);
        socket.receive(dP1);
        String s = new String(dP1.getData()).trim();
        String[] As = s.split(";");
        String requestId = As[0];
        String s1 = As[1];
        String s2 = As[2];
        System.out.println(requestId);
        System.out.println(s1);
        System.out.println(s2);
        String ans = "";
        Set<Character> se2 = new HashSet<>();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        for (char i : str2) {
            se2.add(i);
        }
        for (char i : str1) {
            if (se2.contains(i) != true) {
                ans += i;
            }
        }
        ans = requestId + ";" + ans;
        DatagramPacket dP3 = new DatagramPacket(ans.getBytes(), ans.length(), sA, port);
        socket.send(dP3);
        socket.close();
        
    }
}
