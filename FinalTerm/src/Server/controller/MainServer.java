package Server.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Client.model.Account;
import Client.view.sendRequest;
import DAOs.Condb;

public class MainServer {
	public final static int port = 3000;
	
	public MainServer() {
		// TODO Auto-generated constructor stub
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server running");
			
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Connected " + socket.getInetAddress());
				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				System.out.println(dataInputStream.readUTF());
				
				Thread thread = new Thread (new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							
							
							String dataFromUser = dataInputStream.readUTF();
							System.out.println(dataFromUser);
							JsonObject jobj = new Gson().fromJson(dataFromUser, JsonObject.class);
							String method = jobj.get("method").toString();
							System.out.println(method);
							String checkMethod = removeNgoac(method);
							switch (checkMethod) {
							case "DAOsRegister":
								Account account = new Account(removeNgoac(jobj.getAsJsonObject("data").get("username").toString()),
																removeNgoac(jobj.getAsJsonObject("data").get("password").toString()),
																removeNgoac(jobj.getAsJsonObject("data").get("phone").toString()),
																removeNgoac(jobj.getAsJsonObject("data").get("email").toString()));
								account.toString();
								break;

							default:
								break;
							}
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				});
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
//	public void Register(Account acc) {
//		Account account = 
//	}
	// Xu ly ngoac kep
	public String removeNgoac(String input) {
		String output = "";
		for (int i=1; i<input.length()-1;i++) {
			output+=input.charAt(i);
		}
		return output;
	}
	public static void main(String[] args) {
		new MainServer();
	}
}
