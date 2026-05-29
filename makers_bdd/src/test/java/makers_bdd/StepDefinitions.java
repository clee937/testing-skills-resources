package makers_bdd;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    private final WebDriver driver = new FirefoxDriver();

    @Given("I am on the Makers FAQ page")
    public void I_visit_faq_page() {
        driver.get("https://faq.makers.tech/en/knowledge");
    }

    @When("I search for {string}")
    public void search_for(String query) throws InterruptedException {
        WebElement mainSearch = driver.findElement(By.id("hs_kb-search-input-module-input"));
        mainSearch.click();
        mainSearch.sendKeys(query);
        mainSearch.submit();
        Thread.sleep(3000); // We should really use a dynamic wait!
    }

    @Then("the results page should display results for this term")
    public void the_results_page_should_display_results_for_this_term() {
        List<WebElement> noResults = driver.findElements(By.className("hs-search__no-results"));
        assertTrue(noResults.isEmpty(), "The 'no results found' message appeared unexpectedly.");
    }

    @Then("the results body should say no results were found for {string}")
    public void checkNoResultsFoundMessage(String searchString) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchResultHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hs-search__no-results")));
        assertTrue(searchResultHeader.getText().contains("no results for \"" + searchString + "\""));
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