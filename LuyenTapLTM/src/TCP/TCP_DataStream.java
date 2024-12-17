package TCP;

/*
Mật mã caesar TCP
Server: 203.162.10.109
[Mã câu hỏi (qCode): fOZqi2Kd].  Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã 
thì mỗi ký tự nhận được sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 
thì ký tự “A” sẽ được thay thế bằng ký tự “D”.
Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 
(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
Yêu cầu là xây dựng chương trình client tương tác với server trên,
sử dụng các luồng byte (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;D68C93F7"
b.	Nhận lần lượt chuỗi đã bị mã hóa caesar và giá trị dịch chuyển s nguyên
c.	Thực hiện giải mã ra thông điệp ban đầu và gửi lên Server
d.	Đóng kết nối và kết thúc chương trình.
 */
import java.io.*;
import java.util.*;
import java.net.*;

public class TCP_DataStream {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109", 2207);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        // a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
        String c = "B21DCCN441;fOZqi2Kd";
        dos.writeUTF(c);
        dos.flush();
        // b. Nhận lần lượt chuỗi đã bị mã hóa caesar và giá trị dịch chuyển s nguyên
        String code = dis.readUTF().trim();
        int s = dis.readInt();
        System.out.println(code);
        System.out.println(s);
        // c. Thực hiện giải mã ra thông điệp ban đầu và gửi lên Server
        String answer = Cesar(code, s);
        System.out.println(answer);
        dos.writeUTF(answer);
        // d. Đóng kết nối và kết thúc chương trình.
        dis.close();
        dos.close();
    }

    //Hàm Giải Mã
    public static String Cesar(String code, int s) {
        String ans = "";
        char[] ac = code.toCharArray();
        for (char i : ac) {
            int up = s % 26;
            int distance = (i >= 'a' && i <= 'z') ? (int) ('z' - i) : (int) ('Z' - i);
            if (up <= distance) {
                ans += (char) ((int) (i) + up);
            } else {
                ans += (i >= 'a' && i <= 'z') ? (char) ((int) ('a') - 1 - distance + up) : (char) ((int) ('A') - 1 - distance + up);
            }
        }
        return ans;
    }
}
