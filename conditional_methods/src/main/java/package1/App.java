package package1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Scanner;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        while(n>0){
            WebDriver driver = new ChromeDriver();
            try{
                driver.manage().window().maximize();
                driver.get("https://demo.nopcommerce.com");

                driver.findElement(By.xpath("//a[text()='Register']")).click();
                Thread.sleep(5000);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            finally{
            
                driver.close();
            }
            n--;
        }

        
    }
}
