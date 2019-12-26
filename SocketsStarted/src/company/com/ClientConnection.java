package company.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientConnection {
	private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    
	public ClientConnection(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	public int sendData(String data) {
		int res = 0;
		try {
			out.write(data + "\n");
			out.flush();
			res = Integer.parseInt(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}

}
