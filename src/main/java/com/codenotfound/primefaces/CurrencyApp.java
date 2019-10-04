package com.codenotfound.primefaces;

import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Named
public class CurrencyApp {

    private String dateFrom = "2016-08-08";
    private String dateTo = "2016-08-30";
    private String ccy ="";
    private String url;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() { return dateTo; }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getCcy() { return ccy; }

    public void setCcy(String ccy) { this.ccy = ccy; }

    public String getUrl() { return url; }

    public void setUrlForCcy(String url) { this.url = url; }

    public void setUrlForDateOrPeriod(String url) { this.url = url; }

    public String cleanse_string(String in) {
        return in
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
    }

    private String format_string(String in) {
        return in
                .replace("EU ", "")
                .replace("LT ", "");
    }

    private String get_xml_from_url(String url) throws IOException {

        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();

        return xml_toString(new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)));
    }

    private String xml_toString(BufferedReader r)  throws IOException {

        StringBuilder sb = new StringBuilder();

        r.readLine(); // for skipping the first line of xml
        r.readLine(); // for skipping the first line of xml

        sb.append("<FxRates>");
        String line;

        while ((line = r.readLine()) != null) {
            sb.append(line);
        }

        String data=sb.toString();


        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FxRates.class);

            System.out.println("\n------- XML to Object -----------\n");
            StringReader reader = new StringReader(data);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            FxRates elementsOut = (FxRates) jaxbUnmarshaller.unmarshal(reader);
            System.out.println(cleanse_string(elementsOut.toString()));

            return format_string(cleanse_string(elementsOut.toString()));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return "Error.";
    }

    public String get_rates_by_date_or_period() throws IOException {
        String url, c, f, t, tp;
        tp = "EU";
        f = "&dtFrom=";
        t = "&dtTo=";
        c = "&ccy=";

        url = "https://www.lb.lt/webservices/fxrates/fxrates.asmx/getFxRatesForCurrency?tp=" + tp + c + getCcy() + f + dateFrom + t + dateTo;
        setCcy(""); // set currency to default value
        return get_xml_from_url(url);
    }

    public String get_rates_by_currency() throws IOException {
        String url, c, f, t, tp;
        tp = "EU";
        c = "&ccy=";
        f = "&dtFrom=";
        t = "&dtTo=";

        url = "https://www.lb.lt/webservices/fxrates/fxrates.asmx/getFxRatesForCurrency?tp=" + tp + c + getCcy() + f + t;
        setCcy(""); // set currency to default value
        return get_xml_from_url(url);
    }

}
