package com.epam.task6003.serverPart.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum UserRole {
    @XmlEnumValue("Admin")
    ADMIN,
    @XmlEnumValue("User")
    USER
}
