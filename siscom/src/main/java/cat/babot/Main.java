package cat.babot;

import cat.babot.common.WebAccesManager;
import cat.babot.green.WhatsAppLogic;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static cat.babot.common.WebAccesManager.driver;

public class Main {
    public static void main(String[] args) throws IOException {
        WebAccesManager.createAndStartService();
        WebAccesManager.createDriver();
        WebAccesManager.enterSite("https://web.whatsapp.com/");

        loginWhatsApp();
        //sendMsg("Hello world");
    }

    private static void loginWhatsApp() throws IOException {
        try {
            System.out.print("Please, insert phone number:");
            Scanner sc = new Scanner(System.in);
            new WhatsAppLogic(sc.nextLine()).startByCode();

        }catch(Exception e) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\crashShot.png"));
            WebAccesManager.quitSite();
            e.printStackTrace();
        }
    }
}