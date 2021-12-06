package by.epam.task6003.serverPart.bean;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Objects;

@XmlType(name = "grade")
public class Grade implements Serializable {
    private GradeType type;
    private String discipline;
    private int gradeValue;

    public Grade() {
    }

    public Grade(GradeType type, String discipline, int gradeValue) {
        this.type = type;
        this.discipline = discipline;
        this.gradeValue = gradeValue;
    }

    public GradeType getType() {
        return type;
    }

    public void setType(GradeType type) {
        this.type = type;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;
        Grade grade = (Grade) o;
        return gradeValue == grade.gradeValue &&
                type == grade.type &&
                discipline.equals(grade.discipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, discipline, gradeValue);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "type=" + type +
                ", discipline='" + discipline + '\'' +
                ", gradeValue=" + gradeValue +
                '}';
    }
}
