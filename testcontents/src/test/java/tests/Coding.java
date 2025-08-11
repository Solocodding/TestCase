package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Coding {
    private WebDriver driver;
    private XSSFSheet sheet;
    private int rowNum;

    public Coding(WebDriver driver, XSSFSheet sheet,int rowNum){
        this.driver = driver;
        this.sheet = sheet;
        this.rowNum = rowNum;
    }
    public void codingSolve() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String qName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtQuesTitle']"))).getAttribute("value");
        String qScore = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='score']"))).getAttribute("value");
        String qKeywords="";
        String qDescription = "";
        String allowedLanguages ="";

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

        List<WebElement> allLanguages = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("(//div[@class=\"ant-tabs-nav-list\"])[2]/div[@data-node-key]/div")));

        // System.out.println("Total options fetched = " + allLanguages.size());
        
        for (WebElement option : allLanguages) {
            String lang = option.getText().trim();
            allowedLanguages += lang+" ";
        }
        // System.out.println(allowedLanguages);
        
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Coding");
        row.createCell(1).setCellValue(qName);
        row.createCell(2).setCellValue(qDescription);
        row.createCell(3).setCellValue(qScore);
        row.createCell(4).setCellValue(qKeywords);
        row.createCell(5).setCellValue(allowedLanguages);

        driver.switchTo().defaultContent();
        driver.close();
    }
}
