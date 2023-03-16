package com.example.APIwiz.Services;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import org.json.JSONObject;
@Service
public class ExchangeService {
    public HashMap<String, String> exchange(String destination, String base) throws IOException {
        String from = base.split(" ")[1];
        String amount = base.split(" ")[0];

        String urlString = "https://api.apilayer.com/exchangerates_data/convert?to=" + destination + "&from=" + from + "&amount=" + amount;

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

        JSONObject jsonResponse = new JSONObject(response.toString());
        double result = jsonResponse.getDouble("result");
        HashMap<String,String> hm =new HashMap<>();
        DecimalFormat decfor = new DecimalFormat("0.00");
        hm.put(base.replace(" ",""),decfor.format(result)+ " " + destination);
        return hm;
    }
}

