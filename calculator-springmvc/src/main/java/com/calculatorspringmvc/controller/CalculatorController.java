package com.calculatorspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String showCalculator() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam double num1,
                            @RequestParam double num2,
                            @RequestParam String operator,
                            Model model) {
        double result = 0;
        String operationName = "";

        switch (operator) {
            case "+":
                result = num1 + num2;
                operationName = "Cộng";
                break;
            case "-":
                result = num1 - num2;
                operationName = "Trừ";  
                break;
            case "*":
                result = num1 * num2;
                operationName = "Nhân";
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                    operationName = "Chia";
                } else {
                    model.addAttribute("error", "Không thể chia cho 0");
                    return "calculator";
                }
                break;
        }

        model.addAttribute("result", result);
        model.addAttribute("operationName", operationName);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);

        return "calculator";
    }
}
