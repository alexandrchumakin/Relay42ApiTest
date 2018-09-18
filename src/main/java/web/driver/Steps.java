package web.driver;

import common.Configurations;
import common.StringExtension;
import http.HttpReq;
import http.models.Visitor;
import org.openqa.selenium.WebDriver;
import web.pages.*;
import web.pages.models.Audience;

public class Steps {
    public static void openBrowser(){
        WebDriver driver = DriverManager.getInstance();
        driver.navigate().to(Configurations.getValueByKey("host"));
        DriverManager.waitPageSource();
    }

    public static void login(){
        LoginPage.userName().sendKeys(Configurations.getValueByKey("user"));
        LoginPage.password().sendKeys(Configurations.getValueByKey("password"));
        LoginPage.signIn().click();
        DriverManager.waitPageSource();
    }

    public static String createConnector(){
        String connName = "Test relay42 API";
        Navigation.dataManagement().click();
        Navigation.connectors().click();
        Connectors.newConnector().click();
        Connectors.relay42Api().click();
        Connectors.connectorName().clear();
        Connectors.connectorName().sendKeys(connName);
        Connectors.submit().click();
        DriverManager.waitPageSource();
        return connName;
    }

    public static String createEngagement(){
        String engName = "Tomato";
        Navigation.engagements().click();
        Engagements.newEngagement().click();
        Engagements.type().sendKeys(engName);
        Engagements.description().sendKeys(StringExtension.generateString());
        Engagements.submit().click();
        DriverManager.waitPageSource();
        return engName;
    }

    public static Audience createAudience(String connName, String engName){
        Navigation.dataManagement().click();
        Navigation.audience().click();
        Audiences.newAudience().click();
        Audiences.audienceName().sendKeys(StringExtension.generateString());
        Audiences.description().sendKeys(StringExtension.generateString());
        WebDriverHelper.dragElement(Audiences.engagementToDrag(engName), Audiences.dragEngagement());
        Audiences.next().click();
        DriverManager.waitPageSource();
        Audiences.next().click();
        WebDriverHelper.dragElement(Audiences.connectorToDrag(connName), Audiences.dragConnector());
        Audiences.confirm().click();
        DriverManager.waitPageSource();
        return new Audience(Audiences.newAudienceName().getText(), Audiences.apiIdentifier().getText());
    }

    public static void removeAudience(String audienceName){
        Navigation.audience().click();
        Audiences.audienceRow(audienceName).click();
        Audiences.delete().click();
        Audiences.confirmDelete().click();
        DriverManager.waitPageSource();
    }

    public static void removeConnector(String connName){
        Navigation.connectors().click();
        Connectors.expandDropDown(connName).click();
        Connectors.deleteDropDown(connName).click();
        Connectors.confirmDelete().click();
        DriverManager.waitPageSource();
    }

    public static void removeEngagement(String engName){
        Navigation.engagements().click();
        Engagements.existingEngagement(engName).click();
        Engagements.delete().click();
        Engagements.confirmDelete().click();
        DriverManager.waitPageSource();
    }

    public static Visitor emulateVisiting(String apiIdentifier){
        HttpReq request = new HttpReq();
        return request.emulateVisiting(apiIdentifier);
    }
}
