package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class LoginPage {

    private SelenideElement userNameInput = $("#form-username");
    private SelenideElement passwordInput = $("#form-password");
    private SelenideElement signInButton = $x("//button[@type='submit']");
    private SelenideElement pageTitle = $x("//title[contains(text(), 'Login')]");
    private SelenideElement forgotPassword = $("a[href]");
    private SelenideElement passwordReset = $x("//h2[contains(text(), 'Reset')]");

    @Step("Verify page title")
    public boolean isExistOnLoginPage() {
        return pageTitle.exists();
    }

    @Step("Click on submit button")
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

    @Step("Click on link ForgetPassword and check: message is Exist")
    public boolean clickOnForgetPassword() {
        forgotPassword.shouldBe(Condition.visible).click();
        return passwordReset.shouldBe(Condition.visible).exists();
    }

    @Step("Open Login Page")
    public LoginPage openLoginPage() {
        open("");
        return new LoginPage();
    }

    @Step("Fill in userName and Password in Login page to open DashboardPage")
    public DashboardPage openDashboardPage(String userName, String password) {
        this.openLoginPage()
                .setUserNameInput(userName)
                .setPasswordInput(password)
                .openDashBoardPageByClickOnSubmitButton();
        return new DashboardPage();
    }


}
