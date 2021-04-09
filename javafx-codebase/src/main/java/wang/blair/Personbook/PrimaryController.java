package wang.blair.Personbook;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {
    
    @FXML public ListView<Person> myListView;
    @FXML public Label lblStatusBar;
    @FXML public Label lblCaseNotes;
    @FXML public Button btnNew;
    @FXML public Button btnSave;
    @FXML public Button btnNewCaseNote;
    @FXML public Button btnCancelNewCaseNote;
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
    @FXML public ChoiceBox<CaseNote> choiceBoxForCaseNotes;
    
    public Person currentlySelectedPerson;
    public CaseNote currentlySelectedCaseNote;
    public boolean changesMadeToPersonRecord;
    
    // prevent userDidSelectCaseNote() from executing when switching between person records.
    public boolean suppressCaseNoteListener = false;

    public void initialize() {
        this.setupButtonIcons();
        this.setupSampleData();
        
        ToggleGroup tgp = new ToggleGroup();
        tgp.getToggles().add(btnView);
        tgp.getToggles().add(btnEdit);
        
        this.updateStatusBarWithText("Ready.");
        
        // some buttons only visibile under very specific conditions
        HelperForJavafx.setNodesHidden(new Node[]{btnSave, btnCancelNewCaseNote, btnSaveCaseNote}, true);
        
        // since View is selected by default
        this.setPersonDetailsEditable(false);
        
        // managed separately to Person details
        HelperForJavafx.setTextboxEditable(txtCaseNotes, false);
        
        myListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> userDidSelectListItem(newValue));
        choiceBoxForCaseNotes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> userDidSelectCaseNote(newValue));
    }
    //
    // HANDLE USER ACTIONS
    //

    @FXML
    private void userDidSelectListItem(Person selectedPerson) {
        boolean weShouldContinue = true;
        if (selectedPerson == this.currentlySelectedPerson) {
            // no action required because
            // user selected the same person who is already selected!
            weShouldContinue = false;
        }
        
        if (weShouldContinue) {
            boolean continuingWouldCauseDataLoss = false;
            if (this.currentlySelectedPerson.isNewContactNotYetSaved()) {
                continuingWouldCauseDataLoss = true;
            }
            if (this.changesMadeToPersonRecord) {
                continuingWouldCauseDataLoss = true;
            }
            
            if (!continuingWouldCauseDataLoss) {
                // go ahead and change it, there is no data loss
                this.changeSelectionToPerson(selectedPerson);
            } else {
                boolean proceedWithDestructiveChange = HelperForJavafx.confirmDiscardEditChanges();
                
                if (proceedWithDestructiveChange){
                    // remove current item - only for case where we are creating new record
                    if (this.currentlySelectedPerson.isNewContactNotYetSaved()) {
                        myListView.getItems().remove(currentlySelectedPerson);
                    }
                
                    // complete the selection change
                    this.changeSelectionToPerson(selectedPerson);
                } else {
                    // essentially 'undo' the selection
                    myListView.getSelectionModel().select(this.currentlySelectedPerson);
                }
            }
        }
    }
    
    @FXML
    private void userDidSelectCaseNote(CaseNote selectedCaseNote) {
        if (!suppressCaseNoteListener) {
            this.changeSelectionToCaseNote(selectedCaseNote);
        }
    }
    
    
    @FXML
    private void userDidClickNew() {
        Person person = new Person();
        myListView.getItems().add(person);
        myListView.getSelectionModel().select(person);
    }
    
    
    @FXML
    private void userDidAddNewCaseNote() {
        HelperForJavafx.setNodesHidden(new Node[]{choiceBoxForCaseNotes, btnNewCaseNote}, true);
        HelperForJavafx.setNodesHidden(new Node[]{btnCancelNewCaseNote, btnSaveCaseNote}, false);
        
        txtCaseNotes.setText("");
        txtCaseNotes.setDisable(false);
        txtCaseNotes.requestFocus();
        
        HelperForJavafx.setTextboxEditable(txtCaseNotes, true);
    }
    
    
    @FXML
    private void userDidCancelNewCaseNote() {
        HelperForJavafx.setNodesHidden(new Node[]{choiceBoxForCaseNotes, btnNewCaseNote}, false);
        HelperForJavafx.setNodesHidden(new Node[]{btnCancelNewCaseNote, btnSaveCaseNote}, true);
        
        if (null != currentlySelectedCaseNote) {
            this.changeSelectionToCaseNote(currentlySelectedCaseNote);
        } else {
            txtCaseNotes.setText("");
            txtCaseNotes.setDisable(true);
        }
        
        HelperForJavafx.setTextboxEditable(txtCaseNotes, false);
    }
    
    
    
    @FXML
    private void userDidClickEdit() {
        this.updateStatusBarWithText("Entered Edit mode.");
        this.setPersonDetailsEditable(true);
    }
    
    @FXML
    private void userDidClickView() {
        this.updateStatusBarWithText("Entered View mode.");
        this.setPersonDetailsEditable(false);
        
        // in view mode, save button definitely should not be visible!
        HelperForJavafx.setNodeHidden(btnSave, true);
    }
    
    @FXML
    private void userDidUpdateFullName() {
        HelperForJavafx.disableButtonIfTextIsBlank(btnSave, txtFullName.getText());
    }
    
    
    @FXML
    private void userDidUpdateActiveCaseNote() {
        HelperForJavafx.disableButtonIfTextIsBlank(btnSaveCaseNote, txtCaseNotes.getText());
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
        this.changeSelectionToPerson(people.get(0));
    }
    
    private void changeSelectionToPerson(Person selectedPerson) {
        // if save button was visible from before, it should be invisible now
        HelperForJavafx.setNodeHidden(btnSave, true);
        
        // set name if available
        // TIP: always do null != <value to check> rather than the other way around!
        if (null != selectedPerson.getFullName()) {
            txtFullName.setText(selectedPerson.getFullName());
        } else {
            txtFullName.setText("");
        }
        
        // process case notes
        this.suppressCaseNoteListener = true;
        List<CaseNote> caseNotes = selectedPerson.getCaseNotes();
        boolean caseNotesWereAdded = HelperForCaseNotesGUI.populateCaseNotes(caseNotes, choiceBoxForCaseNotes, txtCaseNotes);
        if (caseNotesWereAdded) {
            this.changeSelectionToCaseNote(choiceBoxForCaseNotes.getSelectionModel().getSelectedItem());
        } else {
            // this person has no case notes, so we need to reset this
            this.currentlySelectedCaseNote = null;
        }
        this.suppressCaseNoteListener = false;
        
        // reset the change tracker
        this.changesMadeToPersonRecord = false;
        
        // finally, update the selection tracker
        this.currentlySelectedPerson = selectedPerson;
    }
    
    private void changeSelectionToCaseNote(CaseNote selectedCaseNote) {
        // if save button was visible from before, it should be invisible now
        HelperForJavafx.setNodeHidden(btnSaveCaseNote, true);

        // populate case note text
        txtCaseNotes.setText(selectedCaseNote.getCaseNoteText());
        
        // finally, update the selection tracker
        this.currentlySelectedCaseNote = selectedCaseNote;
    }
    
    private void setPersonDetailsEditable(boolean isEditable) {
        TextField[] textFields = {txtFullName, txtBdayDay, txtBdayMonth, txtBdayYear};
        HelperForJavafx.setTextboxesEditable(textFields, isEditable);
        
        chkPersonal.setDisable(!isEditable);
        chkBusiness.setDisable(!isEditable);
    }
}
