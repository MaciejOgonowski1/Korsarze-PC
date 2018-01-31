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
        System.out.println("Serwer wlaczony!!!");
        int serwerGotowy=0;
        int klientGotowy=0;
        boolean flaga=true;
        int[] wspolrzedneSerwer = new int[2];
        int[] wspolrzedneKlient = new int[2];


        klientGotowy=inFromClient.read();
        if(klientGotowy==1)//Klient ulozyl statki
        {
            System.out.println("Klient gotowy");
            serwerGotowy=1;//dodac czekanie az serwer ulozy statki
            if(serwerGotowy==1) {
                System.out.println("Serwer gotowy");
                outToClient.write(serwerGotowy);
                wspolrzedneSerwer[0] = inFromClient.read();
                wspolrzedneSerwer[1] = inFromClient.read();
                setTrafienieSerwera(0);//dodac sprawdzenie czy serwer trafiony czy nie
                outToClient.write(getTrafienieSerwera());
                System.out.println("Wspolrzedne serwera przed while"+wspolrzedneSerwer[0]+" "+wspolrzedneSerwer[1]+" trafienie "+getTrafienieSerwera());
                 while (flaga)
                 {
                     System.out.println("While");
                    if (getTrafienieSerwera() == 1)//trafiony serwer
                    {
                        setTrafienieKlienta(2);
                        wspolrzedneSerwer[0] = inFromClient.read();
                        wspolrzedneSerwer[1] = inFromClient.read();
                        setTrafienieSerwera(0);//sprawdzenie czy serwer trafiony
                        outToClient.write(getTrafienieSerwera());
                        System.out.println("Wspolrzedne Serwera sts=1"+wspolrzedneSerwer[0]+" "+wspolrzedneSerwer[1]+" trafienie "+getTrafienieSerwera());

                    }
                    else if (getTrafienieSerwera() == 0)//nietrafiony serwer
                    {
                        setTrafienieSerwera(2);
                        //wybranie wspolrzednych strzalu serwera
                        wspolrzedneKlient[0] = 12;
                        wspolrzedneKlient[1] = 6;
                        outToClient.write(wspolrzedneKlient[0]);
                        outToClient.write(wspolrzedneKlient[1]);
                        setTrafienieKlienta(inFromClient.read());
                        System.out.println("Wspolrzedne Serwera sts=0"+wspolrzedneKlient[0]+" "+wspolrzedneKlient[1]+" trafienie "+getTrafienieKlienta());
                    }
                    else if(getTrafienieKlienta()==1)
                    {
                        System.out.println("stk=0 Jestem");
                        setTrafienieSerwera(2);
                        wspolrzedneKlient[0] = 1;
                        wspolrzedneKlient[1] = 2;
                        outToClient.write(wspolrzedneKlient[0]);
                        outToClient.write(wspolrzedneKlient[1]);
                        setTrafienieKlienta(inFromClient.read());
                        System.out.println("Wspolrzedne klienta stk=1"+wspolrzedneKlient[0]+" "+wspolrzedneKlient[1]+" trafienie "+getTrafienieKlienta());
                        System.out.println("tutaj");
                    }
                    else if (getTrafienieKlienta()==0)
                    {
                        setTrafienieKlienta(2);
                        wspolrzedneSerwer[0] = inFromClient.read();
                        wspolrzedneSerwer[1] = inFromClient.read();
                        setTrafienieSerwera(1);//sprawdzenie czy serwer trafiony
                        outToClient.write(getTrafienieSerwera());
                        System.out.println("Wspolrzedne Serwera stk=0"+wspolrzedneSerwer[0]+" "+wspolrzedneSerwer[1]+" trafienie "+getTrafienieSerwera());
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
        Socket clientSocket = new Socket("localhost", 6789);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        System.out.println("Klient wlaczony!!!");
        int serwerGotowy = 0;
        int klientGotowy = 0;
        boolean flaga = true;
        int[] wspolrzedneSerwer = new int[2];
        int[] wspolrzedneKlient = new int[2];

        klientGotowy = 1;//warunek na ulozenie statkow klienta
        outToServer.write(klientGotowy);
        if (klientGotowy == 1) {
            System.out.println("Klient gotowy");
            serwerGotowy = inFromServer.read();
            if (serwerGotowy == 1) {
                        System.out.println("Serwer gotowy");
                        wspolrzedneSerwer[0] = 2;//ustawienie wspolrzednych strzalu
                        wspolrzedneSerwer[1] = 7;
                        outToServer.write(wspolrzedneSerwer[0]);
                        outToServer.write(wspolrzedneSerwer[1]);
                        setTrafienieSerwera(inFromServer.read());
                        System.out.println("Wspolrzedne serwera przed while" + wspolrzedneSerwer[0] + " " + wspolrzedneSerwer[1] + " trafienie " + getTrafienieSerwera());
                        while (flaga) {
                            System.out.println("While");
                            if (getTrafienieSerwera() == 1) {
                                setTrafienieKlienta(2);
                                wspolrzedneSerwer[0] = 2;//ustawienie wspolrzednych strzalu
                                wspolrzedneSerwer[1] = 3;
                                outToServer.write(wspolrzedneSerwer[0]);
                                outToServer.write(wspolrzedneSerwer[1]);
                                setTrafienieSerwera(inFromServer.read());
                                System.out.println("Wspolrzedne Serwera sts=1" + wspolrzedneSerwer[0] + " " + wspolrzedneSerwer[1] + " trafienie " + getTrafienieSerwera());
                            } else if (getTrafienieSerwera() == 0) {
                                setTrafienieSerwera(2);
                                wspolrzedneKlient[0] = inFromServer.read();
                                wspolrzedneKlient[1] = inFromServer.read();
                                setTrafienieKlienta(1);//sprawdzenie czy klient trafiony
                                outToServer.write((getTrafienieKlienta()));
                                System.out.println("Wspolrzedne klienta sts=0" + wspolrzedneKlient[0] + " " + wspolrzedneKlient[1] + " trafienie " + getTrafienieKlienta());
                            } else if (getTrafienieKlienta() == 1) {
                                setTrafienieSerwera(2);
                                wspolrzedneKlient[0] = inFromServer.read();
                                wspolrzedneKlient[1] = inFromServer.read();
                                setTrafienieKlienta(0);//wprawdzenie czy klient trafiony
                                outToServer.write(getTrafienieKlienta());
                                System.out.println("Wspolrzedne klienta stk=1" + wspolrzedneKlient[0] + " " + wspolrzedneKlient[1] + " trafienie " + getTrafienieKlienta());
                            } else if (getTrafienieKlienta() == 0) {
                                setTrafienieKlienta(2);
                                wspolrzedneSerwer[0] = 2;//ustawienie wspolrzednych strzalu
                                wspolrzedneSerwer[1] = 3;
                                outToServer.write(wspolrzedneSerwer[0]);
                                outToServer.write(wspolrzedneSerwer[1]);
                                setTrafienieSerwera(inFromServer.read());
                                System.out.println("Wspolrzedne klienta stk=0" + wspolrzedneSerwer[0] + " " + wspolrzedneSerwer[1] + " trafienie " + getTrafienieSerwera());
                            } else {
                        System.out.println("Blad przy trafieniu");
                    }

                }
            } else {
                System.out.println("Blad z gotowoscia serwera");
            }
        }
    }


}