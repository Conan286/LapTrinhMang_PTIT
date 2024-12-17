package TCP;

/*
Dạng bài tập sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin
Server: 203.162.10.109
[Mã câu hỏi (qCode): KIUmrIiX]. 
Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206
(thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s).
Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên 
sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
Ví dụ: "B16DCCN999;A63D9404"
b. Nhận dữ liệu từ server là một chuỗi các số nguyên được sắp xếp ngẫu nhiên,
các số được phân tách nhau bởi ký tự ",". Ví dụ: "2,15,4,3,6,8,10,7,1".
c. Sắp xếp tăng dần các giá trị chẵn và sau đó tăng dần các giá trị lẻ trong dãy số.
Ví dụ: "[2, 4, 6, 8, 10];[1, 3, 7, 15]". Gửi chuỗi được sắp xếp này lên server.
d. Đóng kết nối và kết thúc chương trình.
 */
import java.util.*;
import java.io.*;
import java.lang.*;
import static java.lang.Integer.parseInt;
import java.math.*;
import java.net.*;

public class TCP_ByteStream {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        // a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
        String code = "B21DCCN441;KIUmrIiX";
        os.write(code.getBytes());
        os.flush();
        // b. Nhận dữ liệu từ server là một chuỗi các số nguyên được sắp xếp ngẫu nhiên
        byte[] bf = new byte[1024];
        int byteRead = is.read(bf);
        String s = new String(bf, 0, byteRead).trim();
        System.out.println(s);
        ArrayList<Integer> chan = new ArrayList<>();
        ArrayList<Integer> le = new ArrayList<>();
        StringTokenizer ss = new StringTokenizer(s, ",");
        while (ss.hasMoreTokens()) {
            int num = parseInt(ss.nextToken());
            if (num % 2 == 0) {
                chan.add(num);
            } else {
                le.add(num);
            }
        }
        //c. Sắp xếp tăng dần các giá trị chẵn và sau đó tăng dần các giá trị lẻ trong dãy số.
        Collections.sort(chan, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : -1;
            }
        });
        Collections.sort(le, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : -1;
            }
        });
        //Gửi chuỗi được sắp xếp này lên server.
        String answer = String.valueOf(chan) + ";" + String.valueOf(le);
        System.out.println(answer);
        os.write(answer.getBytes());
        //d. Đóng kết nối và kết thúc chương trình.
        os.close();
        is.close();
    }
}
