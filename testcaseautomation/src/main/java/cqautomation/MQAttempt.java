package cqautomation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MQAttempt {
    WebDriver driver;
    
    public MQAttempt(WebDriver driver){
        this.driver=driver;
    }

    public void solve() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String Ans[]={"abc","def","ghi","jkl"};
        
        // Thread.sleep(2000);
        List<WebElement> textAreas = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='ant-form-item-control-input-content']/span/textarea")));

        for (WebElement textArea : textAreas) {
            textArea.sendKeys(Ans[(int)Math.random()*4]);
        }
     
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
        driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
    }
}
