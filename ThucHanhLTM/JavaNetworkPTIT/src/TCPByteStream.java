import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class TCPByteStream{
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // Kết nối tới server tại địa chỉ "localhost" và cổng 806
            socket = new Socket("203.162.10.109", 2206);
            socket.setSoTimeout(5000); // Thiết lập thời gian timeout là 5 giây

            // Tạo luồng đầu vào và đầu ra
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // a. Gửi mã sinh viên và mã câu hỏi
            String studentCode = "B21DCCN441";
            String qCode = "BymEqvLV";
            String message = studentCode + ";" + qCode;
            outputStream.write(message.getBytes());
            outputStream.flush(); // Đẩy dữ liệu đi ngay lập tức

            // b. Nhận dữ liệu từ server
            byte[] receiveData = new byte[1024];
            int bytesRead = inputStream.read(receiveData);
            String serverResponse = new String(receiveData, 0, bytesRead);

            // Phân tách chuỗi thành các số nguyên
            String[] numbersStr = serverResponse.split("\\|");
            int[] numbers = Arrays.stream(numbersStr).mapToInt(Integer::parseInt).toArray();

            // c. Tính tổng các số nguyên
            int sum = Arrays.stream(numbers).sum();

            // Gửi tổng lên server
            String sumMessage = String.valueOf(sum);
            outputStream.write(sumMessage.getBytes());
            outputStream.flush();

            // d. Đóng kết nối và kết thúc chương trình
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
