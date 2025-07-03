package cqautomation;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubjectiveAttempt {
    WebDriver driver;

    String Ans = "this is String to answer subjective type question";

    public SubjectiveAttempt(WebDriver driver) {
        this.driver = driver;
    }

    public void solve() throws Exception {
        
        driver.findElement(By.cssSelector("div.ql-editor")).sendKeys(Ans);
        // Thread.sleep(2000);
        try{
            Path filePath = Paths.get("src","test","resources","FileUpload.pdf");
            File file = filePath.toFile();
            // String file = "/Users/amarveersingh/Downloads/FileUpload.pdf";
            driver.findElement(By.id("upldFile")).sendKeys(file.getAbsolutePath());
        }
        catch(Exception e){
            System.out.println("No file");
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
        // Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
    }
}