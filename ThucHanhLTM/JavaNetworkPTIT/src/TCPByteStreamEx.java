
import java.io.*;
import java.net.*;

public class TCPByteStreamEx {
    private static final String SERVER_ADDRESS = "10.170.46.199"; // Địa chỉ server
    private static final int SERVER_PORT = 2207; // Cổng kết nối

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            // Tạo DataOutputStream và DataInputStream để gửi/nhận dữ liệu dạng byte
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            // a. Gửi chuỗi "studentCode;qCode"
            String studentCode = "B21DCCN441";
            String qCode = "C0vlZ7nK";
            String message = studentCode + ";" + qCode;
            outputStream.writeUTF(message); // Gửi chuỗi
            outputStream.flush();
            System.out.println("Sent: " + message);

            // b. Nhận lần lượt hai số nguyên a và b từ server
            int a = inputStream.readInt();
            int b = inputStream.readInt();
            System.out.println("Received: a = " + a + ", b = " + b);

            // c. Tính toán tổng và tích của a và b
            int sum = a + b;
            int product = a * b;

            // Gửi tổng
            outputStream.writeInt(sum);
            outputStream.flush();
            System.out.println("Sent sum: " + sum);

            // Gửi tích
            outputStream.writeInt(product);
            outputStream.flush();
            System.out.println("Sent product: " + product);

            // d. Đóng kết nối
            socket.close();
            System.out.println("Connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
