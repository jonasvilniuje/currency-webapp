package com.codenotfound.primefaces;





import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.List;



import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlElement;



@XmlAccessorType(XmlAccessType.FIELD)

public class FxRate implements Serializable {

    private String Tp;

    private String Dt;

    @XmlElement(name = "CcyAmt")

    private List<CcyAmt> ccyAmt;

    public String getTp() {

        return Tp;

    }

    public void setTp(String Tp) {

        this.Tp = Tp;

    }

    public String getDt() {

        return Dt;

    }

    public void setDt(String Dt) {

        this.Dt = Dt;

    }

    public List<CcyAmt> getCcyAmt() {

        return ccyAmt;

    }

    public void setCcyAmt(List<CcyAmt> ccyAmt) {

        this.ccyAmt = ccyAmt;

    }

    @Override

    public String toString() {

        Field[] fields = this.getClass().getDeclaredFields();

        String res = "\n";

        try {

            for (Field field : fields) {

                //res += field.getName() + " : " + field.get(this) + "\n";
                res += field.get(this) + " <br /> ";
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return res;

    }

}