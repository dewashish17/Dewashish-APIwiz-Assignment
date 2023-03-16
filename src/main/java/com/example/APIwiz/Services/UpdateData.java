package com.example.APIwiz.Services;

import com.example.APIwiz.Model.LastThirtyDayData;
import com.example.APIwiz.Repository.CurrencyRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Service
public class UpdateData {
    @Autowired
    private CurrencyRepository currencyRepository;
    public void callAPI() throws IOException {
        String end_date = String.valueOf(java.time.LocalDate.now());
        LocalDate oneMonthBefore = LocalDate.now().minusDays(29);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        String start_date = oneMonthBefore.format(formatter);
        String urlString = "https://api.apilayer.com/exchangerates_data/timeseries?start_date="+start_date+"&end_date="+end_date+"";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("apikey", "###API_KEY HERE###");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonResponse = new JSONObject(response.toString()).getJSONObject("rates");
        for(String date: jsonResponse.keySet()){
            LastThirtyDayData obj=new LastThirtyDayData();
            obj.setDate(date);
            JSONObject singleDayObj=jsonResponse.getJSONObject(date);
            obj.setINR(singleDayObj.getDouble("INR"));
            obj.setGBP(singleDayObj.getDouble("GBP"));
            obj.setEUR(singleDayObj.getDouble("EUR"));
            obj.setJPY(singleDayObj.getDouble("JPY"));
            obj.setCHF(singleDayObj.getDouble("CHF"));
            obj.setUSD(singleDayObj.getDouble("USD"));
            obj.setCAD(singleDayObj.getDouble("CAD"));
            obj.setNZD(singleDayObj.getDouble("NZD"));
            obj.setAUD(singleDayObj.getDouble("AUD"));
            obj.setNOK(singleDayObj.getDouble("NOK"));
            obj.setSEK(singleDayObj.getDouble("SEK"));
            currencyRepository.save(obj);
        }
        System.out.println("Saved new data!");
    }
}
