/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wang.blair.Personbook;

import java.time.LocalDate;

/**
 *
 * @author blair
 */
public class CaseNote {
    private LocalDate createTime;
    private String caseNoteText;
    private boolean newCaseNoteNotYetSaved = false;

    @Override
    public String toString() {
        if (this.newCaseNoteNotYetSaved) {
            return "(new case note)";
        } else {
            return "Case Note " + createTime;
        }
    }

    public CaseNote() {
        this.newCaseNoteNotYetSaved = true;
        this.createTime = LocalDate.now();
    }
    
    public CaseNote(String caseNoteText) {
        this.createTime = LocalDate.now();
        this.caseNoteText = caseNoteText;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getCaseNoteText() {
        return caseNoteText;
    }

    public void setCaseNoteText(String caseNoteText) {
        this.caseNoteText = caseNoteText;
    }

    public boolean isNewCaseNoteNotYetSaved() {
        return newCaseNoteNotYetSaved;
    }

    public void setNewCaseNoteNotYetSaved(boolean newCaseNoteNotYetSaved) {
        this.newCaseNoteNotYetSaved = newCaseNoteNotYetSaved;
    }
}