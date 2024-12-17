package TCP;

/*
[Mã câu hỏi (qCode): cPbHjG9z].  Một chương trình server cho phép kết nối qua giao thức TCP
tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). 
Yêu cầu là xây dựng một chương trình client thực hiện kết nối tới server và 
sử dụng luồng ký tự (BufferedWriter/BufferedReader) để trao đổi thông tin theo kịch bản sau:
a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;X1107ABC".
b. Nhận từ server một chuỗi ngẫu nhiên chứa nhiều từ, các từ phân tách bởi khoảng trắng.
c. Thực hiện các bước xử lý:
    Bước 1: Tách chuỗi thành các từ dựa trên khoảng trắng.
    Bước 2: Sắp xếp các từ theo thứ tự từ điển (có phân biệt chữ cái hoa thường).
d. Gửi lại chuỗi đã sắp xếp theo thứ tự từ điển lên server.
e. Đóng kết nối và kết thúc chương trình.
 */
import java.io.*;
import java.util.*;
import java.net.*;
public class TCP_CharacterStream {
    public static void main(String[] args) throws IOException {
        String code = "B21DCCN441;cPbHjG9z";
        Socket socket = new Socket("203.162.10.109",2208);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(code); bw.newLine();
        bw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = br.readLine();
        System.out.println(s);
        ArrayList<String> As = new ArrayList<>();
        StringTokenizer ss = new StringTokenizer(s);
        while(ss.hasMoreTokens()) As.add(ss.nextToken().trim());
        Collections.sort(As,new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }        
        });
        for(int i=0;i<As.size();i++){
            System.out.println(As.get(i));
            bw.write(As.get(i)+" ");
            bw.flush();
        }
        br.close();
        bw.close();
    }
}
