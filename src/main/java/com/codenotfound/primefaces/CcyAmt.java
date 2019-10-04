
package com.codenotfound.primefaces;



import java.io.Serializable;

import java.lang.reflect.Field;



import javax.xml.bind.annotation.XmlAccessType;

import javax.xml.bind.annotation.XmlAccessorType;



@XmlAccessorType(XmlAccessType.FIELD)

public class CcyAmt implements Serializable {

    private String Ccy;

    private String Amt;

    public String getCcy() {

        return Ccy;

    }

    public void setCcy(String Ccy) {

        this.Ccy = Ccy;

    }

    public String getAmt() {

        return Amt;

    }

    public void setAmt(String Amt) {

        this.Amt = Amt;

    }

     @Override

    public String toString() {

        Field[] fields = this.getClass().getDeclaredFields();

        //String res = "{";
         String res="";
        try {

            for (Field field : fields) {

                //res += " " + field.getName() + " : " + field.get(this);
                res += " " + field.get(this);
            }

            //res += "}";

        } catch (Exception e) {

            e.printStackTrace();

        }

        return res;

    }

}