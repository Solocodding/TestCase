package cqautomation;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TestPage {

    WebDriver driver=null;

    public TestPage(WebDriver driver){
        this.driver=driver;
    }
    
    //Total section count
    public int getTotalSections(){
        return driver.findElements(By.xpath("//div[@class='dashboard-segment-container active' or @class='dashboard-segment-container ']")).size();
    }
    public int getTotalQuestions(WebElement section){
        return section.findElements(By.xpath(".//tbody/tr")).size();
    }

    public void attemptAllSections() throws Exception{
        int totalSections = getTotalSections();
        for(int s=0;s<totalSections;s++){
            // System.out.println("S value= " +s);
            List<WebElement> sections = driver.findElements(By.xpath("//div[@class='dashboard-segment-container active' or @class='dashboard-segment-container ']"));
            WebElement currentSection = sections.get(s);
            
            int totalQuestions=getTotalQuestions(currentSection);

            for(int q=0;q<totalQuestions;q++){
                // System.out.println("q value= " +q);
                SolveSection solveSection = new SolveSection(driver, s,q);
                solveSection.solveAllQuestions();
                // Thread.sleep(3000);
            }
            if(s!=totalSections-1){
                sectionSubmit(s);
            }
            Thread.sleep(2000);
        }
    }
    public void sectionSubmit(int s) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement section=driver.findElements(By.xpath(
            "//div[@class='dashboard-segment-container active' or @class='dashboard-segment-container ']")).get(s);
        
        section.findElement(By.xpath(".//button/span[text()='submit this section']")).click();

        // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button/span[text()='yes']")));
        WebElement yesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button/span[text()='yes']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesButton);

        String otp = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//div[@class='ant-modal-body']//strong"))).getText();
        driver.findElement(By.xpath("//div[@class='ant-modal-body']//input[@placeholder='Enter Otp']")).sendKeys(otp);
        driver.findElement(By.xpath("//button/span[text()='Submit']")).click();
        
    }
    public void testSubmit(){

    }
}