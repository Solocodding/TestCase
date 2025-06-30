package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();  
        try{
            // ChromeDriver driver = new ChromeDriver();
            driver.get("https://codequotient.com/login");
            
            driver.getCurrentUrl();
            driver.getTitle();
            driver.getPageSource();      // return the  pagesource return from server
            driver.getWindowHandle();   // return id of single browser window (sting type) dynamic type

            driver.getWindowHandles();   
            

            //conditional methods
            // isDisplayed();
            // isEnabled();  input,button , checkbox,button 
            // isSelected();

            //driver.navigate().to(URL object or string);
            //driver.navigate().back();
            //driver.navigate().forward();
            //driver.navigate().refresh();
            System.out.println(driver.getTitle());
            // WebElement ele=driver.findElement(By.id("name"));
            // System.out.println(driver.findElement(By.id("logo")).isDisplayed());
            driver.switchTo().frame("loginIframe");
            driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("amargadia002@gmail.com");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("20@Karnal");
            driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
            //.  //input[contains(attribute,value)]. // value can be partial value 
            Thread.sleep(10000);

            //input/descendant::*;
            //input/descendant::div;
            //tag/anscester::form;
            //tahg/anscester::*;

            //div//a;  // double slash is use do jump over multiple elements 

            //form
                //div
                //div.     preceding sibling of self
                //self
                //div     following sibling of self
                //div

            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
     
            driver.close();  // only close current browser window 
            driver.quit();   // close all browser window 
        
        }


    }
}
