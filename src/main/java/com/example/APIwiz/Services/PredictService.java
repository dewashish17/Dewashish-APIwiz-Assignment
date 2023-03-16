package com.example.APIwiz.Services;

import com.example.APIwiz.DTOs.PredictedDto;
import com.example.APIwiz.Repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

@Service
public class PredictService {
    @Autowired
    private CurrencyRepository repoDb;
    public PredictedDto predict(String baseCurrency, String date) throws Exception{
        PredictedDto res=new PredictedDto();
        double sum = 0;
        HashMap<String, Double> db30days=new HashMap<String, Double>();
        for(int i=0;i<30;i++){
            db30days.put((String) repoDb.getDateAndCurrency(baseCurrency).get(i)[0], (Double) repoDb.getDateAndCurrency(baseCurrency).get(i)[1]);
        }
        LocalDate start_date= LocalDate.now().minusDays(29);
        LocalDate end_Date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        double pred_Value = 0;
        while (!date.equals(end_Date.format(formatter))){
            sum=0;
            end_Date=end_Date.plusDays(1);
            for(double x:db30days.values()){
                sum+=x;
            }
            db30days.remove(start_date.format(formatter));
            pred_Value=sum/30;
            db30days.put(end_Date.format(formatter),pred_Value);
            start_date=start_date.plusDays(1);
        }
        DecimalFormat decfor = new DecimalFormat("0.00");
        res.setPredictedValue(decfor.format(pred_Value));
        res.setPredictedValue(res.getPredictedValue()+" "+baseCurrency);
        return res;
    }
}