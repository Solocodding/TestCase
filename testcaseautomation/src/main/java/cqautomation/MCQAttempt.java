package cqautomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MCQAttempt {
    WebDriver driver;

    public MCQAttempt (WebDriver driver){
        this.driver = driver;
    }
    public void solve() throws Exception {
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int totalOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='ant-space-item']"))).size();
        
        int OptionToSelect=(int)(Math.random()*totalOptions); 

        WebElement Option = driver.findElements(By.xpath("//div[@class='ant-space-item']")).get(OptionToSelect);
        Option.findElement(By.xpath(".//input")).click();
        
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
        driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
    }
    
}
