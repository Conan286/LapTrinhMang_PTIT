//[Mã câu hỏi (qCode): ayt2sCVY].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;DC73CA2E”
//b.	Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;a1,a2,...,a50” 
//-	requestId là chuỗi ngẫu nhiên duy nhất
//-	a1 -> a50 là 50 số nguyên ngẫu nhiên
//c.	Thực hiện tìm giá trị lớn nhất và giá trị nhỏ nhất thông điệp trong a1 -> a50 và gửi thông điệp lên lên server theo định dạng “requestId;max,min”
//d.	Đóng socket và kết thúc chương trình
import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.net.*;

public class UDPDataType3 {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        String st = ";B21DCCN441;ayt2sCVY";
        DatagramPacket gt1 = new DatagramPacket(st.getBytes(),st.length(),InetAddress.getByName("203.162.10.109"),2207);
        socket.send(gt1);
        byte []bt = new byte[1024];
        DatagramPacket gt2 = new DatagramPacket(bt,bt.length);
        socket.receive(gt2);
        String s = new String(gt2.getData());
        String []as = s.split(";");
        String re = as[0];
        String num = as[1];
        StringTokenizer ss = new StringTokenizer(num,",");
        ArrayList<Integer> a = new ArrayList<>();
        while(ss.hasMoreTokens()){
        a.add(Integer.valueOf(ss.nextToken().trim()));
        }
        Collections.sort(a);
        re += ";" + String.valueOf(a.get(a.size()-1))+","+String.valueOf(a.get(0));
        System.out.println(re);
        DatagramPacket g3 = new DatagramPacket(re.getBytes(),re.length(),InetAddress.getByName("203.162.10.109"),2207);
        socket.send(g3);
        socket.close();
        }
}
