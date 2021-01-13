/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package godaddy;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
//import org.junit.Assert;


/**
 *
 * @author Masuma Tasneem
 */
public class GoDaddy {

    private static WebDriver driver= null;
    
    public static void main(String[] args) {
        
        // I have chosen Chrome for browser 
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\NetBeans 8.2\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        
        EventFiringWebDriver eventHandler = new EventFiringWebDriver(driver);
        try{
        eventHandler.navigate().to("https://au.godaddy.com/");
        System.out.println("The webpage has loaded");}
        catch(Exception e){
            System.out.println("The webpage could not be loaded due to: "+e);
        }
        driver.manage().window().maximize();
        
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        
        /***** TASK(1) SENDING VALUE INSIDE SEARCH BOX   ********/
        try{
        WebElement TextBar= driver.findElement(By.xpath("//input[@name='domainToCheck']"));
        TextBar.sendKeys(".net");
        System.out.println(".net has been written inside search box");}
        catch(Exception e){
            System.out.println("The path to element can not be reached due to: "+e);
        }     
        
        
        /***** TASK(2) CLICKING SEARCH BUTTON ********/
        try{
        WebElement SearchButton= driver.findElement(By.xpath("//button[@class='btn btn-search bg-teal-550  ']"));
        SearchButton.click();
        System.out.println("Search button has been pressed");}
        catch(Exception e){
            System.out.println("The action is not performed due to: "+e);
        }
        
        java.util.Set<String>handles= driver.getWindowHandles();    //Just in case the search results open up in a new window 
        String winHandle1 = driver.getWindowHandle();               //Incase of this particular project it was not further handled 
        
        
        /***** TASK(3) ADD TO CARD *********/
        WebElement addToCard = driver.findElement(By.xpath("//button[@data-cy='dbs-add-to-cart-button']"));
        addToCard.click();
        
        
        /***** TASK(4) CHECK IF PRODUCT WAS ADDED TO THE CARD *********/
        String changedText= " ";
        String expectedText="Added";
        WebElement AddedToCard= driver.findElement(By.xpath("//span[@class='text-black']"));
        changedText=AddedToCard.getText();
        
        if (expectedText.equals(changedText)) {
            System.out.println("SUCCESS");
        }
         else {
    
            System.out.println("ERROR");
        
        }
        
        /*
        try{
          Assert.assertEquals(expectedText, changedText);
          System.out.println("SUCCESS");
         }catch(AssertionError e){
          System.out.println("ERROR");

        throw e;
        }  */
        
        driver.close();
    
}
}
