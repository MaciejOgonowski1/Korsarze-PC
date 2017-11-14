package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    public void clickOpcje()
    {
        System.out.println("Opcje");
        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Korsarze PC");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
        */
        try {
          //  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Opcje.fxml"));
          //  Parent root1 = (Parent) fxmlLoader.load();
            Parent root = FXMLLoader.load(getClass().getResource("Opcje.fxml"));
            Stage opcje = new Stage();
            opcje.setTitle("Opcje");
            opcje.setScene(new Scene(root, 300, 400));
            opcje.show();
        }
        catch(Exception e)
        {
            System.out.println("Nie mogę otworzyć opcje");
        }

    }
    public void clickZaloguj()
    {
        System.out.println("Zaloguj");
        try {
            //  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Opcje.fxml"));
            //  Parent root1 = (Parent) fxmlLoader.load();
            Parent root1 = FXMLLoader.load(getClass().getResource("Graj.fxml"));
            Stage opcje = new Stage();
            opcje.setTitle("KorsarzePC");
            opcje.setScene(new Scene(root1, 300, 400));
            opcje.show();
        }
        catch(Exception e)
        {
            System.out.println("Nie mogę otworzyć Graj");
        }
    }
}
