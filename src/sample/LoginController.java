package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;

public class LoginController {
    @FXML
    public TextField loginText;

    String LoginGracza;

    public void setLoginGracza(String login)
    {
        this.LoginGracza=login;
    }

    public String getLoginGracza()
    {
        return this.LoginGracza;
    }

    public void OK()
    {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błąd w formularzu logowania");
        alert.setContentText("Nie uzupełniłeś swojego imienia");
        alert.initModality(Modality.APPLICATION_MODAL);


        String login;
        login=loginText.getText();
        if(login.isEmpty())
        {
            System.out.println("Nic nie wpisałeś");
            alert.show();
        }
        else
        {
            setLoginGracza(login);
            System.out.println(getLoginGracza());
        }

    }
}
