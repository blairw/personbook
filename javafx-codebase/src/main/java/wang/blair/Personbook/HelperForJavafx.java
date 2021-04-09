/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wang.blair.Personbook;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author blair
 */
public class HelperForJavafx {
    
    public static void setupIconForButton(ButtonBase btn, String iconName) {
        Image img = new Image(App.class.getResourceAsStream(iconName));
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(16);
        imgView.setPreserveRatio(true);
        btn.setGraphic(imgView);
    }
    
    public static void setTextFieldEditable(TextField textfield, boolean isEditable) {
        textfield.setEditable(isEditable);
        
        if (isEditable) {
            textfield.setStyle("-fx-control-inner-background: white;");
        } else {
            textfield.setStyle("-fx-control-inner-background: #f3f3f3;");
        }
    }
    
    public static boolean confirmDiscardEditChanges() {
        boolean preparedReturn = false;
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You are currently editing a new person's record.");
        alert.setHeaderText("You are currently editing a new person's record.");
        alert.setContentText("If you leave now, your edits will be discarded. Are you sure you want to continue?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            preparedReturn = true;
        }
        
        return preparedReturn;
    }
}
