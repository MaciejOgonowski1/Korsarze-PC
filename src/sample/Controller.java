package sample;

import javafx.event.ActionEvent;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
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
    public Label messageLabel;
    public Button grajButton;
    private boolean  waterGenerated = false;
    private Integer rowIndex;
    private Integer columnIndex;
    private int gameState = 0;  //0 - przygotowanie do gry, 1 - w trakcie, 20 - tura moja, 21 - tura przeciwnika, 3 - po;
    private Board myBoard = new Board();
    private Board opponentBoard = new Board(20);
    private GrajController polaczenie = new GrajController();

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

    public void onOpMapClick(MouseEvent mouseEvent) throws IOException {
        if (gameState == 0)
            messageLabel.setText("Najpierw przygotuj się do bitwy");
        if (gameState == 1)
            messageLabel.setText("Połącz się z przeciwnikiem");
        if (gameState == 21)
            messageLabel.setText("Tura przeciwnika");
        if (gameState == 20)
        {
            Node target = (Node) mouseEvent.getTarget();
            // traverse towards root until userSelectionGrid is the parent node
            if (target != opponentGrifdPane) {
                Node parent;
                while ((parent = target.getParent()) != opponentGrifdPane) {
                    target = parent;
                }
            }
            columnIndex = opponentGrifdPane.getColumnIndex(target);
            rowIndex = opponentGrifdPane.getRowIndex(target);
            if (columnIndex == null || rowIndex == null) {
                System.out.println("BOO");
            } else {
                System.out.printf("Mouse entered cell [%d, %d]%n", columnIndex.intValue(), rowIndex.intValue());
            }

            if (opponentBoard.getField(columnIndex, rowIndex) == 0)
            {
                opponentBoard.setField(1, columnIndex, rowIndex);
                Pane pole = new Pane();
                if (askOpponent(columnIndex.intValue(), rowIndex.intValue()) == 0)//pudło
                {
                    pole.setStyle("-fx-background-color: blue");
                    opponentGrifdPane.add(pole, columnIndex.intValue(), rowIndex.intValue());
                    messageLabel.setText("Pudło. Teraz tura przeciwnika");
                    gameState = 21;
                    opponentMove();
                }
                else //trafienie
                {
                    pole.setStyle("-fx-background-color: yellow");
                    opponentGrifdPane.add(pole, columnIndex.intValue(), rowIndex.intValue());
                    opponentBoard.decrementShipCounter();
                    if (opponentBoard.getShip() > 0)
                        messageLabel.setText("Trafienie. Atakuj ponownie");
                    else {
                        messageLabel.setText("Odniosłeś wspaniałe zwycięstwo");
                        gameState = 3;
                        ///podsumowanie
                    }
                }
            }
            else
                messageLabel.setText("pole już atakowane");

        }
    }

    public void onMyMapClick(MouseEvent mouseEvent)
    {
        if(gameState == 0)
        {
            Node target = (Node) mouseEvent.getTarget();
            // traverse towards root until userSelectionGrid is the parent node
            if (target != myGridPane) {
                Node parent;
                while ((parent = target.getParent()) != myGridPane) {
                    target = parent;
                }
            }
            columnIndex = myGridPane.getColumnIndex(target);
            rowIndex = myGridPane.getRowIndex(target);
            if (columnIndex == null || rowIndex == null) {
                System.out.println("BOO");
            } else {
                System.out.printf("Mouse entered cell [%d, %d]%n", columnIndex.intValue(), rowIndex.intValue());
            }


            //ustaw statek
            if(myBoard.getField(columnIndex, rowIndex) == 0)
            {
                if (myBoard.getShip()<20)
                {
                    myBoard.setField(1, columnIndex, rowIndex);
                    myBoard.incrementShipCounter();
                    Pane statek = new Pane();
                    statek.setStyle("-fx-background-color: yellow");
                    myGridPane.add(statek, columnIndex.intValue(), rowIndex.intValue());
                }
                else
                    messageLabel.setText("Twoja flota jest wystarczająco duża. Możesz rozpocząć grę");
            }
            else if(myBoard.getField(columnIndex, rowIndex) == 1)
            {
                myBoard.setField(0,columnIndex, rowIndex);
                myBoard.decrementShipCounter();
                Pane woda = new Pane();
                woda.setStyle("-fx-background-color: blue");
                myGridPane.add(woda, columnIndex.intValue(), rowIndex.intValue());
                messageLabel.setText("Rozstaw swoją flotę na prawej planszy");
            }
        }
        else
            messageLabel.setText("Już nie możes zmienić ustawienia floty");

    }

    public void onMyMapEntered(MouseEvent mouseEvent)
    {
        if(!waterGenerated) {
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 10; j++) {
                    Pane nieznane = new Pane();
                    nieznane.setStyle("-fx-background-color: grey");
                    Pane woda = new Pane();
                    woda.setStyle("-fx-background-color: blue");
                    opponentGrifdPane.add(nieznane, i, j);
                    myGridPane.add(woda, i, j);
                }
            waterGenerated = true;

        }

    }

    public void onOpMapEntered(MouseEvent mouseEvent)
    {
        if(!waterGenerated) {
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 10; j++) {
                    Pane nieznane = new Pane();
                    nieznane.setStyle("-fx-background-color: grey");
                    Pane woda = new Pane();
                    woda.setStyle("-fx-background-color: blue");
                    opponentGrifdPane.add(nieznane, i, j);
                    myGridPane.add(woda, i, j);
                }
            waterGenerated = true;
            messageLabel.setText("Rozstaw swoją flotę na prawej planszy");
        }
    }

    public void serwer() throws Exception
    {
        if (gameState == 1)
        {
            polaczenie.startSerwer2();
            gameState = 21;
            opponentMove();
        }
    }

    public void klient() throws Exception
    {
        if (gameState == 1)
        {
            polaczenie.startKlient2();
            gameState = 20;
        }
    }

    public void onGrajClick(ActionEvent actionEvent)
    {
        if (gameState == 0)
            if(myBoard.getShip() == 20)
            {
                gameState = 1;
                grajButton.setVisible(false);
                messageLabel.setText("Połącz się z przeciwnikiem");
            }
            else
                messageLabel.setText("Rozstaw statki na 20 polach");
    }
    public void opponentMove() throws IOException {
        while(gameState == 21)
        {
            int x = polaczenie.getData();
            int y = polaczenie.getData();
            int answer = myBoard.getField(x,y);
            polaczenie.sendData(answer);
            if (answer == 1)//trafienie
            {
                messageLabel.setText("Trafienie");
                Pane statek = new Pane();
                statek.setStyle("-fx-background-color: red ");
                myGridPane.add(statek, x, y);
                myBoard.decrementShipCounter();
                if (myBoard.getShip() <= 0) {
                    messageLabel.setText("Porażka");
                    gameState = 3;
                    //podsumowanie
                }
            }
            else
            {
                messageLabel.setText("Pudło. Twój ruch");
                Pane statek = new Pane();
                statek.setStyle("-fx-background-color: navy ");
                myGridPane.add(statek, x, y);
                gameState = 20;
            }
        }
    }

    public int askOpponent(int x, int y) throws IOException
    {
        polaczenie.sendData(x);
        polaczenie.sendData(y);
        return polaczenie.getData();
    }

}
