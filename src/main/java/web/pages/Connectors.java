package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.driver.WebDriverHelper;

public class Connectors {
    public static WebElement newConnector(){
        return WebDriverHelper.findElement(By.xpath("//i[@class='r42-plus']"));
    }

    public static WebElement relay42Api(){
        return WebDriverHelper.findElement(By.xpath("//div[@class='partner-name ellipsis ng-binding' and contains(text(), 'Relay42 Streaming API')]"));
    }

    public static WebElement connectorName(){
        return WebDriverHelper.findElement(By.name("vm.data.partner.partnerName"));
    }

    public static WebElement submit(){
        return WebDriverHelper.findElement(By.xpath("//button[text()='Submit']"));
    }

    public static WebElement expandDropDown(String connName){
        return WebDriverHelper.findElement(By.xpath(String.format("//span[text()='%1$s']/../../div[@class='partner-controls']//div[@class='dropdown']", connName)));
    }

    public static WebElement deleteDropDown(String connName){
        return WebDriverHelper.findElement(By.xpath(String.format("//span[text()='%1$s']/../../div[@class='partner-controls']//a[text()='Delete']", connName)));
    }

    public static WebElement confirmDelete(){
        return WebDriverHelper.findElement(By.xpath("//button[text()='Delete']"));
    }

}
