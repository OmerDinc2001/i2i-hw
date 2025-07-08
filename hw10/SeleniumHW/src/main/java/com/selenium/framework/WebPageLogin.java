package com.selenium.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebPageLogin {
    public static void main(String[] args)
    {
        WebDriver driver = new ChromeDriver();

        // URL of the login website that is tested
        driver.get("https://github.com/login");

        // Maximize window size of browser
        driver.manage().window().maximize();

        // Enter your login email id
        driver.findElement(By.id("login_field"))
                .sendKeys("");

        // Enter your login password
        driver.findElement(By.id("password"))
                .sendKeys("");

        driver.findElement(By.className("btn"))
                .click();
    }
}
