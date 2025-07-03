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
    
    //Total section count
    public int getTotalSections(){
        return driver.findElements(By.xpath("//div[@class='dashboard-segment-container active' or @class='dashboard-segment-container ']")).size();
    }

    public void attemptAllSections() throws Exception{
        int totalSections = getTotalSections();

        for(int i=0;i<totalSections;i++){

            //To avoid staleElement problem, get all section again
            List<WebElement> sections = driver.findElements(By.xpath("//div[@class='dashboard-segment-container active' or @class='dashboard-segment-container ']"));
        
            WebElement currentSection = sections.get(i);

            if(!currentSection.getAttribute("class").contains("active")){
                currentSection.click();
            }

            SolveSection solveSection = new SolveSection(driver, currentSection);
            solveSection.solveAllQuestions();

            solveSection.submitSection();
        }
    }
}