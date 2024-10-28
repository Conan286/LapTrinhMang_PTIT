import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPDataType {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Tạo socket UDP
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109"); // Địa chỉ server từ đề bài

            // a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi
            String studentCode = "B21DCCN441";
            String qCode = "03qIDmkV";
            String message = ";" + studentCode + ";" + qCode;
            
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 2207);
            socket.send(sendPacket); // Gửi thông điệp đến server
            
            // b. Nhận thông điệp từ server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket); // Nhận phản hồi từ server
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            
            // c. Phân tích dữ liệu nhận được
            String[] parts = serverResponse.split(";");
            String requestId = parts[0];
            String[] numbersStr = parts[1].split(",");
            int[] numbers = Arrays.stream(numbersStr).mapToInt(Integer::parseInt).toArray();

            // Tìm giá trị lớn nhất và nhỏ nhất trong mảng
            int max = Arrays.stream(numbers).max().getAsInt();
            int min = Arrays.stream(numbers).min().getAsInt();

            // Gửi lại kết quả lên server
            String responseMessage = requestId + ";" + max + "," + min;
            byte[] responseData = responseMessage.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, serverAddress, 2207);
            socket.send(responsePacket); // Gửi thông điệp max, min đến server
            
            // d. Đóng socket và kết thúc chương trình
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
