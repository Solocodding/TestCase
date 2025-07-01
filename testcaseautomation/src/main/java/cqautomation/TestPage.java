package cqautomation;

import java.util.List;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestPage {

    WebDriver driver=null;
    public TestPage(WebDriver driver){
        this.driver=driver;
    }
    
    public void selectSections(){
        //Select Sections
        List<WebElement> sections = driver.findElements(By.cssSelector(".dashboard-segment-container "));
        for(WebElement Questions:sections){
            //Select Questions of Each sections one by one
            List<WebElement> sectionQuestion = Questions.findElements(By.xpath(".//tbody/tr"));
            
            for(WebElement quest:sectionQuestion){

                String QType=quest.findElement(By.xpath(".//td[3]")).getText();
                System.out.println(QType);

                switch (QType) {
                    case "MCQ" :
                    {
                        quest.findElement(By.xpath(".//td[2]")).click();
                        MCQAttempt mcqAttempt=new MCQAttempt(driver , QType);
                        break;
                    }
                    case "MQ" :
                    {
                        quest.findElement(By.xpath(".//td[2]")).click();
                        MQAttempt mqAttempt = new  MQAttempt(driver , QType);
                        break;
                    }
                    case "Coding" :
                    {
                        quest.findElement(By.xpath(".//td[2]")).click();
                        CodingAttempt codingAttempt = new CodingAttempt(driver , QType);
                        break;
                    }
                    case "Subjective" :
                    {
                        quest.findElement(By.xpath(".//td[2]")).click();
                        SubjectiveAttempt subjectiveAttempt = new SubjectiveAttempt(driver , QType);
                        break;
                    }
                    case "Web" :
                    {
                        quest.findElement(By.xpath(".//td[2]")).click();
                        WebAttempt webAttemp = new WebAttempt(driver , QType);
                        break;
                    }
                    default:
                        System.out.println("Select the correct question type");
                        break;
                }
            }
        }
    }

}