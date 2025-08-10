package tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

public class TestExecutor {
    private WebDriver driver;
    private List<String> urls;
   
    public TestExecutor(WebDriver driver, List<String> urls) {
        this.driver=driver;
        this.urls = urls;
    }

    public void executeTests() throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //workbook creation
        XSSFWorkbook workbook = new XSSFWorkbook();

        for(String url:urls){
            driver.navigate().to(url);
            
            String sheetName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2"))).getText();
            XSSFSheet sheet = workbook.createSheet(sheetName);
            // System.out.println("\n" +sheetName +"\n");   

            SectionProcessor sectionProcessor = new SectionProcessor(driver, sheet);
            sectionProcessor.processSections();
        }
        
        // System.out.println("Current directory: " + System.getProperty("user.dir"));

        FileOutputStream fos = new FileOutputStream("TestContentReport.xlsx");
        workbook.write(fos);
        fos.close();
        workbook.close();
    }
}
