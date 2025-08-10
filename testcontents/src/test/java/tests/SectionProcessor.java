package tests;

import java.time.Duration;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SectionProcessor {
    private WebDriver driver;
    private XSSFSheet sheet;

    public SectionProcessor(WebDriver driver, XSSFSheet sheet){
        this.driver = driver;
        this.sheet = sheet;
    }

    public void processSections()throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> sections = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//tbody/tr/td[2]//div[@role=\"button\"]")));

        // System.out.println(sections.size());

        for(int i=0;i<sections.size();i++){
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody/tr/td[2]//div[@role=\"button\"]"))).get(i).click();
            QuestionProcessor questionProcessor = new QuestionProcessor(driver , sheet, i+1);
            // System.out.println("Section handled "+ (i+1));
            questionProcessor.processQuestions();
            
        }
    }
}

