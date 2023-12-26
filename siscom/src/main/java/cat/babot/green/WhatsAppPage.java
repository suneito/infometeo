package cat.babot.green;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static cat.babot.common.WebAccesManager.driver;

public class WhatsAppPage {
    private final Logger atenea = Logger.getLogger("Actions Web Driver");
    private final Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
        wait.until(d -> driver.findElement(codeElement).isDisplayed());
        List <WebElement> codeElements = driver.findElements(codeElement);
        return codeElements.stream().map(WebElement::getText).collect(Collectors.joining(""));
    }

    private WebElement getUntilClickable(By loc) {
        return wait.until(ExpectedConditions.elementToBeClickable(loc));
    }

}
