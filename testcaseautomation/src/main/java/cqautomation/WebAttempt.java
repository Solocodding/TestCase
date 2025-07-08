package cqautomation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class WebAttempt {
    WebDriver driver;
    public WebAttempt(WebDriver driver){
        this.driver=driver;
    }
    
    public void solve() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> LangTabs = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//div[@class='ant-tabs-tab' or @class='ant-tabs-tab ant-tabs-tab-active']")));

        System.out.println("Total lang option in web Q "+LangTabs.size());

        for(WebElement tab: LangTabs){
            String type = tab.getText().trim();
            System.out.println(type);
            wait.until(ExpectedConditions.visibilityOf(tab));
            wait.until(ExpectedConditions.elementToBeClickable(tab));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tab);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);

            switch (type) {

                case "index.html":
                    typeInAceEditor("html", "Html code");
                    break;
                case "index.css":
                    typeInAceEditor("css", "CSS code");
                    break;
                case "index.js":
                    typeInAceEditor("js", "JS code");
                    break;
                default:
                    System.out.println("select Correct type question");
                    break;
            }
        }
        WebElement submitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button//span[text()='submit']")));
        try{
            // wait.until(ExpectedConditions.visibilityOf(submitBtn)).click();
            wait.until(ExpectedConditions.visibilityOf(submitBtn));
            wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
            Thread.sleep(5000); 
        }catch(Exception e){}

        driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
    } 

    public void typeInAceEditor(String editorId, String content) {
        WebElement textarea = driver.findElement(By.cssSelector("#" + editorId + " textarea"));
        textarea.sendKeys(content);
    }
}
