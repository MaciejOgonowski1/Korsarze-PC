package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Controller
{

    public GridPane opponentGrifdPane;

    public ColumnConstraints x0;
    public ColumnConstraints x1;
    public ColumnConstraints x2;
    public ColumnConstraints x3;
    public ColumnConstraints x4;
    public ColumnConstraints x5;
    public ColumnConstraints x6;
    public ColumnConstraints x7;
    public ColumnConstraints x8;
    public ColumnConstraints x9;
    public RowConstraints y0;
    public RowConstraints y1;
    public RowConstraints y2;
    public RowConstraints y3;
    public RowConstraints y4;
    public RowConstraints y5;
    public RowConstraints y6;
    public RowConstraints y7;
    public RowConstraints y8;
    public RowConstraints y9;
    public GridPane myGridPane;
    private boolean  waterGenerated = false;

    public void clickOpcje()
    {
        System.out.println("Opcje");
        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Korsarze PC");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
        */
        try
        {
          //  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Opcje.fxml"));
          //  Parent root1 = (Parent) fxmlLoader.load();
            Parent root = FXMLLoader.load(getClass().getResource("Opcje.fxml"));
            Stage opcje = new Stage();
            opcje.setTitle("Opcje");
            opcje.setScene(new Scene(root, 300, 400));
            opcje.initModality(Modality.APPLICATION_MODAL);
            opcje.show();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
    public void clickZaloguj()
    {
        System.out.println("Zaloguj");
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("Graj.fxml"));
            Stage opcje = new Stage();
            opcje.setTitle("KorsarzePC");
            opcje.setScene(new Scene(root, 1280, 720));
            opcje.initModality(Modality.APPLICATION_MODAL);
            opcje.show();


        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void onOpMapClick(MouseEvent mouseEvent)
    {


        //System.out.println(opponentGrifdPane.getColumnIndex());
        //opponentGrifdPane.setStyle("-fx-background-color: yellow");
        Pane statek=new Pane();
        statek.setStyle("-fx-background-color: yellow");
        opponentGrifdPane.add(statek,0,0);

        System.out.println("Cos tam niby dziala");

        //Label statek2=new Label("Siemandero");
        //statek1.setStyle("-fx-border-color: yellow");
        //Image statek1=new Image(getClass().getResourceAsStream("C:\\Users\\Maciej\\Desktop\\Github\\KorsarzePC\\out\\production\\KorsarzePC\\sample\\Pictures\\ship.jpg"));
        //ImageView imageView = new ImageView();
        ///imageView.setImage(statek1);
        //Image statek1=new Image(getClass().getResourceAsStream("ship.jpg"));
        //opponentGrifdPane.getChildren().add(new ImageView(image));
       // Image image=new Image("ship.png");


        //opponentGrifdPane.add(image,0,2);
        //opponentGrifdPane.add(statek2,0,1);
    }

    public void onMyMapClick(MouseEvent mouseEvent)
    {


    }

    public void onMyMapEntered(MouseEvent mouseEvent)
    {
        if(!waterGenerated)
        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++)
            {
                Pane nieznane=new Pane();
                nieznane.setStyle("-fx-background-color: grey");
                Pane woda=new Pane();
                woda.setStyle("-fx-background-color: blue");
                opponentGrifdPane.add(nieznane, i, j);
                myGridPane.add(woda, i, j);
            }
        waterGenerated = true;

    }

    public void onOpMapEntered(MouseEvent mouseEvent)
    {
        if(!waterGenerated)
        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++)
            {
                Pane nieznane=new Pane();
                nieznane.setStyle("-fx-background-color: grey");
                Pane woda=new Pane();
                woda.setStyle("-fx-background-color: blue");
                opponentGrifdPane.add(nieznane, i, j);
                myGridPane.add(woda, i, j);
            }
        waterGenerated = true;
    }

    public void serwer() throws Exception
    {
        GrajController serwer=new GrajController();
        serwer.startSerwer();
    }

    public void klient() throws Exception
    {
        GrajController klient=new GrajController();
        klient.startKlient();
    }
}
