
import UDP.Product;
import java.io.*;
import java.net.*;

public class UDPObject {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Địa chỉ server và port
            String serverAddress = "203.162.10.109";
            int port = 2209;

            // Mở socket UDP
            socket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName(serverAddress);

            // Chuỗi chứa mã sinh viên và mã câu hỏi
            String studentCode = "B21DCCN441";
            String qCode = "BZUWrXnd";
            String message = ";" + studentCode + ";" + qCode;

            // Gửi thông điệp lên server
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, port);
            socket.send(sendPacket);

            // Nhận thông điệp chứa requestId và đối tượng Product từ server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            // Tách requestId (08 byte đầu tiên)
            String requestId = new String(receivePacket.getData(), 0, 8);

            // Đọc đối tượng Product từ dữ liệu nhận được
            ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 8, receivePacket.getLength() - 8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Product product = (Product) ois.readObject();

            // Sửa thông tin tên sản phẩm và số lượng
            product.setName(fixProductName(product.getName()));
            product.setQuantity(fixProductQuantity(product.getQuantity()));

            // Gửi lại đối tượng đã sửa đổi lên server
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(product);

            // Chuẩn bị gói tin gửi, bao gồm requestId và đối tượng Product
            byte[] productData = baos.toByteArray();
            byte[] resultData = new byte[8 + productData.length];
            System.arraycopy(requestId.getBytes(), 0, resultData, 0, 8);
            System.arraycopy(productData, 0, resultData, 8, productData.length);

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

    // Hàm sửa tên sản phẩm
    private static String fixProductName(String name) {
        String[] words = name.split(" ");
        if (words.length >= 2) {
            String temp = words[0];
            words[0] = words[words.length - 1];
            words[words.length - 1] = temp;
        }
        return String.join(" ", words);
    }

    // Hàm đảo ngược số lượng sản phẩm
    private static int fixProductQuantity(int quantity) {
        String quantityStr = Integer.toString(quantity);
        String reversedQuantityStr = new StringBuilder(quantityStr).reverse().toString();
        return Integer.parseInt(reversedQuantityStr);
    }
}