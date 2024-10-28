package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private UserController userController;

    public ServerThread(Socket socket, UserController userController) {
        this.socket = socket;
        this.userController = userController;
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = dis.readUTF();
                System.out.println("Received from client: " + received);
                
                String[] parts = received.split(";");
                String command = parts[0];

                switch (command) {
                    case "GET_RANKING":
                        handleGetRanking();
                        break;
                    // Thêm các case khác tại đây để xử lý các lệnh khác
                    default:
                        System.out.println("Unknown command: " + command);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleGetRanking() throws IOException {
        System.out.println("Nhận yêu cầu GET_RANKING từ client");
        String rankingData = userController.getRanking();
        dos.writeUTF("RANKING;" + rankingData);
        System.out.println("Đã gửi dữ liệu ranking về client");
    }
}
