package cqautomation;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class SubjectiveAttempt {
    WebDriver driver;

    String Ans = "this is String to answer subjective type question";

    public SubjectiveAttempt(WebDriver driver) {
        this.driver = driver;
    }

    public void solve() {
        
        // Set answer in Quill editor using JS
        // ((JavascriptExecutor) driver).executeScript(
        //     "document.querySelector('.ql-editor').innerHTML = arguments[0];", Ans);

        driver.findElement(By.cssSelector("div.ql-editor")).sendKeys(Ans);

        try{
            String file = "/Users/amarveersingh/Downloads/FileUpload.pdf";
            
            driver.findElement(By.id("upldFile")).sendKeys(file);
        }
        catch(Exception e){
            System.out.println("No file");
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
    }
}