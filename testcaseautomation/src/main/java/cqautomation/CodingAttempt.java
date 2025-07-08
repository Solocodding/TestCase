package cqautomation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CodingAttempt {
    WebDriver driver;
    public CodingAttempt(WebDriver driver){
        this.driver =driver;
    }

    public void solve() throws Exception{
    
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ant-select-selector")));
        dropdown.click();

        //short wait to allow dropdown to render 
        Thread.sleep(1000);

        List<WebElement> allOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.cssSelector(".ant-select-item-option-content")));

        System.out.println(allOptions.size());

        // Allowed langs
        List<String> allowedLanguages = Arrays.asList("C", "C++", "Java", "Python 3");
        List<String> presentLanguages = new ArrayList<>();

        System.out.println("Total options fetched = " + allOptions.size());
        
        for (WebElement option : allOptions) {
            String lang = option.getText().trim();
            if (allowedLanguages.contains(lang)) {
                presentLanguages.add(lang);
            }
        }

        if (presentLanguages.isEmpty()) {
            System.out.println("No valid languages available");
            driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
            return;
        }

        String selectedLang = presentLanguages.get(new Random().nextInt(presentLanguages.size()));

        // Click selected language
        for (WebElement option : allOptions) {
            if (option.getText().trim().equals(selectedLang)) {
                option.click();
                break;
            }
        }

        // Switch-case to type code into Ace Editor
        switch (selectedLang) {
            case "C":
                typeInAceEditor("main", "#include<stdio.h> \n int main() {\n  printf(\"even\");\n  return 0;\n");
                break;
            case "C++":
                typeInAceEditor("main", "#include<iostream> \nusing namespace std;\nint main() {\n  cout << \"even\";\n  return 0;\n");
                break;
            case "Java":
                typeInAceEditor("main", "public class Main {\n  public static void main(String[] args) {\n    System.out.println(\"even\");\n  \n");
                break;
            case "Python 3":   
                typeInAceEditor("main", "print(\"even\")");
                break;
            default:
                System.out.println("Selected language not supported.");
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button//span[text()='submit']"))).click();
        Thread.sleep(3000); //small wait to interpret/compile code 

        //click next question button
        By nextQLocator = By.xpath("//button//span[text()='next question']");
        wait.until(ExpectedConditions.presenceOfElementLocated(nextQLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nextQLocator));
        WebElement nextQ = driver.findElement(nextQLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextQ);

        // Go back to dashboard/home
        driver.findElement(By.xpath("//div[@class='app-logo']/button")).click();
    }

    //failed in case when copy paste not allowed

    // public void typeInAceEditor(String editorId, String content) {
    //     ((JavascriptExecutor) driver).executeScript(
    //         "const editor = ace.edit(arguments[0]);" +
    //         "editor.setValue(arguments[1], -1);", // -1 to move cursor to start
    //         editorId,
    //         content
    //     );
    // }

    public void typeInAceEditor(String editorId, String content) throws InterruptedException {
        // Click on editor container (not the textarea)
        WebElement editorContent = driver.findElement(By.cssSelector("#" + editorId + " .ace_content"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editorContent);
        editorContent.click();

        Thread.sleep(200); // allow focus

        WebElement textarea = driver.findElement(By.cssSelector("#" + editorId + " textarea"));

        // Simulate Ctrl+A (or Command+A on Mac) then Backspace to clear
        // Clear the editor content more reliably
        textarea.sendKeys(Keys.chord(Keys.COMMAND, "a")); // or CONTROL on Windows
        Thread.sleep(100);
        textarea.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(100);

        // code writing 
        for (String line : content.split("\n")) {
            textarea.sendKeys(line);
            textarea.sendKeys(Keys.RETURN);
            Thread.sleep(30); // throttle for stability
        }
    }
}
