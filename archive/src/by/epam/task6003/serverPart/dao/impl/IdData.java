package by.epam.task6003.serverPart.dao.impl;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "idData")
@XmlRootElement
public class IdData {
    private int currentId;

    public IdData(){
    }

    public IdData(int currentId){
        this.currentId=currentId;
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }
}
