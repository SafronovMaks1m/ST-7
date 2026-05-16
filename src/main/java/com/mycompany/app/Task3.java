package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Task3 {
    public static void run(WebDriver webDriver) {
        try {
            System.out.println("------ЗАДАНИЕ №3------\n");

            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            webDriver.get(url);

            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);

            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray timeArray = (JSONArray) hourly.get("time");
            JSONArray tempArray = (JSONArray) hourly.get("temperature_2m");
            JSONArray rainArray = (JSONArray) hourly.get("rain");

            StringBuilder table = new StringBuilder();
            table.append(String.format("%-4s %-20s %-15s %-15s\n", "№", "Дата/время", "Температура", "Осадки (мм)"));
            table.append("-------------------------------------------------------------\n");

            for (int i = 0; i < timeArray.size(); i++) {
                String time = ((String) timeArray.get(i)).replace("T", " ");
                Object temp = tempArray.get(i);
                Object rain = rainArray.get(i);

                table.append(String.format("%-4d %-20s %-15s %-15s\n",
                        (i + 1), time, temp.toString() + "°C", rain.toString() + " мм"));
            }

            System.out.print(table.toString());

            try (PrintWriter out = new PrintWriter(new FileWriter("result/forecast.txt"))) {
                out.print(table.toString());
                System.out.println("\n Прогноз погоды сохранен в файл");
            }

        } catch (Exception e) {
            System.out.println("Ошибка в Задании №3: " + e.toString());
        }
    }
}