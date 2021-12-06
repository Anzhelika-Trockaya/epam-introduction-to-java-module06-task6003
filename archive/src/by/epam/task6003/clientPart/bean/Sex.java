package by.epam.task6003.clientPart.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Sex {
    @XmlEnumValue("Female")
    FEMALE,
    @XmlEnumValue("Male")
    MALE
}
