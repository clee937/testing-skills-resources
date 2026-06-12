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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    private final WebDriver driver = new FirefoxDriver();

    private void acceptCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement acceptButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("hs-eu-confirmation-button")
                    )
            );

            acceptButton.click();

        } catch (TimeoutException e) {
            // Cookie banner not displayed — continue test
        }
    }

    @Given("I am on the Makers FAQ page")
    public void I_visit_faq_page() {
        driver.get("https://faq.makers.tech/en/knowledge");
    }

    @Given("I am on the Makers homepage")
    public void I_visit_homepage() {
        driver.get("https://makers.tech/");
        acceptCookiesIfPresent();
    }

    @When("I search for {string}")
    public void search_for(String query) throws InterruptedException {
        WebElement mainSearch = driver.findElement(By.id("hs_kb-search-input-module-input"));
        mainSearch.click();
        mainSearch.sendKeys(query);
        mainSearch.submit();
        Thread.sleep(3000); // We should really use a dynamic wait!
    }

    @When("I click the {string} link")
    public void clickLink(String linkText) {

        String originalWindow = driver.getWindowHandle();
        int originalWindowCount = driver.getWindowHandles().size();

        driver.findElement(By.linkText(linkText)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(driver -> driver.getWindowHandles().size() > originalWindowCount);

            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
        } catch (TimeoutException e) {
            // Link stayed in the same tab — that's fine
        }
    }

    @Then("the results page should display results for this term")
    public void the_results_page_should_display_results_for_this_term() {
        List<WebElement> noResults = driver.findElements(By.className("hs-search__no-results"));
        assertTrue(noResults.isEmpty(), "The 'no results found' message appeared unexpectedly.");
    }


    @Then("there should be {int} results")
    public void there_should_be_results_count_results(int resultsCount) {
        List<WebElement> results =
                driver.findElements(By.cssSelector("#hsresults li"));

        assertEquals(resultsCount, results.size());
    }

    @Then("the term {string} should appear in the URL")
    public void the_term_should_appear_in_the_url(String searchTerm) {
        String currentUrl = driver.getCurrentUrl();

        assertTrue(
                currentUrl != null && currentUrl.contains(searchTerm),
                "Expected URL to contain: " + searchTerm + " but was: " + currentUrl
        );
    }

    @Then("the results body should say no results were found for {string}")
    public void checkNoResultsFoundMessage(String searchString) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchResultHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hs-search__no-results")));
        assertTrue(searchResultHeader.getText().contains("no results for \"" + searchString + "\""));
    }

    @Then("I should be on the {string} page")
    public void iShouldBeOnPage(String expectedPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(expectedPath));

        assertTrue(
                driver.getCurrentUrl().contains(expectedPath),
                "Expected URL to contain: " + expectedPath +
                        " but was: " + driver.getCurrentUrl()
        );
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

// Next steps: Refactor using page object models