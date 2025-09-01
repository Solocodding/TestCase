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
    private int rowNum = 1;

    public SectionProcessor(WebDriver driver, XSSFSheet sheet){
        this.driver = driver;
        this.sheet = sheet;
    }

    public void processSections()throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> sections = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//tbody/tr/td[2]//div[@role=\"button\"]")));

        //Header of sheets;
        createHeader();

        for(int i=0;i<sections.size();i++){
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody/tr/td[2]//div[@role=\"button\"]"))).get(i).click();
            QuestionProcessor questionProcessor = new QuestionProcessor(driver , sheet, i+1);
            rowNum = questionProcessor.processQuestions(rowNum);
            
        }
    }

    private void createHeader() {
        org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);

        headerRow.createCell(1).setCellValue("Question Link");  //new
        headerRow.createCell(2).setCellValue("Question ID");  //new
        headerRow.createCell(3).setCellValue("Question Type");
        headerRow.createCell(4).setCellValue("Question Name");
        headerRow.createCell(5).setCellValue("Question Description");
        headerRow.createCell(6).setCellValue("Question Score");
        headerRow.createCell(7).setCellValue("Question Keywords");
        headerRow.createCell(8).setCellValue("Option1");  //new
        headerRow.createCell(9).setCellValue("Option2");  //new
        headerRow.createCell(10).setCellValue("Option3");  //new
        headerRow.createCell(11).setCellValue("Option4");  //new
        headerRow.createCell(12).setCellValue("Option5");  //new
        headerRow.createCell(13).setCellValue("Option6");  //new
        headerRow.createCell(14).setCellValue("Correct Option");  //new
        // headerRow.createCell(15).setCellValue("Allowed languages");
        // headerRow.createCell(16).setCellValue("Web TestCases");
        // headerRow.createCell(17).setCellValue("MQ Questions");
    }

}

