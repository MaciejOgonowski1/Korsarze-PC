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
            System.out.println("Czy statki sa rozlozone?");
            wyslac=inFromClient.read();

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
        int dane=1;
        tab[0]=12;
        tab[1]=15;
        tab[2]=1;

        System.out.println("Klient rozlozyl statki czekam na serwer");
        outToServer.write(dane);
        wynik = inFromServer.read();
        System.out.println("FROM SERVER: " + wynik);
        int trafiony=1;

        while(true)
        {
            if(wynik==1) {//statki rozlozone poczatek gry strzela klient
                if (wynik == 1) { //przeslanie danych 0,1 koordy strzalu klienta
                    outToServer.write(tab[0]); //wyslanie danych do klienta
                    outToServer.write(tab[1]);
                    outToServer.write(tab[2]);
                    wynik = inFromServer.read(); //czy trafiono w statek
                    if(trafiony==1)//trafiony
                    {
                        outToServer.write(tab[0]); //wyslanie danych do klienta
                        outToServer.write(tab[1]);
                        outToServer.write(tab[2]);
                        wynik = inFromServer.read(); //czy trafiono w statek
                    }
                    else if (trafiony==0)//nie trafiony strzela klient
                    {
                        trafiony=0;
                        outToServer.write(trafiony);
                        odb[0] = inFromServer.read();
                        odb[1] = inFromServer.read();
                        odb[2] = inFromServer.read();
                        if(odb[2]==1)//serwer trafiony
                        {
                            trafiony=0;
                            outToServer.write(trafiony);
                            odb[0] = inFromServer.read();
                            odb[1] = inFromServer.read();
                            odb[2] = inFromServer.read();
                        }
                        else if(odb[2]==0)//serwer nie trafiony
                        {
                            trafiony=1;//strzela serwer
                        }
                    }
                }
                else if(wynik == 0) //
                {
                    System.out.println("Nie ulozone");

                }
            }

        }
    }

}
