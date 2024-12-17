package UDP;

/*
[Mã câu hỏi (qCode): 2gZgSnir].  Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã thì mỗi ký tự nhận được sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 thì ký tự “A” sẽ được thay thế bằng ký tự “D”.
Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu xây dựng chương trình client trao đổi thông tin với server theo kịch bản mô tả dưới đây:
a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN001;825EE3A7"
b.	Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;strEncode;s".
•	requestId là chuỗi ngẫu nhiên duy nhất
•	strEncode là chuỗi thông điệp bị mã hóa
•	s là số nguyên chứa giá trị độ dịch của mã
c.	Giải mã tìm thông điệp ban đầu và gửi lên server theo định dạng “requestId;strDecode”
d.	Đóng socket và kết thúc chương trình.
 */
import java.util.*;
import java.math.*;
import java.lang.*;
import java.io.*;
import java.net.*;

public class UDP_DataType {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        String code = ";B21DCCN441;2gZgSnir";
        int sP = 2207;
        InetAddress sA = InetAddress.getByName("203.162.10.109");
        DatagramPacket dP = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dP);
        byte[] bf = new byte[1024];
        DatagramPacket dP1 = new DatagramPacket(bf, bf.length, sA, sP);
        socket.receive(dP1);
        String s = new String(dP1.getData()).trim();
        System.out.println(s);
        String ss[] = s.split(";");
        String rq = ss[0];
        String ce = ss[1].trim();
        int k = Integer.parseInt(ss[2].trim());
        System.out.println(rq);
        System.out.println(ce);
        System.out.println(k);
        String de = giaiMa(ce, k);
        System.out.println(de);
        de = rq + ";" + de;
        DatagramPacket dP2 = new DatagramPacket(de.getBytes(), de.length(), sA, sP);
        socket.send(dP2);
        socket.close();
    }

    public static String giaiMa(String s, int k) {
        String ans = "";
        char[] a = s.toCharArray();
        for (char i : a) {
            int z = (i >= 'a' && i <= 'z') ? (int) ('z' - i) : (int) ('Z' - i);
            int h = k % 26;
            if (h <= z) {
                ans += (char) ((int) (i) + h);
            } else {
                ans += (i >= 'a' && i <= 'z') ? (char) ((int) ('a') - 1 + (h - z)) : (char) ((int) ('A') - 1 + (h - z));
            }
        }
        return ans;
    }
}
//A-1 = Z 0 1
//A-27 = Z 0 1
