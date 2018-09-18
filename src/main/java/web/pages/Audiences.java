package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.driver.WebDriverHelper;

public class Audiences {
    public static WebElement newAudience(){
        return WebDriverHelper.findElement(By.xpath("//i[@class='r42-plus']"));
    }

    public static WebElement audienceRow(String audienceName){
        return WebDriverHelper.findElement(By.xpath(String.format("//a[text()='%1$s']", audienceName)));
    }

    public static WebElement audienceName(){
        return WebDriverHelper.findElement(By.id("segment-name"));
    }
    public static WebElement description(){
        return WebDriverHelper.findElement(By.id("segment-description"));
    }

    public static WebElement engagementToDrag(String engName){
        return WebDriverHelper.findElement(By.xpath(String.format("//a[@data-name='%1$s']", engName)));
    }

    public static WebElement dragEngagement(){
        return WebDriverHelper.findElement(By.id("dropTargetAnd"));
    }

    public static WebElement next(){
        return WebDriverHelper.findElement(By.xpath("//a[text()='Next']"));
    }

    public static WebElement connectorToDrag(String connName){
        return WebDriverHelper.findElement(By.xpath(String.format("//span[text()='%1$s']", connName)));
    }

    public static WebElement dragConnector(){
        return WebDriverHelper.findElement(By.id("integrationDropTarget"));
    }

    public static WebElement confirm(){
        return WebDriverHelper.findElement(By.xpath("//a[text()='Confirm']"));
    }

    public static WebElement newAudienceName(){
        return WebDriverHelper.findElement(By.xpath("//h3[@class='title ng-binding']"));
    }

    public static WebElement delete(){
        return WebDriverHelper.findElement(By.xpath("//i[@class='r42-trash']"));
    }

    public static WebElement confirmDelete(){
        return WebDriverHelper.findElement(By.xpath("//button[text()='Delete']"));
    }

    public static WebElement apiIdentifier(){
        return WebDriverHelper.findElement(By.xpath("//p[contains(text(), 'API Identifier')]/../p[contains(@class, 'highlight')]"));
    }
}
