package by.epam.task6003.clientPart.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum FamilyMemberType {
    @XmlEnumValue("husband")
    HUSBAND,
    @XmlEnumValue("wife")
    WIFE,
    @XmlEnumValue("daughter")
    DAUGHTER,
    @XmlEnumValue("son")
    SON,
    @XmlEnumValue("mother")
    MOTHER,
    @XmlEnumValue("father")
    FATHER,
    @XmlEnumValue("sister")
    SISTER,
    @XmlEnumValue("brother")
    BROTHER
}
