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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        Row row = sheet.createRow(rowNum);

        String url=driver.getCurrentUrl();
        String QuestID = url.substring(url.lastIndexOf("/") + 1);

        String qName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtQuesTitle']"))).getAttribute("value");
        String qScore = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='score']"))).getAttribute("value");
        String qKeywords="";
        String qDescription = "";

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

        List<WebElement> allOptionElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.xpath("//textarea[@rows='2']")));
     
        int i=8;
        for (WebElement option : allOptionElements) {
            String lang ="";
            lang = option.getText().trim();
            // System.out.println("Option  : " + lang);

            if(!lang.isEmpty()){
                row.createCell(i).setCellValue(lang);
            }
            i++;
        }

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        int optionNumber = -1;
        for (int j = 0; j < checkboxes.size(); j++) {
            if (checkboxes.get(j).isSelected()) {
                optionNumber = j + 1;  // because list is 0-based, but options start at 1
                break;
            }
        }

        
        

        row.createCell(1).setCellValue("https://assess.testpad.chitkara.edu.in/questions/preview/" + QuestID);
        row.createCell(2).setCellValue(QuestID);
        row.createCell(3).setCellValue("MCQ");
        row.createCell(4).setCellValue(qName);
        row.createCell(5).setCellValue(qDescription);
        row.createCell(6).setCellValue(qScore);
        row.createCell(7).setCellValue(qKeywords);
        row.createCell(14).setCellValue(optionNumber);
    

        driver.switchTo().defaultContent();
        driver.close();
    }
}
