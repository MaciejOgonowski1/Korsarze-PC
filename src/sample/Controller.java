package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller
{

    public GridPane opponentGrifdPane;

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
        //System.out.println(opponentGrifdPane.getColumnIndex();
    }

    public void onMyMapClick(MouseEvent mouseEvent) {
    }

    public void onMyMapEntered(MouseEvent mouseEvent) {
    }

    public void onOpMapEntered(MouseEvent mouseEvent) {
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
