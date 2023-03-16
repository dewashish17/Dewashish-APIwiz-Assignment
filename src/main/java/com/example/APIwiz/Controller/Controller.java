package com.example.APIwiz.Controller;

import com.example.APIwiz.DTOs.PredictedDto;
import com.example.APIwiz.Services.ExchangeService;
import com.example.APIwiz.Services.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/currency")
public class Controller {
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private PredictService predictService;
    @GetMapping("/exchange")
    public HashMap<String, String> exchange(@RequestParam("destination") String destination, @RequestParam("base") String base) throws IOException {
        return exchangeService.exchange(destination,base);
    }
    @GetMapping("/predict")
    public PredictedDto predict(@RequestParam("baseCurrency") String baseCurrency, @RequestParam("date") String date) throws Exception {
        return predictService.predict(baseCurrency,date);
    }
}
