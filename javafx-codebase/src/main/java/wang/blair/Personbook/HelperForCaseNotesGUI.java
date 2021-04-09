/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wang.blair.Personbook;

import java.util.List;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

/**
 *
 * @author blair
 */
public class HelperForCaseNotesGUI {
    
    public static boolean populateCaseNotes(List<CaseNote> caseNotes, ChoiceBox choiceBoxForCaseNotes, TextArea txtCaseNotes) {
        boolean caseNotesWereAdded = false;

        // TIP: to clear previous case notes, use clear(), not removeAll().
        choiceBoxForCaseNotes.getItems().clear();
        txtCaseNotes.setText("");
        
        if (caseNotes.size() > 0) {
            choiceBoxForCaseNotes.setDisable(false);
            txtCaseNotes.setDisable(false);
            for (CaseNote cn : caseNotes) {
                choiceBoxForCaseNotes.getItems().add(cn);
                
                caseNotesWereAdded = true;
            }
            
            // select the first available item
            choiceBoxForCaseNotes.getSelectionModel().select(0);
        } else {
            choiceBoxForCaseNotes.setDisable(true);
            txtCaseNotes.setDisable(true);
        }
        
        return caseNotesWereAdded;
    }
    
}
