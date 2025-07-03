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

    public void solve() throws Exception {
        boolean withFile = driver.findElements(By.xpath("//input[@type='file']")).size() > 0;
        System.out.println(withFile);
        // Set answer in Quill editor using JS
        ((JavascriptExecutor) driver).executeScript(
            "document.querySelector('.ql-editor').innerHTML = arguments[0];", Ans);

        if (withFile) {
            System.out.println("File upload field found, uploading...");
            String file = "/Users/amarveersingh/Downloads/FileUpload.pdf";
            driver.findElement(By.xpath("//input[@type='file']")).sendKeys(file);
        } else {
            System.out.println("No file upload option.");
        }

        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
    }

}
