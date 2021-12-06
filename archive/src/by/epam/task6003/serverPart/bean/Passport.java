package by.epam.task6003.serverPart.bean;

import by.epam.task6003.serverPart.dao.impl.LocalDateAdapter;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlType(name = "passport")
public class Passport implements Serializable {
    private String number;
    private String identificationNumber;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOfIssue;
    private String issuedBy;
    private String birthPlace;

    public Passport() {
    }

    public Passport(String number, String identificationNumber, LocalDate dateOfIssue, String issuedBy, String birthPlace) {
        this.number = number;
        this.identificationNumber = identificationNumber;
        this.dateOfIssue = dateOfIssue;
        this.issuedBy = issuedBy;
        this.birthPlace = birthPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public LocalDate takeDateOfIssue() {
        return dateOfIssue;
    }

    public void changeDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport)) return false;
        Passport passport = (Passport) o;
        return number.equals(passport.number) &&
                identificationNumber.equals(passport.identificationNumber) &&
                dateOfIssue.equals(passport.dateOfIssue) &&
                issuedBy.equals(passport.issuedBy) &&
                Objects.equals(birthPlace, passport.birthPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, identificationNumber, dateOfIssue, issuedBy, birthPlace);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "number='" + number + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", issuedBy='" + issuedBy + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                '}';
    }
}
