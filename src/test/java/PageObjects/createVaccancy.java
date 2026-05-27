package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class createVaccancy extends basePage {

    public createVaccancy(WebDriver driver) {
        super(driver);
    }

    // Private Locators (Encapsulation)

    @FindBy(xpath = "//span[normalize-space()='Recruitment']")
    private WebElement recrut_menu;

    @FindBy(xpath = "//a[normalize-space()='Vacancies']")
    private WebElement vacancy;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement add_btn;

    @FindBy(xpath = "//h6[normalize-space()='Add Vacancy']")
    private WebElement page_text;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    private WebElement vaccancy_name;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    private WebElement job_title_dd;

    // Improved dropdown locator (dynamic text)
    @FindBy(xpath = "//div[@role='option']")
    private WebElement dropdown_value;

    @FindBy(xpath = "//textarea[@placeholder='Type description here']")
    private WebElement descriptionArea;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement HM;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
    private WebElement NoOFPos;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement save_vacancy;

    // Reusable Wait
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // ================= METHODS =================

    public void recru_menu() {

        logger.info("Navigating to Recruitment → Vacancies");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(recrut_menu)).click();
            wait.until(ExpectedConditions.visibilityOf(vacancy)).click();
            wait.until(ExpectedConditions.elementToBeClickable(add_btn)).click();

            logger.info("Add Vacancy page opened successfully");

        } catch (Exception e) {
            logger.error("Navigation failed: " + e.getMessage());
            throw e;
        }
    }

    public void verifyPage() {

        logger.info("Verifying Add Vacancy page");

        try {
            boolean text_visible = wait.until(
                    ExpectedConditions.visibilityOf(page_text)).isDisplayed();

            String res_page = page_text.getText();
            logger.info("Page title: " + res_page);

            Assert.assertTrue(text_visible, "Add vacancy page is not open");
            logger.info("Page verified successfully");

        } catch (Exception e) {
            logger.error("Page verification failed: " + e.getMessage());
            throw e;
        }
    }

    public void input_details() {

        logger.info("Entering vacancy details");

        try {
            vaccancy_name.sendKeys("Test Automation Engineer II");
            logger.info("Vacancy name entered");

            job_title_dd.click();
            wait.until(ExpectedConditions.visibilityOf(dropdown_value)).click();
            logger.info("Dropdown value selected");

            descriptionArea.sendKeys("Automation Test Engineer role with Selenium and Java");
            logger.info("Description entered");

            NoOFPos.sendKeys("10");
            logger.info("Number of positions entered");

        } catch (Exception e) {
            logger.error("Failed to enter vacancy details: " + e.getMessage());
            throw e;
        }
    }

    public void submit() {

        logger.info("Submitting vacancy");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(save_vacancy)).click();
            logger.info("Vacancy created successfully");

        } catch (Exception e) {
            logger.error("Failed to create vacancy: " + e.getMessage());
            throw e;
        }
    }
}