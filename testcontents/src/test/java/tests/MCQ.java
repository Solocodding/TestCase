package tests;

import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MCQ {
    private WebDriver driver;
    private XSSFSheet sheet;
    private int rowNum;

    public MCQ(WebDriver driver , XSSFSheet sheet,int rowNum){
        this.driver = driver;
        this.sheet = sheet;
        this.rowNum = rowNum;
    }
    public void mcqSolve(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String qName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtQuesTitle']"))).getAttribute("value");
        String qScore = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='score']"))).getAttribute("value");
        String qKeywords="";
        String qDescription = "";

        try{
            // List<WebElement> tags = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            //     By.xpath("//div[@class='ant-select-selection-overflow']//span/span[@class='ant-tag ant-tag-default css-f9u17k']")));
            
            // for(WebElement tag: tags){
            //     qKeywords += tag.getAttribute("value")+" ";
            // }

            qDescription = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id=\"txtQues\"]//div[@class=\"ql-editor\"]/p"))).getText();

        }catch(Exception e){
            System.out.println("No keywords or descriptions");
        }
        
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("MCQ");
        row.createCell(1).setCellValue(qName);
        row.createCell(2).setCellValue(qDescription);
        row.createCell(3).setCellValue(qScore);

        driver.switchTo().defaultContent();
        driver.close();
    }
}
