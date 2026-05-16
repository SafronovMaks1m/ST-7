package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        try {
            System.out.println("------ЗАДАНИЕ №1------\n");
            webDriver.get("https://www.calculator.net/password-generator.html");

            Thread.sleep(2000);

            WebElement passwordElement = webDriver.findElement(By.className("verybigtext"));

            String generatedPassword = passwordElement.getText();
            System.out.println("Сгенерированный пароль от сайта: " + generatedPassword);

            Task2.run(webDriver);

            Task3.run(webDriver);

        } catch (Exception e) {
            System.out.println("Ошибка выполнения Задания №1:");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}