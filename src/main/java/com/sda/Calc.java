package com.sda;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class Calc extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer a = Optional.ofNullable(req.getParameter("a"))
                .filter(e -> StringUtils.isNumeric(e))
                .map(e -> Integer.valueOf(e))
                .orElse(0);
        Integer b = Optional.ofNullable(req.getParameter("b"))
                .filter(e -> StringUtils.isNumeric(e))
                .map(e -> Integer.valueOf(e))
                .orElse(0);

        CalculationResult result = calculate(req.getPathInfo(), a, b);


        PrintWriter writer = resp.getWriter();
        writer.print("<h1>Wynik " + result.resultRepresentation + "</h1>");


    }

    private CalculationResult calculate(String path, int a, int b) {

        if (path.endsWith("add")) {
            return new CalculationResult(a + b, a + " + " + b + " = " + (a + b));

        } else if (path.endsWith("substract")) {
            return new CalculationResult(a - b, a + " - " + b + " = " + (a - b));
        } else if (path.endsWith("multiply")) {
            return new CalculationResult(a * b, a + " - " + b + " = " + (a * b));
        }
        return new CalculationResult( 0, "Zły operator");
    }

    private static class CalculationResult {
        private Integer result;
        private String resultRepresentation;

        public CalculationResult(Integer result, String resultRepresentation) {
            this.result = result;
            this.resultRepresentation = resultRepresentation;
        }

        public Integer getResult() {
            return result;
        }

        public void setResult(Integer result) {
            this.result = result;
        }

        public String getResultRepresentation() {
            return resultRepresentation;
        }

        public void setResultRepresentation(String resultRepresentation) {
            this.resultRepresentation = resultRepresentation;
        }
    }
}



