package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.driver.WebDriverHelper;

public class LoginPage {
    public static WebElement userName(){
        return WebDriverHelper.findElement(By.name("username"));
    }

    public static WebElement password(){
        return WebDriverHelper.findElement(By.name("password"));
    }

    public static WebElement signIn(){
        return WebDriverHelper.findElement(By.xpath("//button[text()='Sign in']"));
    }
}
