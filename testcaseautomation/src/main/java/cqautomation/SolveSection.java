package cqautomation;

import org.openqa.selenium.*;
import java.util.List;

public class SolveSection {
    WebDriver driver;
    WebElement section;

    public SolveSection(WebDriver driver , WebElement section){
        this.driver=driver;
        this.section=section;
    }

    public void solveAllQuestions() throws Exception{

        int totalQuestions = section.findElements(By.xpath(".//tbody/tr")).size();


        for(int i=0;i<totalQuestions;i++){

            System.out.println("Section reference " + i + section);
            // get questions in every iteration to avoid staleElement 
            List<WebElement> questions = section.findElements(By.xpath(".//tbody/tr"));
            System.out.println("Wrong Number "+ i);
            WebElement quest =questions.get(i);

            String qType=quest.findElement(By.xpath(".//td[3]")).getText();

            switch (qType) {
                case "MCQ" :
                    quest.findElement(By.xpath(".//td[5]/a")).click();                        
                    MCQAttempt mcqAttempt=new MCQAttempt(driver);
                    break;
                case "MQ" :
                    quest.findElement(By.xpath(".//td[5]/a")).click();
                    MQAttempt mqAttempt = new  MQAttempt(driver);
                    break;
                case "Coding" :
                    quest.findElement(By.xpath(".//td[5]/a")).click();
                    CodingAttempt codingAttempt = new CodingAttempt(driver);
                    break;
                case "Subjective" :
                    // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//td[2]"))).click();
                    quest.findElement(By.xpath("//tbody/tr/td[5]/a")).click();
                    System.out.println("Subject Q solution started and clicked");
                    SubjectiveAttempt subjectiveAttempt = new SubjectiveAttempt(driver);
                    subjectiveAttempt.solve();
                    System.out.println("Subjective solved");
                    break;    
                case "Web" :    
                    quest.findElement(By.xpath(".//td[5]/a")).click();
                    WebAttempt webAttempt = new WebAttempt(driver);
                    break;
                    
                default:
                    System.out.println("Select the correct question type");
                    break;
            }
        }
    }
    public void submitSection(){
        
    }
}
