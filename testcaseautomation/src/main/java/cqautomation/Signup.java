package cqautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Signup {

    WebDriver driver = null;
    int n;

    public Signup(WebDriver driver, int n) {
        this.driver = driver;
    };


    public void autoSignup(WebDriver driver , String arr[], int i) throws Exception{

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://tests.cqtestga.com/test/1750997410369");
        // driver.get("https://tests.cqtestga.com/test/1751882623443");
        // driver.get("https://tests.cqtestga.com/test/1751690961413");
        // driver.get("https://tests.cqtestga.com/test/1751715740072");
        //mq
        // driver.get("https://tests.cqtestga.com/test/1747480945432");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='sign up']"))).click();

        driver.findElement(By.id("name")).sendKeys(arr[i]);
        driver.findElement(By.id("email")).sendKeys(arr[i] + "@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Master@123");                
        driver.findElement(By.id("quizCode")).sendKeys("1234");
        driver.findElement(By.id("mobile")).sendKeys("1234567890");
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type=\"submit\"]"))).click();
        Thread.sleep(2000);
                
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='start-quiz-container']/button"))).click();
    }
    
    public String[] GenerateEmails(int n){
        
        String arr[]=new String[n];
        String GmailString="abcdefghijklmnopqrstuvwxyz1234567890";
        int min=5;
        int max=10;
        
        for(int j=0;j<n;j++){
            int mailLength=(int)(Math.random()*(max-min+1))+min; 
            StringBuffer usermail=new StringBuffer();

            for(int i=0;i<mailLength;i++){
                int mailcharIndex=(int)(Math.random()*36);
                usermail.append((GmailString.charAt(mailcharIndex)));
            }
            arr[j]="cqtest"+usermail.toString();
        }
        return arr;
    }
}
