package tests;

import common.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

//mvn clean test
//allure serve allure-results tạo report
//mvn clean test chạy các class ko theo thứ tự có thể gây ra lỗi, chạy lệnh ; mvn clean test -Dtest=LoginTest
// Nếu như IDE chạy được dự án hiện tại nhưng Maven không đọc được config dự án hiện tại
//Mà đọc của dự án cũ thì sử dụng lệnh sau : cd D:\Men\OrangeHRM
//mvn clean test -Dtest=LoginTest
@Epic("LoginFeature")
public class LoginTest extends BaseTest {

    @Test(description = "Verify valid login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Login with valid username and password")
    @Story("Valid login story")
    public void LoginSuccessful() {
        //Assert
        Assert.assertTrue(this.login.isDashboardDisplayed(), "Login Fail");
    }
}
