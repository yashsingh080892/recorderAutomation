package org.example.demorecorder;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {

    @Test
    public void recorderTool() throws InterruptedException {


            ChromeOptions options = new ChromeOptions();
            String extensionPath = "/Users/yashaswi.singh/Downloads/build-chrome 4_blackpearl_test";
            options.addArguments("load-extension=" + extensionPath);

            WebDriver driver = new ChromeDriver(options);
            driver.get("https://app.testsigma.com/ui/");
            driver.manage().window().maximize();
            driver.findElement(By.xpath("//input[@id=\"login-email\"]")).sendKeys("rajesh@democheck.com");
            driver.findElement(By.xpath("//input[@id=\"login-password\"]")).sendKeys("Rajesh0626@");


            List<WebElement> loginButtons = driver.findElements(By.xpath("//button[text()='Log in']"));
            List<WebElement> signInButtons = driver.findElements(By.xpath("//button[text()='Sign in']"));

            // Use if-else to decide which button to click
            if (!loginButtons.isEmpty()) {
                // If "Log in" button is present, click it
                loginButtons.get(0).click();
            } else if (!signInButtons.isEmpty()) {
                // If "Log in" button is not present, check for "Sign in" and click it
                signInButtons.get(0).click();
            } else {
                System.out.println("Neither 'Log in' nor 'Sign in' button is found.");
            }

            Thread.sleep(10000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("beamerAnnouncementPopup")));
            driver.switchTo().frame(iframe);
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("popupClose")));
            closeButton.click();
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("//*[contains(text(), 'Create Tests')]")).click();
            driver.findElement(By.xpath("//span[text()=\"Test Cases\"]")).click();
            Thread.sleep(1000);
            driver.get("https://app.testsigma.com/ui/td/103/cases/filters/1914");
            Thread.sleep(5000);
            driver.findElement(By.xpath("//span[text()=\"Record\"]")).click();
            Thread.sleep(5000);




            Set<String> windowHandles = driver.getWindowHandles();
            List<String> windowList = new ArrayList<>(windowHandles);
            System.out.println("Number of open windows: " + windowList.size());


            for (String windowHandle : windowList) {
                driver.switchTo().window(windowHandle);
                System.out.println("Window Title: " + driver.getTitle());
                if (driver.getTitle().equals("")){
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("window.focus();");


                    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement iframe1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ts-recorder-widget-frame")));
                    driver.switchTo().frame(iframe1);
                    WebElement refresh = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid=\"refresh\"]")));
                    refresh.click();
                    Thread.sleep(5000);


                    WebElement addNewStep = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid=\"add\"]")));
                    addNewStep.click();
                    Thread.sleep(10000);


                }
            }





        }



    }



