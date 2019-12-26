package company.com;

import java.net.*;
import java.io.*;

public class ServerSide2 {

	public static void main(String[] args) {
		try {
			ServerConnection conn = new ServerConnection(new ServerSocket(4011));
			conn.toDoDataAlgorithm();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}