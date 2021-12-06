package by.epam.task6003.clientPart.bean;

import by.epam.task6003.serverPart.dao.impl.LocalDateAdapter;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlType(name="familyMember")
public class FamilyMember implements Serializable {
    private String name;
    private FamilyMemberType type;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthdayDate;
    private String placeOfWork;
    private String position;

    public FamilyMember() {
    }

    public FamilyMember(String name, FamilyMemberType type, LocalDate birthdayDate) {
        this.name = name;
        this.type = type;
        this.birthdayDate = birthdayDate;
    }

    public FamilyMember(String name, FamilyMemberType type, LocalDate birthdayDate, String placeOfWork, String position) {
        this.name = name;
        this.type = type;
        this.birthdayDate = birthdayDate;
        this.placeOfWork = placeOfWork;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FamilyMemberType getType() {
        return type;
    }

    public void setType(FamilyMemberType type) {
        this.type = type;
    }

    public LocalDate takeBirthdayDate() {
        return birthdayDate;
    }

    public void changeBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyMember)) return false;
        FamilyMember that = (FamilyMember) o;
        return Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(birthdayDate, that.birthdayDate) &&
                Objects.equals(placeOfWork, that.placeOfWork) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, birthdayDate, placeOfWork, position);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", birthdayDate=" + birthdayDate +
                ", placeOfWork='" + placeOfWork + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
