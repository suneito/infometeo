package cat.babot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.ArrayList;

public class WebAccesManager {
    public static WebDriver driver;
    private static ChromeOptions chromeOptions;

    public static void createAndStartService() {
        WebDriverManager.chromedriver().setup();
        ArrayList<String> optionsList = new ArrayList<>();
        chromeOptions = new ChromeOptions();
        optionsList.add("--start-maximized");
        optionsList.add("--incognito");
        optionsList.add("disable-notifications");
        optionsList.add("--remote-allow-origins=*");
        chromeOptions.addArguments(optionsList);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    }

    public static void createDriver() {
        driver = new ChromeDriver(chromeOptions);
    }

    public static void enterSite() {
        driver.get("https://web.whatsapp.com/");
    }

    public static void quitSite() {
        driver.quit();
    }

}
