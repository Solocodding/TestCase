package cqautomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MQAttempt {
    WebDriver driver;
    public MQAttempt(WebDriver driver){
        this.driver=driver;
    }

    public void solve() throws Exception {
        String Ans[]={"abc","def","ghi","jkl"};
        
        Thread.sleep(2000);
        int SubQuestions = driver.findElements(By.xpath("//div[@class='ant-form-item']")).size();
        System.out.println( "SubQuestions "+SubQuestions);

        for(int i=0;i<SubQuestions;i++){
         driver.findElements(By.xpath("//div[@class='ant-form-item' or @class ='ant-form-item ant-form-item-has-success']//textarea")).get(i).sendKeys(Ans[i%4]);
            System.out.println("SubQuestion "+ i + "Solved");
        }
        System.out.println("After solving MQ ");
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
        driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
    }
}
