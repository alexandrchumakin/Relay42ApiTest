package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.driver.WebDriverHelper;

public class Engagements {
    public static WebElement newEngagement(){
        return WebDriverHelper.findElement(By.xpath("//i[@class='r42-plus']"));
    }

    public static WebElement type(){
        return WebDriverHelper.findElement(By.id("data-interaction-mainFieldValue"));
    }

    public static WebElement description(){
        return WebDriverHelper.findElement(By.id("data-interaction-description"));
    }

    public static WebElement submit(){
        return WebDriverHelper.findElement(By.id("r42-profiles-interactions-interactionform-edit-submit"));
    }

    public static WebElement delete(){
        return WebDriverHelper.findElement(By.xpath("//i[@class='r42-trash']"));
    }

    public static WebElement existingEngagement(String engName){
        return WebDriverHelper.findElement(By.xpath(String.format("//span[text()='%1$s']", engName)));
    }

    public static WebElement confirmDelete(){
        return WebDriverHelper.findElement(By.xpath("//button[text()='Delete']"));
    }
}
