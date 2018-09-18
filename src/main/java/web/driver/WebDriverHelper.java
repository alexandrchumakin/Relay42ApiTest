package web.driver;

import common.StringExtension;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WebDriverHelper {
    public static WebElement findElement(By by){
        WebDriver driver = DriverManager.getInstance();
        WebDriverWait waiter = new WebDriverWait(driver, 5);
        WebElement element = null;
        try{
            waiter.until(ExpectedConditions.presenceOfElementLocated(by));
            element = driver.findElement(by);
            scrollToElement(driver, element);    // try to make focus
        } catch(Exception ex){
            System.out.println(String.format("\r\nDEBUG: Cannot set focus to element with '%1$s' locator, error: '%2$s'", by, StringExtension.formatMessage(ex)));
        }
        return element;
    }

    private static void scrollToElement(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void dragElement(WebElement from, WebElement to){
        Screen screen = new Screen();
        String moveFrom = String.format("images/%1$s.png", StringExtension.generateString());
        String moveTo = String.format("images/%1$s.png", StringExtension.generateString());
        getScreenOfElement(from, moveFrom);
        getScreenOfElement(to, moveTo);
        try {
            screen.dragDrop(moveFrom, moveTo);
        } catch (FindFailed findFailed) {
            System.out.println("Failed to perform drag and drop: " + StringExtension.formatMessage(findFailed));
        }
        removeImages();
    }

    private static void removeImages(){
        String absPath = new File("").getAbsoluteFile().toString();
        try {
            FileUtils.deleteDirectory(new File(absPath +"/images"));
        } catch (IOException e) {
            System.out.println("Cannot remove folder images");
        }
    }

    private static void getScreenOfElement(WebElement element, String path){
        File screenshot = ((TakesScreenshot)DriverManager.getInstance()).getScreenshotAs(OutputType.FILE);
        try {
            BufferedImage img = ImageIO.read(screenshot);
            Rectangle rect = new Rectangle(element.getLocation(), element.getSize());
            Point p = element.getLocation();
            BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
            ImageIO.write(dest, "png", screenshot);
            String absPath = new File("").getAbsoluteFile().toString();
            FileUtils.copyFile(screenshot, new File(absPath +"/" + path));
        } catch(Exception ex) {
            System.out.println(String.format("\r\nDEBUG: cannot take screenshot if element with tag '%1$s' by path '%2$s'", element.getTagName(), path));
        }
    }
}
