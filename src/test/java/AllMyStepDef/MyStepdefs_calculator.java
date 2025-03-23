package AllMyStepDef;

import common.Calculator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;


public class MyStepdefs_calculator {

    public Calculator calculator;

    @Before
    public void setUp () {
        System.out.println("The test is setup and ready to go");
    }

    @Given("I have two numbers {double} and {double}")
    public void iHaveTwoNumbersAnd(double first, double second) {
        calculator = new Calculator(first, second);
        System.out.println("I have two numbers");

    }

    @When("I add the two numbers")
    public void iAddTheTwoNumbers() {

        calculator.add();
        System.out.println("Adding numbers");
    }

    @When("I mul the two numbers")
    public void iMultiplyTheTwoNumbers() {

        calculator.mul();
    }
    @When("I div the two numbers")
    public void iDivideTheTwoNumbers() {

        calculator.div();
    }
    @Then("I get the result {double}")
    public void iGetTheResult(double expected) {
        double actual = calculator.getResult();


        assertEquals(expected, actual, 0.001);

        System.out.println("And get result");



    }

    @When("I sub the two numbers")
    public void iSubtractTheTwoNumbers() {

        calculator.sub();

    }

    @Given("the test case starts with this")
    public void theTestTestCaseStartsWithThis() {
        System.out.println("Test starts here");
    }

    @And("then something else happens")
    public void thenSomethingElseHappens() {
        System.out.println("Something is happening");

    }
    @After
    public void tearDown() {
        System.out.println("The test is done");

    }
}

