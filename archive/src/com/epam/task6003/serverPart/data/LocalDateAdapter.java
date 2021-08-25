package com.epam.task6003.serverPart.data;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    public LocalDate unmarshal(String date) throws Exception {
        return LocalDate.parse(date);
    }

    public String marshal(LocalDate date) throws Exception {
        return date.toString();
    }
}
