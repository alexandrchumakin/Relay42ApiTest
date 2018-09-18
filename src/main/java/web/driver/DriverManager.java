package web.driver;

import common.StringExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    public static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/lib/geckodriver");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void waitPageSource(){
        WebDriver currentDriver = DriverManager.getInstance();
        try {
            for(int i = 0; i < 20; i++) {
                String currentSource = currentDriver.getPageSource();
                Thread.sleep(500);
                String newSource = currentDriver.getPageSource();
                if (currentSource.equals(newSource))
                    break;
                else
                    Thread.sleep(500);
            }
        }
        catch (InterruptedException ex){
            System.out.println(StringExtension.formatMessage(ex));
        }
    }
}
