package cat.babot;

public class WhatsAppLogic {
    private WebAccesManager wam;
    private String phone;
    private String phoneCode;
    private final WhatsAppPage wap;

    public WhatsAppLogic(String phone) {
        wap = new WhatsAppPage();
        this.phone = phone;
    }

    public void start() {
        wap.click(wap.codeButton);
        wap.write(phone, wap.phoneInput);
        wap.click(wap.siguienteButton);
        phoneCode = wap.obtainCode();
        System.out.print("The verification code is: ".concat(phoneCode));
        System.out.println("Please, insert the code in your phone.");
    }
}
