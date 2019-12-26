package company.com;

import java.net.*;
import java.io.*;

public class ClientSide {
	static int res1 = 0;
	static int res2 = 0;

    public static void main(String[] args) {
        try {
        	//генерируем количество символов в массиве и формируем строку
        	Integer amountChar = 100000000;
			char[] massChar = new char[amountChar];
			for (int i = 0; i < amountChar; i++) {
				massChar[i] = (Math.random() < 0.5) ? (char) (Math.random() * 25 + 97) : (char) (Math.random() * 25 + 65);
			}
			String myStroka = String.valueOf(massChar);
        	
        	//создаем сокеты и отправляем данные
			ClientConnection conn = new ClientConnection(new Socket("localhost", 4010));
			ClientConnection conn2 = new ClientConnection(new Socket("192.168.137.37", 4011));
			
			String str1 = myStroka.substring(0, amountChar/2);
			String str2 = myStroka.substring(amountChar/2, amountChar);
			
			long startTime = System.nanoTime();
			Runnable task = new Runnable() {
	            public void run() {
	            	
	    			res1 = conn.sendData(str1);
	            }
	        };
	        Thread thread = new Thread(task);
	        thread.start();
			
	        Runnable task2 = new Runnable() {
	            public void run() {
	    		    res2 = conn2.sendData(str2);
	            }
	        };
	        Thread thread2 = new Thread(task2);
	        thread2.start();
			
	        //выводим результирующее найденое число символов в строке
			try {
				thread.join();
				thread2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long stopTime = System.nanoTime();
		    long elapsedTime = stopTime - startTime;
		    System.out.println("Время выполнения потоков: " + elapsedTime/100000000);
	        System.out.println("Количество символов: " + (res1 + res2));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
}
