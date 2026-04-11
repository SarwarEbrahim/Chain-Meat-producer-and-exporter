package com.example.chainmeatproducerandexporter.commonFiles;

import com.example.chainmeatproducerandexporter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class loginPageController
{
    @javafx.fxml.FXML
    private TextField userIdTF;
    @javafx.fxml.FXML
    private PasswordField passwordPF;
    @javafx.fxml.FXML
    private ComboBox<String> roleComboBox;
    @javafx.fxml.FXML
    private Label messageLabel;

    private User qualityInspectorUser;
    private User inventoryManagerUser;
    private User SupplierUser;
    private User ExportDeliveryCoordinator;

    @javafx.fxml.FXML
    public void initialize() {
        roleComboBox.getItems().addAll("Quality Inspector", "Inventory Manager","Supplier","Export delivery coordinator","Operation Manager","Import & Logistic Manager","Customer","Accounts");
        roleComboBox.setValue("Quality Inspector");

        messageLabel.setText("");

        qualityInspectorUser = new User("abir1", "Arshad", "qi@chainmeat.com", "1234", "Quality Inspector");
        inventoryManagerUser = new User("abir1", "Arshad", "im@chainmeat.com", "1234", "Inventory Manager");
        SupplierUser = new User("ebrahim1", "Ebrahim", "s@chainmeat.com", "1234", "Supplier");
        ExportDeliveryCoordinator= new User("ebrahim1", "Ebrahim", "ED@chainmeat.com", "1234", "Export delivery coordinator");
    }

    @javafx.fxml.FXML
    public void LoginButtonOnAction(ActionEvent actionEvent) {
        String userId = userIdTF.getText().trim();
        String password = passwordPF.getText().trim();
        String selectedRole = roleComboBox.getValue();

        if (userId.isEmpty() || password.isEmpty() || selectedRole == null) {
            messageLabel.setText("Please fill all fields.");
            return;
        }

        if (selectedRole.equals("Quality Inspector")) {
            if (userId.equals(qualityInspectorUser.getUserId()) &&
                    password.equals(qualityInspectorUser.getPassword())) {

                messageLabel.setText("Login successful.");
                openScene(actionEvent, "QualityInspectorDashboard.fxml", "Quality Inspector Dashboard");
            } else {
                messageLabel.setText("Invalid Quality Inspector ID or Password.");
            }
        }

        else if (selectedRole.equals("Inventory Manager")) {
            if (userId.equals(inventoryManagerUser.getUserId()) &&
                    password.equals(inventoryManagerUser.getPassword())) {

                messageLabel.setText("Login successful.");
                openScene(actionEvent, "InventoryManagerDashboard.fxml", "Inventory Manager Dashboard");
            } else {
                messageLabel.setText("Invalid Inventory Manager ID or Password.");
            }
        }
    }

    private void openScene(ActionEvent actionEvent, String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            Scene nextScene = new Scene(fxmlLoader.load());

            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setTitle(title);
            nextStage.setScene(nextScene);
            nextStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Could not load next page.");
        }
    }
}