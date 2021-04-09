/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wang.blair.Personbook;

import java.time.LocalDateTime;

/**
 *
 * @author blair
 */
public class CaseNote {
    private LocalDateTime createTime;
    private String caseNoteText;
    private boolean newCaseNoteNotYetSaved = false;

    @Override
    public String toString() {
        if (this.newCaseNoteNotYetSaved) {
            return "(new case note)";
        } else {
            return "Case Note " + HelperForData.formatLocalDateTime(createTime);
        }
    }

    public CaseNote() {
        this.newCaseNoteNotYetSaved = true;
        this.createTime = LocalDateTime.now();
    }
    
    public CaseNote(String caseNoteText) {
        this.createTime = LocalDateTime.now();
        this.caseNoteText = caseNoteText;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
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