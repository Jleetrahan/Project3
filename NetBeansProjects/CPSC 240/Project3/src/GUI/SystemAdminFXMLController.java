package GUI;

import basicStuff.LoginAccount;
import bike.BikeDB;
import bike.OfficeMan;
import bike.SalesAssociate;
import bike.SysAdmin;
import bike.WarehouseManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Jackson Trahan
 */
public class SystemAdminFXMLController implements Initializable {
    @FXML
    TabPane tabPane;
    @FXML
    ComboBox manageAccountsCB, manageAccountType, userCB;
    @FXML
    Text firstNameLabel, lastNameLabel, usernameLabel, passwordLabel, emailLabel;
    @FXML
    TextField firstNameField, lastNameField, usernameField, passwordField, emailField;
    @FXML
    Button addAccountButton, deleteAccountButton, resetPassButton;
    @FXML
    ListView listView;
    
    ArrayList<Node> added = new ArrayList();
    Node[] nodes;
    Node[] addAccountVis;
    Node[] deleteAccountVis;
    Node[] resetPassVis;
    
    @FXML
    private void logOut() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        try {
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = (Stage) tabPane.getScene().getWindow();
            stage.setScene(new Scene(root));  
            stage.show();
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void switchMenu(ActionEvent event) {
        System.out.println(manageAccountsCB.getValue().toString().toLowerCase());
        ArrayList<Node> visable = new ArrayList();
        switch (manageAccountsCB.getValue().toString().toLowerCase()) {
            case "add account":
                visable = new ArrayList(Arrays.asList(addAccountVis));
                break;
            case "delete account":
                visable = new ArrayList(Arrays.asList(deleteAccountVis));
                break;
            case "reset password":
                visable = new ArrayList(Arrays.asList(resetPassVis));
                break;
        }
        for (Node n : nodes) {
            if (visable.contains(n))
                n.setVisible(true);
            else
                n.setVisible(false);
        }
    }
    
    @FXML
    private void addUser(ActionEvent event) throws Exception {
        switch (manageAccountType.getValue().toString().toLowerCase()) {
            case "office manager":
                BikeDB.getDB().addUser(new OfficeMan(firstNameField.getText(),
                lastNameField.getText(), emailField.getText(), usernameField.getText(),
                passwordField.getText()));
                break;
            case "warehouse manager":
                BikeDB.getDB().addUser(new WarehouseManager(firstNameField.getText(),
                lastNameField.getText(), emailField.getText(), usernameField.getText(),
                passwordField.getText()));
                break;
            case "sales associate":
                BikeDB.getDB().addUser(new SalesAssociate(firstNameField.getText(),
                lastNameField.getText(), emailField.getText(), usernameField.getText(),
                passwordField.getText()));
                break;
            case "system admin":
                BikeDB.getDB().addUser(new SysAdmin(firstNameField.getText(),
                lastNameField.getText(), emailField.getText(), usernameField.getText(),
                passwordField.getText()));
                break;
        }
        resetLists();
    }
    
    @FXML
    private void deleteUser(ActionEvent event) throws Exception {
        System.out.println("Please");
        for (LoginAccount la : BikeDB.getDB().getUsers()) {
            System.out.println(la.getUsername() + ", " + userCB.getValue().toString());
            if (la.getUsername().equals(userCB.getValue().toString())) {
                System.out.println("HI");
                BikeDB.getDB().removeUser(la);
                break;
            }
        }
        resetLists();
    }
    
    @FXML
    private void resetPass(ActionEvent event) {
        for (LoginAccount user : BikeDB.getDB().getUsers()) {
            if (user.getUsername().equals(userCB.getValue().toString())) {
                user.reset();
            }
        }
        resetLists();
    }
    
    private void resetLists() {
        listView.setItems(FXCollections.observableArrayList(BikeDB.getDB().getUsers()));
        userCB.setItems(FXCollections.observableArrayList(BikeDB.getDB().getUsers()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setItems(FXCollections.observableArrayList(BikeDB.getDB().getUsers()));
        manageAccountType.setItems(FXCollections.observableArrayList(new String[] {"Office Manager", "Warehouse Manager", "Sales Associate", "System Admin"}));
        manageAccountsCB.setItems(FXCollections.observableArrayList(new String[] {"Add Account", "Delete Account", "Reset Password"}));
        userCB.setItems(FXCollections.observableArrayList(BikeDB.getDB().getUsers()));
        
        nodes = new Node[] {firstNameLabel, lastNameLabel, usernameLabel, 
            passwordLabel, emailLabel, firstNameField, lastNameField, usernameField,
            passwordField, emailField, addAccountButton, deleteAccountButton, manageAccountType,
            listView, userCB, resetPassButton};
        
        addAccountVis = new Node[] {firstNameLabel, lastNameLabel, usernameLabel, 
            passwordLabel, emailLabel, firstNameField, lastNameField, usernameField,
            passwordField, emailField, addAccountButton, manageAccountType, listView};
        
        deleteAccountVis = new Node[] {listView, userCB, deleteAccountButton};
        
        resetPassVis = new Node[] {listView, userCB, resetPassButton};
    }
}
