package cqautomation;

import org.openqa.selenium.*;
import java.util.List;

public class SolveSection {
    WebDriver driver;
    int sectionCount, questCount;

    public SolveSection(WebDriver driver, int sectionCount, int questCount) {
        this.driver = driver;
        this.sectionCount = sectionCount;
        this.questCount = questCount;
    }

    public void solveAllQuestions() throws Exception {

        // List<WebElement> sections = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By
        //     .xpath("//div[@class='dashboard-segment-container active' or @class='dashboard-segment-container ']"))));
        // System.out.println("total sections= "+sections.size());

        List<WebElement> sections = driver.findElements(By
            .xpath("//div[@class='dashboard-segment-container active' or @class='dashboard-segment-container ']"));
        System.out.println("total sections= "+sections.size());

        WebElement currentSection = sections.get(sectionCount);

        WebElement questionToSolve = currentSection.findElements(By.xpath(".//tbody/tr")).get(questCount);

        String qType = questionToSolve.findElement(By.xpath(".//td[3]")).getText();
        System.out.println(qType);

        switch (qType) {
            case "MCQ":
                questionToSolve.findElement(By.xpath(".//td[5]/a")).click();
                MCQAttempt mcqAttempt = new MCQAttempt(driver);
                mcqAttempt.solve();
                break;
            case "MQ":
                questionToSolve.findElement(By.xpath(".//td[5]/a")).click();
                MQAttempt mqAttempt = new MQAttempt(driver);
                mqAttempt.solve();
                break;
            case "Coding":
                questionToSolve.findElement(By.xpath(".//td[5]/a")).click();
                CodingAttempt codingAttempt = new CodingAttempt(driver);
                codingAttempt.solve();
                break;
            case "Subjective":
                questionToSolve.findElement(By.xpath(".//td[5]/a")).click();
                SubjectiveAttempt subjectiveAttempt = new SubjectiveAttempt(driver);
                subjectiveAttempt.solve();
                break;
            case "Web":
                questionToSolve.findElement(By.xpath(".//td[5]/a")).click();
                WebAttempt webAttempt = new WebAttempt(driver);
                webAttempt.solve();
                break;

            default:
                System.out.println("Select the correct question type");
                break;
        }
    }
}
