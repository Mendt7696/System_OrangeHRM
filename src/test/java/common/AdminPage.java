package common;

import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage extends BaseTest {
    WebDriver driver;
    WebDriverWait wait;

    //constructor
    public AdminPage(WebDriver driver) {
        super();
        this.driver = driver;
        long explicitTimeout = Long.parseLong(ConfigReader.getProperty("explicitTimeout"));
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(explicitTimeout));
    }

    //locator
    By adminButton = By.xpath("//*[text()='Admin']/ancestor::a");
    By textVerify = By.xpath("//*[text()='System Users']/ancestor::div[@class='oxd-table-filter-header-title']");
    By userName = By.xpath("//*[text()='Username']/following::input[@class='oxd-input oxd-input--active']");
    By searchButton = By.xpath("//*[@type ='submit']");
    By textSearchResult = By.xpath("//*[@class='oxd-table-cell oxd-padding-cell']/*[text()='Admin']");
    By userRoles = By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')][1]");
    By roleItem = By.xpath("//*[text()='Admin']/parent::div[@role='option']");
    By roleName = By.xpath("(//*[@class='oxd-table-card']/descendant::div[text()='Admin'])[2]");

    //Màn hình Add User
    By addButton = By.xpath("//*[@class='orangehrm-header-container']/descendant::*[text()=' Add ']");
    By newRole = By.xpath("(//*[@class='oxd-select-text oxd-select-text--active'])[1]");
    By newitemRole = By.xpath("(//*[@role='listbox']/descendant::*[@role='option'])[2]");
    By employeeName = By.xpath("//*[@placeholder='Type for hints...']");
    By status = By.xpath("(//*[@class='oxd-select-text oxd-select-text--active'])[2]");
    By statusofItem = By.xpath("(//*[@role='listbox']/descendant::*[@role='option'])[3]");
    By newUserame = By.xpath("(//*[@class='oxd-input-group oxd-input-field-bottom-space']/descendant::*[@class='oxd-input oxd-input--active'])[1]");
    // Tìm input có label là Password
    By newPassword = By.xpath("//label[text()='Password']/following::input[1]");

    // Tìm input có label là Confirm Password
    By confirmPassword = By.xpath("//label[text()='Confirm Password']/following::input[1]");

    By saveButton = By.xpath("//*[@class='oxd-form-actions']/descendant::*[@type='submit']");
    By toastSuccessful = By.xpath("//*[text()='Successfully Saved']");
    By itemEmployeeName = By.xpath("(//*[@role='listbox']/descendant::*[@role='option'])[1]");
    By deleteButton = By.xpath("(//*[@class='oxd-table-cell-actions']/descendant::*[@class='oxd-icon bi-trash'])[2]");
    By deleteToastSuccessful = By.xpath("//*[@class='oxd-toast-content oxd-toast-content--success']/descendant::*[text()='Successfully Deleted']");
    By yesDeleteButton = By.xpath("//*[@class='orangehrm-modal-footer']/descendant::*[text()=' Yes, Delete ']");
    By checkboxRecord1 = By.xpath("(//*[@class='oxd-icon bi-check oxd-checkbox-input-icon'])[2]");
    By checkboxRecord2 = By.xpath("(//*[@class='oxd-icon bi-check oxd-checkbox-input-icon'])[3]");
    By deleteSelected = By.xpath("//*[text()=' Delete Selected ']");


    // Admin Screen displayed
    public boolean adminIsDisplayed() {
        this.wait.until(ExpectedConditions.elementToBeClickable(adminButton)).click();
        try {
            WebElement text = this.driver.findElement(textVerify);
            System.out.println(text.getText());
            return text.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("System Users not found", e);
        }
    }

    //Search Item by Username into System Users
    public boolean Check_SearchFunction_ByUserName(String UserName) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(userName)).sendKeys(UserName);

        this.wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(textSearchResult));
            WebElement textResult = this.driver.findElement(textSearchResult);
            System.out.println(textResult.getText());
            return textResult.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("Khong tim thay element ", e);
        }
    }

    //Search Item by User Roles into System Users
    public boolean Check_SearchFunction_ByUserRoles() {
        this.wait.until(ExpectedConditions.elementToBeClickable(userRoles)).click();

        this.wait.until(ExpectedConditions.elementToBeClickable(roleItem)).click();

        this.wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(roleName));
            WebElement admin = this.driver.findElement(roleName);
            System.out.println(admin.getText());
            return admin.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("Khong tim thay Admin trong User Roles ", e);
        }
    }

    //Add User
    public boolean addUser(String Employee_Name, String NewUsername, String ConfirmPassWord, String NewPassWord) {
        //Click Add button
        this.wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        //Select User Role
        this.wait.until(ExpectedConditions.elementToBeClickable(newRole)).click();
        this.wait.until(ExpectedConditions.elementToBeClickable(newitemRole)).click();
        //Enter Employee Name
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName)).sendKeys(Employee_Name);
        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(itemEmployeeName, 1));
        WebElement firstName = this.driver.findElement(itemEmployeeName);

        // Scroll và click item bằng JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", firstName);
        js.executeScript("arguments[0].click();", firstName);

        //// Dùng Actions để moveToElement rồi click
        //Actions actions = new Actions(driver);
        //actions.moveToElement(firstItem).click().perform();
        //Select Status
        this.wait.until(ExpectedConditions.elementToBeClickable(status)).click();
        this.wait.until(ExpectedConditions.elementToBeClickable(statusofItem)).click();
        //Enter Username
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(newUserame)).sendKeys(NewUsername);
        //Enter Confirm Password
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword)).sendKeys(ConfirmPassWord);
        //Enter  New Password
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(newPassword)).sendKeys(NewPassWord);
        //Click button Save
        this.wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        //Toast Display
        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(toastSuccessful));
            WebElement actualToast = this.driver.findElement(toastSuccessful);
            System.out.println(actualToast.getText());
            return actualToast.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("Toast no display", e);
        }
    }

    public boolean RecordDelete() {
        this.wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();

        //Switch to Alert Confirm => Do ther HTML custome HTML thanh popup(<div>.. ) nên dùng Webelement thường
        /*Alert alertDelete = this.driver.switchTo().alert();
        System.out.println(alertDelete.getText());
        alertDelete.accept();*/
        this.wait.until(ExpectedConditions.elementToBeClickable(yesDeleteButton)).click();
        try {
            WebElement deleteSuccessful = this.wait.until(ExpectedConditions.visibilityOfElementLocated(deleteToastSuccessful));
            return deleteSuccessful.isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException("Toast Successful Delete no display", e);
        }
    }

    public String multiDeleteRecords() {
        WebElement checkBox1 = this.wait.until(ExpectedConditions.elementToBeClickable(checkboxRecord1));
        WebElement checkBox2 = this.wait.until(ExpectedConditions.elementToBeClickable(checkboxRecord2));

        scrollToElement(checkBox1);
        if (!checkBox1.isSelected()) checkBox1.click();

        scrollToElement(checkBox2);
        if (!checkBox2.isSelected()) checkBox2.click();

        this.wait.until(ExpectedConditions.elementToBeClickable(deleteSelected)).click();

        try {
            WebElement deleteSuccessful = this.wait.until(ExpectedConditions.visibilityOfElementLocated(deleteToastSuccessful));
            return deleteSuccessful.getText();
        } catch (Exception e) {
            throw new RuntimeException("Toast Successful Delete no display", e);
        }
    }

}
