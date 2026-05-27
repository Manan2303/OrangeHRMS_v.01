package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class addJob extends basePage {

    public addJob(WebDriver driver) {
        super(driver);
    }

    //Private Locators (Encapsulation)



	@FindBy(xpath = "//span[normalize-space()='Admin']")
    private WebElement admin_menu;

    @FindBy(xpath = "//span[normalize-space()='Job']")
    private WebElement job_opt;

    @FindBy(xpath = "//a[normalize-space()='Job Titles']")
    private WebElement job_opt_title;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement job_add_btn;

    @FindBy(xpath = "//h6[normalize-space()='Add Job Title']")
    private WebElement addjob_page_title;

    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//input")
    private WebElement jobTitle_field;

    @FindBy(xpath = "//textarea[@placeholder='Type description here']")
    private WebElement title_description;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement uploadJobSpecification;

    @FindBy(xpath = "//textarea[@placeholder='Add note']")
    private WebElement note_textArea;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement save_btn;

    // Reusable Wait
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // ================== METHODS ==================

    public void adminMenu() {
        logger.info("Navigating to Admin → Job → Job Titles");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(admin_menu)).click();
            wait.until(ExpectedConditions.elementToBeClickable(job_opt)).click();
            wait.until(ExpectedConditions.elementToBeClickable(job_opt_title)).click();
            wait.until(ExpectedConditions.elementToBeClickable(job_add_btn)).click();

            logger.info("Job Title Add page opened successfully");

        } catch (Exception e) {
            logger.error("Navigation to Job Title page failed: " + e.getMessage());
            throw e;
        }
    }

    public void verifyPage() {
        logger.info("Verifying Add Job Title page");

        try {
            boolean result = wait.until(ExpectedConditions.visibilityOf(addjob_page_title)).isDisplayed();

            String pageTitle = addjob_page_title.getText();
            logger.info("Page title is: " + pageTitle);

            Assert.assertTrue(result, "Wrong page is open");
            logger.info("Page verified successfully");

        } catch (Exception e) {
            logger.error("Page verification failed: " + e.getMessage());
            throw e;
        }
    }

    public void inputDetails(String job_title, String job_des, String note) {

        logger.info("Entering job details");

        try {
            jobTitle_field.sendKeys(job_title);
            logger.info("Job title entered: " + job_title);

            title_description.sendKeys(job_des);
            logger.info("Job description entered");

            // File Upload
            String filepath = "C:\\Users\\sriva\\Desktop\\PIC\\test.pdf";
            uploadJobSpecification.sendKeys(filepath);
            logger.info("File uploaded: " + filepath);

            note_textArea.sendKeys(note);
            logger.info("Note entered");

        } catch (Exception e) {
            logger.error("Failed to enter job details: " + e.getMessage());
            throw e;
        }
    }

    public void submit() {
        logger.info("Clicking on Save button");

        try {
            save_btn.click();
            logger.info("Job submitted successfully");

        } catch (Exception e) {
            logger.error("Failed to submit job: " + e.getMessage());
            throw e;
        }
    }
}