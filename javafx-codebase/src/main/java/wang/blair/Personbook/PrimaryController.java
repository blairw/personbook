package wang.blair.Personbook;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {
    
    @FXML public ListView<Person> myListView;
    @FXML public Label lblStatusBar;
    @FXML public Button btnNew;
    @FXML public Button btnSave;
    @FXML public Button btnNewCaseNote;
    @FXML public Button btnSaveCaseNote;
    @FXML public ToggleButton btnView;
    @FXML public ToggleButton btnEdit;
    @FXML public TextField txtFullName;
    @FXML public TextField txtBdayDay;
    @FXML public TextField txtBdayMonth;
    @FXML public TextField txtBdayYear;
    @FXML public TextArea txtCaseNotes;
    @FXML public CheckBox chkPersonal;
    @FXML public CheckBox chkBusiness;
    @FXML public ChoiceBox choiceBoxForCaseNotes;
    
    public Person currentlySelectedPerson;
    public boolean changesHaveBeenMade;

    public void initialize() {
        this.setupButtonIcons();
        this.setupSampleData();
        
        ToggleGroup tgp = new ToggleGroup();
        tgp.getToggles().add(btnView);
        tgp.getToggles().add(btnEdit);
        
        this.updateStatusBarWithText("Ready.");
        
        // btnSave only visibile under very specific conditions
        btnSave.setVisible(false);
        
        // since View is selected by default
        this.setEverythingEditable(false);
        
        myListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> userDidSelectListItem(newValue));
    }
    
    
    //
    // HANDLE USER ACTIONS
    //

    @FXML
    private void userDidSelectListItem(Person selectedPerson) {
        if (selectedPerson == this.currentlySelectedPerson) {
            // no action required - user selected the same person who is already selected!
        } else {
            if (this.currentlySelectedPerson.isNewContactNotYetSaved() || this.changesHaveBeenMade) {
                boolean proceedWithDestructiveChange = HelperForJavafx.confirmDiscardEditChanges();
                
                if (proceedWithDestructiveChange){
                    // remove current item - only for case where we are creating new record
                    if (this.currentlySelectedPerson.isNewContactNotYetSaved()) {
                        myListView.getItems().remove(currentlySelectedPerson);
                    }
                    
                    // reset the change tracker
                    this.changesHaveBeenMade = false;
                
                    // complete the selection change
                    this.changeSelectionToPerson(selectedPerson);
                } else {
                    // essentially 'undo' the selection
                    myListView.getSelectionModel().select(this.currentlySelectedPerson);
                }
            } else {
                this.changeSelectionToPerson(selectedPerson);
            }
        }
    }
    
    @FXML
    private void userDidClickNew() {
        Person person = new Person();
        myListView.getItems().add(person);
        myListView.getSelectionModel().select(person);
        
        this.currentlySelectedPerson = person;
    }
    
    @FXML
    private void userDidClickEdit() {
        this.updateStatusBarWithText("Entered Edit mode.");
        this.setEverythingEditable(true);
    }
    
    @FXML
    private void userDidClickView() {
        this.updateStatusBarWithText("Entered View mode.");
        this.setEverythingEditable(false);
        
        // in view mode, save button definitely should not be visible!
        btnSave.setVisible(false);
    }
    
    @FXML
    private void userDidUpdateFullName() {
        String enteredValue = txtFullName.getText();
        
        // TIP: avoid using == for string comparisons
        this.changesHaveBeenMade = !enteredValue.equals(this.currentlySelectedPerson.getFullName());
        
        // TIP: do not allow empty full name!
        // fancy blankness checker from https://stackoverflow.com/questions/3247067/how-do-i-check-that-a-java-string-is-not-all-whitespaces
        if (this.changesHaveBeenMade && !enteredValue.trim().isEmpty()) {
            btnSave.setVisible(true);
        } else {
            btnSave.setVisible(false);
        }
    }
    
    
    //
    // FUNCTIONALITIES
    //
    
    private void updateStatusBarWithText(String newStatusBarText) {
        lblStatusBar.setText(newStatusBarText);
    }
    
    private void setupButtonIcons() {
        HelperForJavafx.setupIconForButton(btnNew, "Farm-Fresh_add.png");
        HelperForJavafx.setupIconForButton(btnNewCaseNote, "Farm-Fresh_add.png");
        HelperForJavafx.setupIconForButton(btnEdit, "Farm-Fresh_pencil.png");
        HelperForJavafx.setupIconForButton(btnView, "Farm-Fresh_vcard.png");
        HelperForJavafx.setupIconForButton(btnSave, "Farm-Fresh_diskette.png");
        HelperForJavafx.setupIconForButton(btnSaveCaseNote, "Farm-Fresh_diskette.png");
    }
    
    private void setupSampleData() {
        // add all the things
        List<Person> people = HelperForData.generateSamplePersonRecords();
        
        for (Person p : people) {
            myListView.getItems().add(p);
        }
        
        // select the first person by default
        // we can assume there is a first person since we just populated above!
        myListView.getSelectionModel().select(0);
        this.changeSelectionToPerson(myListView.getItems().get(0));
    }
    
    private void changeSelectionToPerson(Person selectedPerson) {
        this.updateStatusBarWithText("Selected record for " + selectedPerson + ".");
        this.currentlySelectedPerson = selectedPerson;
        
        // if save button was visible from before, it should be invisible now
        btnSave.setVisible(false);
        
        // set name if available
        // TIP: always do null != <value to check> rather than the other way around!
        if (null != selectedPerson.getFullName()) {
            txtFullName.setText(selectedPerson.getFullName());
        } else {
            txtFullName.setText("");
        }
        
        // TIP: to clear previous case notes, use clear(), not removeAll().
        choiceBoxForCaseNotes.getItems().clear();
        if (selectedPerson.getCaseNotes().size() > 0) {
            choiceBoxForCaseNotes.setDisable(false);
            txtCaseNotes.setDisable(false);
            
            for (CaseNote cn : selectedPerson.getCaseNotes()) {
                choiceBoxForCaseNotes.getItems().add(cn);
            }
        } else {
            choiceBoxForCaseNotes.setDisable(true);
            txtCaseNotes.setDisable(true);
        }
    }
    
    private void setEverythingEditable(boolean isEditable) {
        HelperForJavafx.setTextFieldEditable(txtFullName, isEditable);
        HelperForJavafx.setTextFieldEditable(txtBdayDay, isEditable);
        HelperForJavafx.setTextFieldEditable(txtBdayMonth, isEditable);
        HelperForJavafx.setTextFieldEditable(txtBdayYear, isEditable);
        
        chkPersonal.setDisable(!isEditable);
        chkBusiness.setDisable(!isEditable);
    }
}
