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
    int trafienieSerwera=0;
    int trafienieKlienta=0;

    public void setTrafienieSerwera(int trafienieSerwera) {
        this.trafienieSerwera = trafienieSerwera;
    }

    public int getTrafienieSerwera() {
        return trafienieSerwera;
    }

    public void setTrafienieKlienta(int trafienieKlienta) {
        this.trafienieKlienta = trafienieKlienta;
    }

    public int getTrafienieKlienta() {
        return trafienieKlienta;
    }

    public void startSerwer() throws Exception {

        ServerSocket welcomeSocket = new ServerSocket(6789);
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        int serwerGotowy=0;

        int klientGotowy=0;
        boolean flaga=true;
        int[] wspolrzedneSerwer = new int[2];
        int[] wspolrzedneKlient = new int[2];


        serwerGotowy=inFromClient.read();
        if(serwerGotowy==1)//Klient ulozyl statki
        {
            if(klientGotowy==1) {//dodac czekanie az serwer ulozy statki
                outToClient.write(klientGotowy);
                wspolrzedneSerwer[0] = inFromClient.read();
                wspolrzedneSerwer[1] = inFromClient.read();
                setTrafienieSerwera(0);//dodac sprawdzenie czy serwer trafiony czy nie
                 while (flaga) {
                    if (getTrafienieSerwera() == 1)//trafiony serwer
                    {
                        setTrafienieKlienta(2);
                        outToClient.write(getTrafienieSerwera());
                        wspolrzedneSerwer[0] = inFromClient.read();
                        wspolrzedneSerwer[1] = inFromClient.read();
                        setTrafienieSerwera(1);//sprawdzenie czy serwer trafiony
                        outToClient.write(getTrafienieSerwera());
                        break;

                    }
                    else if (getTrafienieSerwera() == 0)//nietrafiony serwer
                    {
                        setTrafienieSerwera(2);
                        //wybranie wspolrzednych strzalu serwera
                        outToClient.write(wspolrzedneKlient[0]);
                        outToClient.write(wspolrzedneKlient[1]);
                        setTrafienieKlienta(inFromClient.read());
                        break;
                    }
                    else if(getTrafienieKlienta()==1)
                    {
                        setTrafienieSerwera(2);
                        outToClient.write(wspolrzedneKlient[0]);
                        outToClient.write(wspolrzedneKlient[1]);
                        setTrafienieKlienta(inFromClient.read());
                        break;
                    }
                    else if (getTrafienieKlienta()==0)
                    {
                        setTrafienieKlienta(2);
                        wspolrzedneSerwer[0] = inFromClient.read();
                        wspolrzedneSerwer[1] = inFromClient.read();
                        setTrafienieSerwera(1);//sprawdzenie czy serwer trafiony
                        outToClient.write(getTrafienieSerwera());
                        break;
                    }
                    else
                    {
                        System.out.println("Blad przy trafieniu");
                    }
                }
            }
            else
            {
                System.out.println("Blad z gotowoscia klienta");
            }


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
