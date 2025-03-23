package AllMyStepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BasketBallMyStepdefs {

    WebDriver driver;
    Random random;

    String firstName;
    String lastName;
    String email;
    String password;
    String dateOfBirth;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        random = new Random();

        firstName = getRandomFirstName();
        lastName = getRandomLastName();
        email = firstName.toLowerCase() + "." + lastName.toLowerCase() + getRandomNumber(3) + "@gmail.com";
        password = "Test" + getRandomNumber(5) + "!";
        dateOfBirth = getRandomDateOfBirth();


        System.out.println("Skapar test användare:");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("DOB: " + dateOfBirth);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String getRandomFirstName() {
        String[] names = {"Eva", "Ragnar", "Arne", "Albin", "Karl", "Leo", "Mari", "Batman"};
        return names[random.nextInt(names.length)];
    }

    private String getRandomLastName() {
        String[] names = {"Söderback", "Petersson", "Palmer", "Björk", "Israelsson", "Ivarsson", "Rinius"};
        return names[random.nextInt(names.length)];

    }

    private String getRandomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();

    }

    private String getRandomDateOfBirth() {
        int year = 1970 + random.nextInt(40);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(30);

        LocalDate dob = LocalDate.of(year, month, day);
        return dob.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private void fillRequiredFields(boolean acceptTerm) throws InterruptedException {
        driver.findElement(By.id("member_firstname")).sendKeys(firstName);
        driver.findElement(By.id("member_lastname")).sendKeys(lastName);
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(password);
        driver.findElement(By.id("dp")).sendKeys(dateOfBirth);

        Thread.sleep(500);


        driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[7]/label")).click();
        Thread.sleep(100);
        driver.findElement(By.cssSelector("label[for='sign_up_25']")).click();
        Thread.sleep(100);
        driver.findElement(By.cssSelector("label[for='sign_up_26']")).click();
        Thread.sleep(500);
    }

    @Given("The user is on the BasketBall regitration page")
    public void theUserIsOnTheBasketBallRegitrationPage() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @And("The user fills in all the required fields")
    public void theUserFillsInAllTheRequiredFields() throws InterruptedException {
        fillRequiredFields(true);
    }

    @And("The user press {string}")
    public void theUserPress(String buttonLabel) {
        driver.findElement(By.name("join")).click();
    }

    @And("The account should be created")
    public void theUserAccountShouldBeCreated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("SignUpConfirmation"));

        WebElement confirmationMassage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/h2"))
        );

        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after valid registration: " + currentUrl);


        if (!currentUrl.contains("Confirmation") || !currentUrl.contains("MembershipNumber")) {
            throw new AssertionError("Account not created. Current URL: " + currentUrl);
        }

        System.out.println("Account created, check your e-mail: " + email);
    }

    @But("Leaves the last name field empty")
    public void butLeavesTheLastNameFieldEmpty()  {
    }

    @Then("The user should see an error message last name is required")
    public void theUserShouldSeeAnErrorMessageLastNameIsRequired() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("label[for='member_lastname']")));

        Thread.sleep(500);

        String actualErrorText = errorMessage.getText();

        if (!actualErrorText.contains("Last name is required")) {
            throw new AssertionError("Error message not found " + actualErrorText);
        }

        System.out.println(" Last name missing error message is: " + actualErrorText);
    }


    @And("The account should not be created")
    public void theAccountShouldNotBeCreated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String currentUrl = driver.getCurrentUrl();

        if (currentUrl.contains("Confirmation") || currentUrl.contains("Success") || currentUrl.contains("Thank-you")) {
            throw new AssertionError("The account have been created, but it should not have been. Current URL is:" + currentUrl);
        }

        System.out.println(" The account has not been created, the user is still on the registration page: " + currentUrl);
    }

    @And("The user fills in the required fields but leaves the first name field empty")
    public void theUserFillsInTheRequiredFieldsButLeavesTheFirstNameFieldEmpty() throws InterruptedException {
//        driver.findElement(By.id("member_lastname")).sendKeys(lastName);
//        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
//        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
//        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
//        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(password);
//        driver.findElement(By.id("dp")).sendKeys(dateOfBirth);
//
//
//        driver.findElement(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[7]/label")).click();
//        Thread.sleep(100);
//        driver.findElement(By.cssSelector("label[for='sign_up_25']")).click();
//        Thread.sleep(100);
//        driver.findElement(By.cssSelector("label[for='sign_up_26']")).click();
//        Thread.sleep(500);



    }


    @Then("The user should see an error message first name is required")
    public void theUserShouldSeeAnErrorMessageFirstNameIsRequired() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//
//        try {
//            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'First name is required')]")));
//
//            if (!errorMessage.isDisplayed()) {
//                throw new AssertionError("Felmeddelandet för förnamn visas inte!");
//            }
//
//            System.out.println(" Felmeddelandet 'First name is required' visas korrekt.");
//        } catch (Exception e) {
//            throw new AssertionError("Felmeddelandet för saknat förnamn hittades inte på sidan!");
//        }
    }

        @But("The user does not accept the terms and conditions")
        public void theUserDoesNotAcceptTheTermsAndConditions() {
//            WebElement termsCheckbox = driver.findElement(By.cssSelector("label[for='sign_up_25']"));
//            if (termsCheckbox.isSelected()) {
//                throw new AssertionError("The user accepted the checkbox");
//            }
//            System.out.println("Good the user have not accepted the checkbox");
        }

    @Then("The user should see an error message {string}")
    public void theUserShouldSeeAnErrorMessage(String arg0) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        try {
//            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signup_form\"]/div[11]/div/div[2]/div[1]/span/span")));
//
//            if (!errorMessage.isDisplayed()) {
//                throw new AssertionError("Felmeddelandet för terms and conditions visas inte!");
//            }
//
//            System.out.println(" Felmeddelandet för 'terms and conditions' visas korrekt.");
//        } catch (Exception e) {
//            throw new AssertionError("Felmeddelandet för saknat terms and conditions hittades inte på sidan!");
//        }

    }

    @And("The user fills in all the required fields but enter mismatching passwords")
    public void theUserFillsInAllTheRequiredFieldsButEnterMismatchingPasswords() {
    }

    @But("But enter mismatching passwords")
    public void butEnterMismatchingPasswords() {
    }
}
