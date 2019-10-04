package com.codenotfound.primefaces;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FxRates")

public class FxRates implements Serializable {

    private List<FxRate> fxRates;

    @XmlElement(name = "FxRate")
    public List<FxRate> getFxRates() {
        return fxRates;
    }

    public void setFxRates(List<FxRate> fxRates) {
        this.fxRates = fxRates;
    }

    @Override
    public String toString() {
        Field[] fields = this.getClass().getDeclaredFields();
        String res = "";
        try {
            for (Field field : fields) {
                //res += field.getName() + " :\n" + field.get(this);

                res += field.get(this) + " " ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}