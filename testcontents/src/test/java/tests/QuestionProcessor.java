package tests;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuestionProcessor {
    private WebDriver driver;
    private XSSFSheet sheet;
    private int sectionIndex;

    public QuestionProcessor(WebDriver driver, XSSFSheet sheet, int sectionIndex) {
        this.driver = driver;
        this.sheet = sheet;
        this.sectionIndex = sectionIndex;
    }

    public int processQuestions(int rowNum) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        String originalTab = driver.getWindowHandle();
       
        //Question count for each section 
        List<WebElement> questions = wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//table//tr[" + sectionIndex + "]//table/tbody/tr")));
                
        for (int j = 0; j < questions.size(); j++) {
            //selecting each question one by one 
            WebElement question = wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//table//tr[" + sectionIndex + "]//table/tbody/tr["+ (j+1) +"]")));

            //Question type
            wait.until(ExpectedConditions.visibilityOf(question));
            String QType = question.findElement(By.xpath(".//td[5]")).getText(); // Question type

            WebElement editButton;
            if(QType.equals("Coding")){
               editButton = question.findElement(By.xpath("(.//td[8]//button)[2]")); 
            }else{
                editButton = question.findElement(By.xpath(".//td[8]//button"));
            }
            wait.until(ExpectedConditions.visibilityOf(editButton));
            wait.until(ExpectedConditions.elementToBeClickable(editButton));
        
            editButton.click();

            Set<String> windows = driver.getWindowHandles();

            String questionTab="";
            for(String tab:windows){
                if(!tab.equals(originalTab)){
                    questionTab = tab;
                }
            }

            //switch to question tab 
            if(!questionTab.equals("")){
                driver.switchTo().window(questionTab);
            }

            switch (QType) {
                case "Subjective":
                    Subjective subjective = new Subjective(driver, sheet,rowNum);
                    subjective.SubjectiveSolve();
                    rowNum++;
                    break;
                case "MCQ":
                    MCQ mcq = new MCQ(driver, sheet,rowNum);
                    mcq.mcqSolve();
                    rowNum++;
                    break;
                case "Coding":
                    Coding coding = new Coding(driver, sheet,rowNum);
                    coding.codingSolve();
                    rowNum++;
                    break;
                case "MQ":
                    MQ mq = new MQ(driver, sheet, rowNum);
                    mq.mqSolve();
                    rowNum++;
                    break;
                case "Web":
                    Web web = new Web(driver, sheet, rowNum);
                    web.webSolve();
                    rowNum++;
                    break;
                default:
                    System.out.println("Wrong question type: " + QType);
            }
            driver.switchTo().window(originalTab);
        }
        return rowNum;
    }
}
