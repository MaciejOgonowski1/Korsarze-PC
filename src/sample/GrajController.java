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

    public void startKlient() throws Exception{
        String sentence;
        String modifiedSentence;

        Socket clientSocket = new Socket("localhost", 6789);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Klient Dziala");
        int[] tab=new int[3];
        int[] odb=new int[3];
        int wynik;
        tab[0]=12;
        tab[1]=15;
        tab[2]=1;

        while(true)
        {

            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);

            //tab[0] = inFromUser.read();
            outToServer.write(tab[0]);
            wynik = inFromServer.read();
            System.out.println("FROM SERVER: " + wynik);

            //tab[1] = inFromUser.read();
            outToServer.write(tab[1]);
            wynik = inFromServer.read();
            System.out.println("FROM SERVER: " + wynik);

            //tab[2] = inFromUser.read();
            outToServer.write(tab[2]);
            wynik = inFromServer.read();
            System.out.println("FROM SERVER: " + wynik);
            System.out.println("Koniec Danych");

        }
    }

}
