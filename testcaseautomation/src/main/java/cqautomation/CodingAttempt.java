package cqautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CodingAttempt {
    WebDriver driver;
    public CodingAttempt(WebDriver driver){
        this.driver =driver;
    }
    public void solve() {
        // Logic to select MCQ option and submit
        System.out.println("coding solved");
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
        driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
    }
    
}
