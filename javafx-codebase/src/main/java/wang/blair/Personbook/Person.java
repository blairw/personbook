/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wang.blair.Personbook;

import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blair
 */
public class Person {
    
    private String fullName;
    private boolean importantPersonal;
    private boolean importantBusiness;
    private boolean newContactNotYetSaved = false;
    private MonthDay bdayMonthDay;
    private Year birthdayYear;
    
    // TIP: each Person starts with an empty list of case notes, rather than a null for the entire case notes property!
    private List<CaseNote> caseNotes = new ArrayList<>();


    @Override
    public String toString() {
        if (newContactNotYetSaved) {
            return "(new contact)";
        } else {
            return fullName;
        }
    }
    
    public Person() {
        this.newContactNotYetSaved = true;
    }
    
    public Person(String fullName) {
        this.fullName = fullName;
    }

    public void addCaseNote(CaseNote cn) {
        this.caseNotes.add(cn);
    }
    
    
    // ALL THE GETTERS
    
    public String getFullName() {
        return fullName;
    }

    public boolean isImportantPersonal() {
        return importantPersonal;
    }

    public boolean isImportantBusiness() {
        return importantBusiness;
    }

    public boolean isNewContactNotYetSaved() {
        return newContactNotYetSaved;
    }

    public MonthDay getBdayMonthDay() {
        return bdayMonthDay;
    }

    public Year getBirthdayYear() {
        return birthdayYear;
    }

    public List<CaseNote> getCaseNotes() {
        return caseNotes;
    }
    
    // ALL THE SETTERS

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setImportantPersonal(boolean importantPersonal) {
        this.importantPersonal = importantPersonal;
    }

    public void setImportantBusiness(boolean importantBusiness) {
        this.importantBusiness = importantBusiness;
    }

    public void setNewContactNotYetSaved(boolean newContactNotYetSaved) {
        this.newContactNotYetSaved = newContactNotYetSaved;
    }

    public void setBdayMonthDay(MonthDay bdayMonthDay) {
        this.bdayMonthDay = bdayMonthDay;
    }

    public void setBirthdayYear(Year birthdayYear) {
        this.birthdayYear = birthdayYear;
    }
    
    
}