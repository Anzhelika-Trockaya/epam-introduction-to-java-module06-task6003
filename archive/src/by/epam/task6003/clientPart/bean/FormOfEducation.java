package by.epam.task6003.clientPart.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum FormOfEducation {
    @XmlEnumValue("Full-time education")
    FULL_TIME_EDUCATION,
    @XmlEnumValue("Extramural studies")
    EXTRAMURAL_STUDIES
}
