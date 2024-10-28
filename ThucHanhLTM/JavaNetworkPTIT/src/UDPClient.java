import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Địa chỉ server và port
            String serverAddress = "203.162.10.109";
            int port = 2207;

            // Mở socket UDP
            socket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName(serverAddress);

            // Chuỗi chứa mã sinh viên và mã câu hỏi
            String studentCode = "B21DCCN441";
            String qCode = "MM3l4VHB";
            String message = ";" + studentCode + ";" + qCode;

            // Gửi thông điệp lên server
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, port);
            socket.send(sendPacket);

            // Nhận thông điệp từ server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Phân tích chuỗi nhận được từ server
            String[] parts = receivedMessage.split(";");
            String requestId = parts[0]; // Lấy requestId
            String[] numbers = parts[1].split(","); // Lấy danh sách các số nguyên z1 -> z50
            int[] intNumbers = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();

            // Tìm số lớn thứ hai và số nhỏ thứ hai
            Arrays.sort(intNumbers);
            int secondMax = intNumbers[intNumbers.length - 2];
            int secondMin = intNumbers[1];

            // Chuẩn bị và gửi thông điệp chứa kết quả lên server
            String resultMessage = requestId + ";" + secondMax + "," + secondMin;
            byte[] resultData = resultMessage.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, serverIP, port);
            socket.send(resultPacket);

            // Đóng socket và kết thúc chương trình
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
