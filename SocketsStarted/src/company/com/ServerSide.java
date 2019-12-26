package company.com;

import java.net.*;
import java.io.*;

public class ServerSide {

	public static void main(String[] args) {
		try {
			ServerConnection conn = new ServerConnection(new ServerSocket(4010));//создаем сокет для сервера
			conn.toDoDataAlgorithm();//выполняем операции с получеными данными отправляем результат обратно
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}