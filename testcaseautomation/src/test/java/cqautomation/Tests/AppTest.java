package cqautomation.Tests;

import cqautomation.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Scanner;

public class AppTest {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        // System.out.println("Enter number of user to automate");
        // int n=sc.nextInt();
        int n = 1;
        WebDriver driver = null;

        for (int i = 0; i < n; i++) {
            try {
                driver = new ChromeDriver();
                // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
                
                // Generate random mails
                Signup signup = new Signup(driver, n);

                String arr[] = signup.GenerateEmails(n);

                signup.autoSignup(driver, arr, i);
                Thread.sleep(1000);

                TestPage testpage = new TestPage(driver);
                testpage.attemptAllSections();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // driver.quit();
                sc.close();
            }
        }
    }
}
