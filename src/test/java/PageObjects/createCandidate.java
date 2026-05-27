package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class createCandidate extends basePage {

    public createCandidate(WebDriver driver) {
        super(driver);
    }

    // Private Locators (Encapsulation)

    @FindBy(xpath = "//span[normalize-space()='Recruitment']")
    private WebElement recrut_menu;

    @FindBy(xpath = "//a[normalize-space()='Candidates']")
    private WebElement candidates;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement add_candidate;

    @FindBy(xpath = "//h6[normalize-space()='Add Candidate']")
    private WebElement cand_text;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement F_Name;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    private WebElement Mid_Name;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement L_Name;

    @FindBy(xpath = "//body//input[@type='email']")
    private WebElement email;

    @FindBy(xpath = "(//input[@placeholder='Type here'])[2]")
    private WebElement contact;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement uploadResume;

    @FindBy(xpath = "//textarea[@placeholder='Type here']")
    private WebElement notes;

    @FindBy(xpath = "//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']")
    private WebElement consent_checkBox;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement save;

    // Reusable Wait
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // ================== ACTION METHODS ==================

    public void recru_menu() {

        logger.info("Navigating to Recruitment → Add Candidate");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(recrut_menu)).click();
            wait.until(ExpectedConditions.elementToBeClickable(candidates)).click();
            wait.until(ExpectedConditions.elementToBeClickable(add_candidate)).click();

            logger.info("Add Candidate button clicked successfully");

        } catch (Exception e) {
            logger.error("Failed to navigate to Add Candidate page: " + e.getMessage());
            throw e;
        }
    }

    public void verify_candidate_page() {

        logger.info("Verifying Add Candidate page");

        try {
            boolean result = wait.until(
                    ExpectedConditions.visibilityOf(cand_text)).isDisplayed();

            String res_page = cand_text.getText();
            logger.info("Page title: " + res_page);

            Assert.assertTrue(result, "Add candidate page is not open");
            logger.info("Add Candidate page verified successfully");

        } catch (Exception e) {
            logger.error("Page verification failed: " + e.getMessage());
            throw e;
        }
    }

    public void cand_data_input(String f_name, String M_name, String L_name,
                                String Email, String phn, String note_des) {

        logger.info("Entering candidate details");

        try {
            F_Name.sendKeys(f_name);
            Mid_Name.sendKeys(M_name);
            L_Name.sendKeys(L_name);
            logger.info("Candidate name entered: " + f_name + " " + M_name + " " + L_name);

            email.sendKeys(Email);
            contact.sendKeys(phn);
            logger.info("Email and phone entered");

            //  File Upload
            String filepath = "C:\\Users\\sriva\\Desktop\\PIC\\test.pdf";
            uploadResume.sendKeys(filepath);
            logger.info("Resume uploaded: " + filepath);

            wait.until(ExpectedConditions.elementToBeClickable(notes));
            notes.sendKeys(note_des);
            logger.info("Notes entered");

            // Optional checkbox
            if (!consent_checkBox.isSelected()) {
                consent_checkBox.click();
                logger.info("Consent checkbox selected");
            }

        } catch (Exception e) {
            logger.error("Failed to enter candidate details: " + e.getMessage());
            throw e;
        }
    }

    public void submit() {

        logger.info("Submitting candidate form");

        try {
            save.click();
            logger.info("Candidate form submitted successfully");

        } catch (Exception e) {
            logger.error("Failed to submit candidate form: " + e.getMessage());
            throw e;
        }
    }
}