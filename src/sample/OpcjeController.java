package sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpcjeController {
    public void login()
    {
        System.out.println("Login");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage opcje = new Stage();
            opcje.setTitle("NICK");
            opcje.setScene(new Scene(root, 300, 400));
            opcje.initModality(Modality.APPLICATION_MODAL);
            opcje.show();
        }
        catch(Exception e)
        {
            System.out.println("Nie mogę otworzyć login!!!");
        }
    }
    public void ustawienia()
    {
        System.out.println("Ustawienia");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ustawienia.fxml"));
            Stage opcje = new Stage();
            opcje.setTitle("Ustawienia");
            opcje.setScene(new Scene(root, 300, 400));
            opcje.initModality(Modality.APPLICATION_MODAL);
            opcje.show();
        }
        catch(Exception e)
        {
            System.out.println("Nie mogę otworzyć ustawien");
        }
    }
    public void statystyka()
    {
        System.out.println("Statystyki");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Statystyki.fxml"));
            Stage opcje = new Stage();
            opcje.setTitle("Statystyki");
            opcje.setScene(new Scene(root, 300, 400));
            opcje.initModality(Modality.APPLICATION_MODAL);
            opcje.show();
        }
        catch(Exception e)
        {
            System.out.println("Nie mogę otworzyć statystyk");
        }
    }
    public void oAplikajcji()
    {
        System.out.println("O aplikacji");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("OAplikacji.fxml"));
            Stage opcje = new Stage();
            opcje.setTitle("O Aplikacji");
            opcje.setScene(new Scene(root, 300, 300));
            opcje.initModality(Modality.APPLICATION_MODAL);
            opcje.show();
        }
        catch(Exception e)
        {
            System.out.println("Nie mogę otworzyć oAplikacji");
        }
    }
}
