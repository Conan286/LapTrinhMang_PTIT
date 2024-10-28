
import java.io.*;
import java.net.*;

public class TCPCharacterStreamEx{
    private static final String SERVER_ADDRESS = "203.162.10.109"; // Địa chỉ server
    private static final int SERVER_PORT = 2208; // Cổng kết nối

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            // Sử dụng BufferedWriter và BufferedReader để gửi/nhận dữ liệu
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // a. Gửi chuỗi "studentCode;qCode"
            String studentCode = "B21DCCN441";
            String qCode = "ElhzzbZ7";
            String message = studentCode + ";" + qCode;
            writer.write(message);
            writer.newLine(); // Kết thúc dòng
            writer.flush();
            System.out.println("Sent: " + message);

            // b. Nhận danh sách các tên miền từ server
            String domainList = reader.readLine();
            System.out.println("Received domain list: " + domainList);

            // c. Tìm các tên miền có đuôi ".edu"
            String[] domains = domainList.split(", ");
            StringBuilder eduDomains = new StringBuilder();
            for (String domain : domains) {
                if (domain.endsWith(".edu")) {
                    if (eduDomains.length() > 0) {
                        eduDomains.append(", ");
                    }
                    eduDomains.append(domain);
                }
            }

            // Gửi danh sách tên miền .edu lên server
            writer.write(eduDomains.toString());
            writer.newLine(); // Kết thúc dòng
            writer.flush();
            System.out.println("Sent .edu domains: " + eduDomains.toString());

            // d. Đóng kết nối
            socket.close();
            System.out.println("Connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
