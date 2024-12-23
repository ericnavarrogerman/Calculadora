package com.example.calculadora;


public class CalculatorLogic {
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewOperation = true;
    private String operationDisplay = "";


    public String getCurrentNumber() {
        return currentNumber;
    }

    public String getOperationDisplay() {
        return operationDisplay;
    }

    public void appendNumber(String number) {
        if (isNewOperation) {
            currentNumber = number;
            isNewOperation = false;
            operationDisplay = number;
        } else {
            currentNumber += number;
            operationDisplay += number;
        }
    }

    public void setOperator(String operator) {
        if (!currentNumber.isEmpty()) {
            firstNumber = Double.parseDouble(currentNumber);
            this.operator = operator;
            operationDisplay = currentNumber + " " + operator + " ";
            currentNumber = "";
        }
    }

    public void clear() {
        currentNumber = "";
        operator = "";
        firstNumber = 0;
        isNewOperation = true;
        operationDisplay = "";
    }

    public void addDecimalPoint() {
        if (!currentNumber.contains(".")) {
            currentNumber += ".";
            operationDisplay += ".";
        }
    }

    public void calculatePercent() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);

            if (!operator.isEmpty() && firstNumber != 0) {
                double percentage = (firstNumber * number) / 100;

                switch (operator) {
                    case "+":
                    case "-":
                        currentNumber = String.valueOf(percentage);
                        operationDisplay = firstNumber + " " + operator + " " + number + "% = ";
                        break;
                    case "×":
                    case "÷":
                        currentNumber = String.valueOf(number / 100);
                        operationDisplay = firstNumber + " " + operator + " " + number + "% = ";
                        break;
                }
            } else {
                currentNumber = String.valueOf(number / 100);
                operationDisplay = number + "% = " + currentNumber;
            }
        }
    }

    public void calculateSquareRoot() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            if (number >= 0) {
                double result = Math.sqrt(number);
                currentNumber = formatResult(result);
                operationDisplay = "√(" + number + ") = " + currentNumber;
                isNewOperation = true;
            } else {
                currentNumber = "Error";
                operationDisplay = "Error";
            }
        }
    }

    public void backspace() {
        if (!currentNumber.isEmpty()) {
            currentNumber = currentNumber.length() > 1 ?
                    currentNumber.substring(0, currentNumber.length() - 1) :
                    "";
            if (operator.isEmpty()) {
                operationDisplay = currentNumber;
            } else {
                operationDisplay = operationDisplay.length() > 1 ?
                        operationDisplay.substring(0, operationDisplay.length() - 1) :
                        "";
            }
        }
    }

    public void calculateResult() {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            String fullOperation = firstNumber + " " + operator + " " + secondNumber;
            double result;

            try {
                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "×":
                        result = firstNumber * secondNumber;
                        break;
                    case "÷":
                        if (secondNumber == 0) throw new ArithmeticException("División por cero");
                        result = firstNumber / secondNumber;
                        break;
                    default:
                        throw new IllegalStateException("Operador no válido: " + operator);
                }
                currentNumber = formatResult(result);
                operationDisplay = fullOperation + " = " + currentNumber;
            } catch (Exception e) {
                currentNumber = "Error";
                operationDisplay = "Error";
            }

            operator = "";
            isNewOperation = true;
        }
    }

    private String formatResult(double result) {
        String formatted = String.valueOf(result);
        if (formatted.endsWith(".0")) {
            formatted = formatted.substring(0, formatted.length() - 2);
        }
        return formatted;
    }
}