import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws SocketException {
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket("Localhost", 1234)) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            while (true) {
                System.out.println("Введите выражение через пробел: ");
                String request = scanner.nextLine();
                if (request.equals("end"))
                    break;
                if (request.contains(",")) {
                    System.out.println("Не корректный ввод");
                    continue;
                }
                if (!(request.contains(" "))) {
                    System.out.println("Не корректный ввод");
                    continue;
                }
                // String[] value = request.split(" ");
                // for (int i = 0; i < value.length; i = i + 2) {
                // String[] string = value[i].split("");
                // String find =
                // "qwertyuiop[]asdfghjkl;'zxcvbnm/йцукенгшщзхъфывапролджэячсмитьбю!\"№%:?()ё~`@#$^&{}><";
                // for (int j = 0; j < string.length; j++) {
                // if (find.contains(string[j])){
                // string[j] = "";
                // }
                // }
                // }
                dataOutputStream.writeUTF(request);
                System.out.println(dataInputStream.readUTF());
            }
            scanner.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
