package GUI;

import basicStuff.LoginAccount;
import bike.BikeDB;
import bike.OfficeMan;
import bike.SalesAssociate;
import bike.SysAdmin;
import bike.WarehouseManager;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Jackson Trahan
 */
public class SignInFXMLController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private AnchorPane ap;
    
    @FXML
    private void signIn(ActionEvent event) throws Exception {
        BikeDB db = BikeDB.getDB();
        for (LoginAccount user : db.getUsers()) {
            if (user.validate(username.getText(), password.getText())) {
                Parent root = null;
                if (OfficeMan.class.isInstance(user)) {
                    root = FXMLLoader.load(getClass().getResource("OfficeManager.fxml"));
                } else if (SalesAssociate.class.isInstance(user)) {
                    root = FXMLLoader.load(getClass().getResource("SalesAssociate.fxml"));
                } else if (SysAdmin.class.isInstance(user)) {
                    root = FXMLLoader.load(getClass().getResource("SystemAdmin.fxml"));
                } else if (WarehouseManager.class.isInstance(user)) {
                    root = FXMLLoader.load(getClass().getResource("WarehouseManager.fxml"));
                }
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                db.switchUser(user);
                break;
            }
        }
    }
    
    @FXML
    private void quit(ActionEvent e) {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
