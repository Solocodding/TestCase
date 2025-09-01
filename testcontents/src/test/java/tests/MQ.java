package tests;

import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MQ {
    private WebDriver driver;
    private XSSFSheet sheet;
    private int rowNum;

    public MQ(WebDriver driver,XSSFSheet sheet,int rowNum){
        this.driver = driver;
        this.sheet = sheet;
        this.rowNum = rowNum;
    }
    public void mqSolve(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String qName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtQuesTitle']"))).getAttribute("value");
        String qScore = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='score']"))).getAttribute("value");
        String qKeywords="";
        String qDescription = "";
        String MQ_Questions="";

        try{
            List<WebElement> tags = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='ant-select-selection-overflow']//span/span[@class='ant-tag ant-tag-default css-f9u17k']")));
            
            for(WebElement tag: tags){
                qKeywords += tag.getText()+" ";
            }

            qDescription = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id=\"txtQues\"]//div[@class=\"ql-editor\"]/p"))).getText();

        }catch(Exception e){
            System.out.println("No keywords or descriptions");
        }
        
        List<WebElement> allQuestions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//textarea[@rows='2' and @placeholder='Question']")));

        System.out.println("Total MQ quest= " + allQuestions.size());
        
        for (WebElement option : allQuestions) {
            String quest = option.getText().trim();
            MQ_Questions += quest+" ";
        }
        
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("MQ");
        row.createCell(1).setCellValue(qName);
        row.createCell(2).setCellValue(qDescription);
        row.createCell(3).setCellValue(qScore);
        row.createCell(4).setCellValue(qKeywords);
        row.createCell(8).setCellValue(MQ_Questions);
        

        driver.switchTo().defaultContent();
        driver.close();
    }
}
