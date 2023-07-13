package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginPage {

    private SelenideElement userNameInput = $("#form-username");
    private SelenideElement passwordInput = $("#form-password");
    private SelenideElement signInButton = $x("//button[@type='submit']");
    private SelenideElement pageTitle = $x("//title[contains(text(), 'Login')]");
    private SelenideElement fogotPassword = $("a[href]");
    private SelenideElement passwordReset = $x("//h2[contains(text(), 'Reset')]");

    public boolean isExistOnLoginPage(){
        return pageTitle.exists();
    }

    public LoginPage clickOnSubmitButton() {
        signInButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Input user name")
    public LoginPage setUserNameInput(String userName) {
        userNameInput.shouldBe(Condition.visible).sendKeys(userName);
        return this;
    }

    @Step("Input password")
    public LoginPage setPasswordInput(String password) {
        passwordInput.shouldBe(Condition.visible).sendKeys(password);
        return this;
    }
    @Step("Go to DashboardPage by click on the SingIn Button")
    public DashboardPage openDashBoardPageByClickOnSubmitButton() {
        signInButton.shouldBe(Condition.visible).click();
        return new DashboardPage();
    }

    public boolean clickOnForgetPassword(){
        fogotPassword.shouldBe(Condition.visible).click();
        return passwordReset.shouldBe(Condition.visible).exists();
    }


}
