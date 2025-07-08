package cqautomation;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SolveSection {
    WebDriver driver;
    int sectionCount, questCount;

    public SolveSection(WebDriver driver, int sectionCount, int questCount) {
        this.driver = driver;
        this.sectionCount = sectionCount;
        this.questCount = questCount;
    }

    public void solveAllQuestions() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String sectionXpath = "//*[@class='dashboard-segment-container ' or @class='dashboard-segment-container active']";
        
        String qType = wait
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(sectionXpath)))
        .get(sectionCount)
        .findElements(By.xpath(".//tbody/tr"))
        .get(questCount)
        .findElement(By.xpath(".//td[3]")).getText();
        
        // String qType = questionToSolve.findElement(By.xpath(".//td[3]")).getText();

        WebElement questionLink = wait
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(sectionXpath)))
            .get(sectionCount)
            .findElements(By.xpath(".//tbody/tr"))
            .get(questCount)
            .findElement(By.xpath(".//td[2]/a"));

        questionLink.click(); 

        switch (qType) {
            case "MCQ":
                MCQAttempt mcqAttempt = new MCQAttempt(driver);
                mcqAttempt.solve();
                break;
            case "MQ":
                MQAttempt mqAttempt = new MQAttempt(driver);
                mqAttempt.solve();
                break;
            case "Coding":
                CodingAttempt codingAttempt = new CodingAttempt(driver);
                codingAttempt.solve();
                break;
            case "Subjective":
                SubjectiveAttempt subjectiveAttempt = new SubjectiveAttempt(driver);
                subjectiveAttempt.solve();
                break;
            case "Web":
                WebAttempt webAttempt = new WebAttempt(driver);
                webAttempt.solve();
                break;

            default:
                System.out.println("Select the correct question type");
                break;
        }
    }
}
