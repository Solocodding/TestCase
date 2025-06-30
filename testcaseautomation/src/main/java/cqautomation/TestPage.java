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
    
    public void selectSections(){
        List<WebElement> sections = driver.findElements(By.cssSelector(".dashboard-segment-container "));
        System.out.println(sections.size());
    }
}
