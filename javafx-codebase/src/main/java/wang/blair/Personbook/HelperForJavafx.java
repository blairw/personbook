/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wang.blair.Personbook;

import java.util.List;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
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
    public static void setTextFieldsEditable(TextField[] textFields, boolean isEditable) {
        for (TextField textField : textFields) {
            setTextFieldEditable(textField, isEditable);
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
    
    // TIP: This is needed to both set the item visible and reorganise layouts.
    // https://stackoverflow.com/questions/28558165/javafx-setvisible-hides-the-element-but-doesnt-rearrange-adjacent-nodes
    public static void setNodeHidden(Node node, boolean isHidden) {
        node.setVisible(!isHidden);
        node.setManaged(!isHidden);
    }
    public static void setNodesHidden(Node[] nodes, boolean isHidden) {
        for (Node node : nodes) {
            setNodeHidden(node, isHidden);
        }
    }
    
    // TIP: do not allow empty full name!
    // fancy blankness checker from https://stackoverflow.com/questions/3247067/how-do-i-check-that-a-java-string-is-not-all-whitespaces
    public static void disableButtonIfTextIsBlank(Button button, String text) {
        if (!text.trim().isEmpty()) {
            button.setDisable(false);
        } else {
            button.setDisable(true);
        }
        
    }
}
