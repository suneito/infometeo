package cat.babot.green;

import cat.babot.common.WebAccesManager;

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

}
