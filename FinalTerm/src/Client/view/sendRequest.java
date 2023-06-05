package Client.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class sendRequest {
	public String Fetch(String json,String url,int port) {
        String res="";
        String sentence_to_server;
        try {
            Socket socket = new Socket(url, port);
            sentence_to_server = json;
            OutputStreamWriter outToServer =
                    new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            BufferedReader inFromServer =
                    new BufferedReader(new
                            InputStreamReader(socket.getInputStream(), "UTF-8"));
            outToServer.write(sentence_to_server + '\n');
            outToServer.flush();
//            res = inFromServer.readLine();
            socket.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return res;
    }
}
