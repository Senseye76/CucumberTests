package AllMyStepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStepdefsAddv2 {

    private WebDriver driver;

    @Given("I'm adding and i have the first number {int}")
    public void iMAddingTheFirstNumber(int first) {

        driver = new ChromeDriver();
        driver.get("https://www.marshu.com/articles/calculate-addition-calculator-add-two-numbers.php");

        WebElement field = driver.findElement(By.name("n01"));
        field.sendKeys(String.valueOf(first));
    }

    @And("I'm adding and i have the second number {int}")
    public void iMAddingTheSecondNumber(int second) {

    }

    @Then("I'm adding the two numbers")
    public void iMAddingTheTwoNumbers() {

    }

    @When("Getting the result {int}")
    public void gettingTheResult(int expected) {
    }


}
