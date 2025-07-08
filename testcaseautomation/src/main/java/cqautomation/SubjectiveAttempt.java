package cqautomation;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubjectiveAttempt {
    WebDriver driver;

    String Ans = "this is String to answer subjective type question";

    public SubjectiveAttempt(WebDriver driver) {
        this.driver = driver;
    }

    public void solve() throws Exception {
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.ql-editor")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ql-editor")));
        editor.click();
        editor.sendKeys(Ans);
        
        try{
            String filePath = "src/test/resources/fileupload.pdf";
            File file =new File(filePath);

            if(!file.exists()){
                System.out.println("File not found");
            }
            else{
                driver.findElement(By.id("upldFile")).sendKeys(file.getAbsolutePath());
            }
        }
        catch(Exception e){
            System.out.println("No file upload option");
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
        driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
    }
}