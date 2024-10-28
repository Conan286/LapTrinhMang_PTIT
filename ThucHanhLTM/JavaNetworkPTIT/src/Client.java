import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    
    // Hàm tính UCLN của 2 số nguyên
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Hàm tính BCNN của 2 số nguyên
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public static void main(String[] args) {
        String serverAddress = "172.188.19.218";  // Địa chỉ server
        int serverPort = 1605;  // Cổng server
        
        try {
            // Tạo kết nối tới server
            Socket socket = new Socket(serverAddress, serverPort);
            
            // Tạo luồng vào và ra
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Gửi mã sinh viên và mã câu hỏi
            String studentCode = "B21DCCN416";
            String qCode = "WNkndwm";
            String message = studentCode + ";" + qCode;
            dos.writeUTF(message);  // Gửi chuỗi lên server
            dos.flush();

            // Nhận 2 số nguyên a và b từ server
            int a = dis.readInt();
            int b = dis.readInt();

            // Tính UCLN và BCNN
            int ucln = gcd(a, b);
            int bcnn = lcm(a, b);

            // Gửi UCLN và BCNN lên server
            dos.writeInt(ucln);
            dos.writeInt(bcnn);
            dos.flush();

            // Đóng kết nối
            dis.close();
            dos.close();
            socket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}