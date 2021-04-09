/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wang.blair.Personbook;

import java.time.MonthDay;
import java.time.Year;

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

    public Person(String fullName, boolean importantPersonal, boolean importantBusiness, MonthDay bdayMonthDay, Year birthdayYear) {
        this.fullName = fullName;
        this.importantPersonal = importantPersonal;
        this.importantBusiness = importantBusiness;
        this.bdayMonthDay = bdayMonthDay;
        this.birthdayYear = birthdayYear;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isImportantPersonal() {
        return importantPersonal;
    }

    public void setImportantPersonal(boolean importantPersonal) {
        this.importantPersonal = importantPersonal;
    }

    public boolean isImportantBusiness() {
        return importantBusiness;
    }

    public void setImportantBusiness(boolean importantBusiness) {
        this.importantBusiness = importantBusiness;
    }

    public MonthDay getBdayMonthDay() {
        return bdayMonthDay;
    }

    public void setBdayMonthDay(MonthDay bdayMonthDay) {
        this.bdayMonthDay = bdayMonthDay;
    }

    public Year getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(Year birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    public boolean isNewContactNotYetSaved() {
        return newContactNotYetSaved;
    }

    public void setNewContactNotYetSaved(boolean newContactNotYetSaved) {
        this.newContactNotYetSaved = newContactNotYetSaved;
    }
}