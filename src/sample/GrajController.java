package sample;

import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class GrajController
{
    public GridPane opponentGrifdPane;
    public GridPane myGridPane;


    public void startSerwer() throws Exception {
        System.out.println("Serwer Test wlaczaony");
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        String zmienna;
        zmienna = "Cos dodatkowego";
        int wynik;
        int wyslac;
        int[] tab = new int[3];

        while (true) {

            clientSentence = inFromClient.readLine();
            System.out.println("Klient: " + clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + zmienna + '\n';
            outToClient.writeBytes(capitalizedSentence);


            wynik = inFromClient.read();
            System.out.println("Klient: " + wynik);
            outToClient.write((wynik));
            wynik = inFromClient.read();
            System.out.println("Klient: " + wynik);
            outToClient.write((wynik));
            wynik = inFromClient.read();
            System.out.println("Klient: " + wynik);
            outToClient.write((wynik));
            System.out.println("Koniec pierwszych danych");

        }
    }

}
