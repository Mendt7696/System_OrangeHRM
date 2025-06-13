package tests;

import common.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("AdminFeature")
public class AdminTest extends BaseTest {
    @Test(description = "Access Admin OK")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Open Admin OK")
    @Story("Admin story")
    public void AccessAdminSuccessful() {
        Assert.assertTrue(this.admin.adminIsDisplayed(), "Khong hien thị man hình Admin");
    }

    @Test(description = "Function Search Valid")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Search Valid")
    @Story("Admin story")
    public void search_Valid_UserName() {
        this.admin.adminIsDisplayed();

        Assert.assertTrue(this.admin.Check_SearchFunction_ByUserName("Admin"), "Function Search active incorrect");
    }

    @Test(description = "Function Search Valid")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Search user roles Valid")
    @Story("Admin story")
    public void search_Valid_UserRoles() {
        this.admin.adminIsDisplayed();
        this.admin.Check_SearchFunction_ByUserRoles();

        Assert.assertTrue(this.admin.Check_SearchFunction_ByUserRoles(), "Search user roles incorrect");
    }

    @Test(description = "Function Add NewUser")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Add NewUser Successful")
    @Story("Admin story")
    public void addNewUser_Successful() {
        this.admin.adminIsDisplayed();
        this.admin.addUser("M", "Hally", "nam1234", "nam1234");
    }

    @Test(description = "Function Delete User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Delete NewUser Successful")
    @Story("Admin story")
    public void check_DeleteUser(){
        this.admin.adminIsDisplayed();
        Assert.assertTrue(this.admin.RecordDelete(), "Delete User error");
    }

    @Test(description = "Function Delete Multi User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Delete Muilti NewUser Successful")
    @Story("Admin story")
    public void deleteMultiUsers(){
        this.admin.adminIsDisplayed();
        String actualResult = this.admin.multiDeleteRecords();
        String expectedResult = "Successfully Deleted";
        Assert.assertEquals(actualResult, expectedResult,"Can not delete multi users");
    }
}


