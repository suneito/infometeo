package cat.babot.green;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static cat.babot.green.WebAccesManager.driver;

public class WhatsAppLogic {
    private WebAccesManager wam;
    private String phone;
    private String phoneCode;
    private final WhatsAppPage wap;

    public WhatsAppLogic(String phone) {
        wap = new WhatsAppPage();
        this.phone = phone;
    }

    public void startByCode() {
        wap.click(wap.codeButton);
        wap.write(phone, wap.phoneInput);
        wap.click(wap.siguienteButton);
        phoneCode = wap.obtainCode();
        System.out.println("The verification code is: ".concat(phoneCode));
        System.out.print("Please, insert the code in your phone.");
    }

    public void startByQr() {
    }

    private static void loginWhatsApp() throws IOException {
        WebAccesManager.createAndStartService();
        WebAccesManager.createDriver();
        WebAccesManager.enterSite("https://web.whatsapp.com/");
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
