import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPString{
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Địa chỉ server và port
            String serverAddress = "203.162.10.109";
            int port = 2208;

            // Mở socket UDP
            socket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName(serverAddress);

            // Chuỗi chứa mã sinh viên và mã câu hỏi
            String studentCode = "B21DCCN441";
            String qCode = "5REpehsp";
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
            String data = parts[1]; // Lấy data

            // Chuẩn hóa chuỗi: ký tự đầu của từ là in hoa, còn lại in thường
            String normalizedData = normalizeString(data);

            // Chuẩn bị và gửi thông điệp chứa chuỗi đã chuẩn hóa lên server
            String resultMessage = requestId + ";" + normalizedData;
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

    // Hàm chuẩn hóa chuỗi theo yêu cầu
    private static String normalizeString(String input) {
        String[] words = input.split(" ");
        StringBuilder normalizedString = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                normalizedString.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        // Xóa khoảng trắng dư thừa cuối chuỗi
        return normalizedString.toString().trim();
    }
}