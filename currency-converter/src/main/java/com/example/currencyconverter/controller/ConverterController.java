package com.example.currencyconverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
@Controller
public class ConverterController {

    private static final double USD_TO_VND_RATE = 23000;

    @GetMapping("/converter")
    public String converterPage() {
        return "converter";
    }

    @PostMapping("/converter")
    public String convertCurrency(
            @RequestParam("amount") double amount,
            @RequestParam("fromCurrency") String fromCurrency,
            @RequestParam("toCurrency") String toCurrency,
            Model model) {
        double result;
        if (fromCurrency.equals("USD") && toCurrency.equals("VND")) {
            result = amount * USD_TO_VND_RATE;
        } else if (fromCurrency.equals("VND") && toCurrency.equals("USD")) {
            result = amount / USD_TO_VND_RATE;
        } else {
            result = amount;
        }
        DecimalFormat df = new DecimalFormat("#,##0.00");
        model.addAttribute("result", df.format(result) + " " + toCurrency);
        return "converter";
    }
}



