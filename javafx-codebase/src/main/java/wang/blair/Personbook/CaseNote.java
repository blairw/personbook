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

    @Override
    public String toString() {
        return "Case Note " + createTime;
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
}