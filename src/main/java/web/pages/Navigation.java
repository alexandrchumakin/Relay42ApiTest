package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.driver.WebDriverHelper;

public class Navigation {
    public static WebElement dataManagement(){
        return WebDriverHelper.findElement(By.xpath("//span[text()='Data Management']"));
    }

    public static WebElement audience(){
        return WebDriverHelper.findElement(By.xpath("//span[text()='Audiences']"));
    }

    public static WebElement connectors(){
        return WebDriverHelper.findElement(By.xpath("//span[text()='Connectors']"));
    }

    public static WebElement engagements(){
        return WebDriverHelper.findElement(By.xpath("//span[text()='Engagements']"));
    }
}
