package cat.babot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WebAccesManager.createAndStartService();
        WebAccesManager.createDriver();
        WebAccesManager.enterSite();
        System.out.print("Please, insert phone number:");
        Scanner sc = new Scanner(System.in);
        new WhatsAppLogic(sc.nextLine()).start();
        WebAccesManager.quitSite();
    }
}