package cat.babot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static cat.babot.WebAccesManager.driver;

public class WhatsAppPage {
    private final Logger atenea = Logger.getLogger("Actions Web Driver");

    public By codeButton = By.xpath("//span[@role='button']");
    public By phoneInput = By.xpath("//input[contains(@class, 'selectable-text')]");
    public By siguienteButton = By.xpath("//div[@role='button']/div/div");
    public By codeElement = By.xpath("//div[contains(@aria-details, 'instructions')]//span");

    protected void click(By loc) {
        getUntilClickable(loc).click();
        atenea.info("CLICK ON > ".concat(loc.toString()));
    }

    protected void write(String value, By loc) {
        WebElement element = getUntilClickable(loc);
        element.clear();
        element.sendKeys(value);
        atenea.info("WRITE '".concat(value).concat("' IN > ".concat(loc.toString())));
    }

    public String obtainCode() {
        List <WebElement> codeElements = driver.findElements(codeElement);
        return codeElements.stream().map(WebElement::getText).collect(Collectors.joining(" "));
    }

    private static WebElement getUntilClickable(By loc) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(loc));
    }

}
