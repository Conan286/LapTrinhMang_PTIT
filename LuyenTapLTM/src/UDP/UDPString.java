package UDP;


import java.net.*;
import java.util.*;
import java.math.*;
import java.io.*;
import java.lang.*;
//[Mã câu hỏi (qCode): euVLFfmH].  Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. 
//Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;5B35BCC1”
//b.	Nhận thông điệp từ server theo định dạng “requestId;data” 
//-	requestId là một chuỗi ngẫu nhiên duy nhất
//-	data là chuỗi dữ liệu cần xử lý
//c.	Xử lý chuẩn hóa chuỗi đã nhận thành theo nguyên tắc 
//i.	Ký tự đầu tiên của từng từ trong chuỗi là in hoa
//ii.	Các ký tự còn lại của chuỗi là in thường
//Gửi thông điệp chứa chuỗi đã được chuẩn hóa lên server theo định dạng “requestId;data”
//d.	Đóng socket và kết thúc chương trình
public class UDPString {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        String st = ";B21DCCN441;euVLFfmH";
        DatagramPacket gt1 = new DatagramPacket(st.getBytes(),st.length(),InetAddress.getByName("203.162.10.109"),2208);
        socket.send(gt1);
        byte []bt = new byte[1024];
        DatagramPacket gt2 = new DatagramPacket(bt,bt.length);
        socket.receive(gt2);
        String s = new String(gt2.getData());
        String []sa = s.split(";");
        String ri = sa[0];
        String data = sa[1].toLowerCase();
        System.out.println(data);
         data = ch(data);
        ri += ";"+ data;       
        System.out.println(data);
        DatagramPacket gt3 = new DatagramPacket(ri.getBytes(),ri.length(),InetAddress.getByName("203.162.10.109"),2207);
        socket.send(gt3);
        socket.close();
    }
    public static String ch(String s )
    {
    String ans = "";
    char []aa = s.toCharArray();
    ans += (char)(aa[0]-32);
    for(int i = 1;i<aa.length-1;i++)
    {
    if(aa[i-1]==' ') ans+= (char)(aa[i]-32);
    else ans+= aa[i];
    }
    return ans;
    }
}
