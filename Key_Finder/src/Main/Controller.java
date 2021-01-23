package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.omg.CORBA.INTERNAL;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
public class Controller implements Initializable
{
    public Label counter;
    public Label avg;
    int index_i;
    int index_j;
    public void check_key(ActionEvent actionEvent) throws IOException
    {
        Button btn = (Button) actionEvent.getSource();
        String btn_text[] = btn.getText().split( " ");
        System.out.print(btn_text[0]+" i ,  "+  btn_text[1]+" j");
        int  i = Integer.parseInt(btn_text[0]);
        int  j = Integer.parseInt(btn_text[1]);
        double  j_double= Math.pow(Math.abs(index_j - j ),2);
        double  i_double= Math.pow(Math.abs(index_i - i ),2);
        double distance = Math.pow(i_double+j_double,0.5);
        int floor_val = (int) Math.floor(distance);
        if(i==index_i&&j==index_j)
        {


            Image img = new Image("Resources/key_48px.png");
            ImageView view = new ImageView(img);
            //view.setSmooth(true);
            view
                    .setFitHeight(37.0);
            btn.setGraphic(view);
            btn.setTextFill(Paint.valueOf("0xFFFF00"));
            btn.setStyle("-fx-background-color: yellow");
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Found the key ", ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK)
            {
                ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("Main_Screen.fxml").openStream());

                Controller controller = loader.getController();
                controller.set_counter(attempt+1,counter_num);
                Scene scene = new Scene(root);
                primaryStage.setTitle("DashBoard");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            return;
        }
        else if((i==index_i&&j==index_j-1) || (i==index_i-1&&j==index_j) || (i==index_i&&j==index_j+1) || ( i==index_i+1 &&j==index_j) || floor_val == 1)
        {
            btn.setStyle("-fx-background-color: red");
            btn.setTextFill(Paint.valueOf("0xFF0000"));
        }
        else if((i==index_i&&j==index_j-2) || (i==index_i-2&&j==index_j) || (i==index_i&&j==index_j+2) || ( i==index_i+2&&j==index_j) ||  floor_val == 2)
        {
            btn.setStyle("-fx-background-color: CRIMSON");
            btn.setTextFill(Paint.valueOf("0xDC143C"));
        }
        else if((i==index_i&&j==index_j-3) || (i==index_i-3&&j==index_j) || (i==index_i&&j==index_j+3) || ( i==index_i+3&&j==index_j) ||  floor_val == 3)
        {
            btn.setStyle("-fx-background-color: pink");
            btn.setTextFill(Paint.valueOf("0xFFC0CB"));
        }
        else if((i==index_i&&j==index_j-4) || (i==index_i-4&&j==index_j) || (i==index_i&&j==index_j+4) || ( i==index_i+4&&j==index_j) ||  floor_val == 4)
        {
            btn.setStyle("-fx-background-color: purple");
            btn.setTextFill(Paint.valueOf("0x800080"));
        }
        else
        {
            btn.setStyle("-fx-background-color: lightblue");
            btn.setTextFill(Paint.valueOf("0xADD8E6"));
        }
        counter_num++;
        counter.setText(String.valueOf(counter_num));
    }
    private void set_counter(int i, int counter_num)
    {
        attempt = i;
    }
    int attempt;
    int counter_num = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Random random = new Random();
        index_i = random.nextInt(8);
        index_j = random.nextInt(8);
    }
}