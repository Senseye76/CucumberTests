package AllMyStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyStepdefsAdd {

    private WebDriver driver;


         @Given("I have the first number {int}")
        public void iHaveTheFirstNumber ( int first){

             driver = new ChromeDriver();
             driver.get("https://www.marshu.com/articles/calculate-addition-calculator-add-two-numbers.php");

             //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

             WebElement popUp = driver.findElement(By.cssSelector(".fc-button-label"));
             popUp.click();

             WebElement field = driver.findElement(By.name("n01"));
             field.sendKeys(String.valueOf(first));
        }


    @And("I have the second number {int}")
        public void iHaveTheSecondNumber ( int second){

            WebElement field = driver.findElement(By.name("n02"));
            field.sendKeys(String.valueOf(second));
        }

        @Then("I am adding the two numbers")
        public void iAmAddingTheTwoNumbers () {
            WebElement addButton = driver.findElement(By.xpath("//input[@value='Find Addition']"));
            addButton.click();
        }

        @When("I will get the result {int}")
        public void iWillGetTheResult ( int expected){
             WebElement field = driver.findElement(By.tagName("answer"));
             String actual = field.getAttribute("value");

             assertEquals(Integer.toString(expected), actual);
        }
}


