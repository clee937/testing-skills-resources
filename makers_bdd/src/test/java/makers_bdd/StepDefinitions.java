package makers_bdd;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    private final WebDriver driver = new FirefoxDriver();

    @Given("I am on the Makers FAQ page")
    public void I_visit_faq_page() {
        driver.get("https://faq.makers.tech/en/knowledge");
    }

    @When("I search for {string}")
    public void search_for(String query) throws InterruptedException {
        WebElement mainSearch = driver.findElement(By.className("kb-search__bar"));
        WebElement element = mainSearch.findElement(By.name("term"));
        element.sendKeys(query);
        element.submit();
        Thread.sleep(3000); // We should really use a dynamic wait!
    }

    @Then("the results header should mention {string}")
    public void checkTitle(String searchString) {
        WebElement resultHeading = driver.findElement(By.className("kb-search-results__heading"));
        assertTrue(resultHeading.getText().contains(searchString));
    }

    @Then("the results body should say no results were found for {string}")
    public void checkNoResultsFoundMessage(String searchString) {
        WebElement resultBody = driver.findElement(By.className("kb-search-results__listing"));
        assertTrue(resultBody.getText().contains("no search results for \"" + searchString + "\""));
    }

    @After
    public void closeBrowser(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }
        driver.quit();
    }
}