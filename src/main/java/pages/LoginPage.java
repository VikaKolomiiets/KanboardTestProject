package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.DashboardPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
public class LoginPage {

    private SelenideElement userNameInput = $("#form-username");
    private SelenideElement passwordInput = $("#form-password");
    private SelenideElement signInButton = $x("//button[@type='submit']");

    @Step("Go to ElementsPage by click on the Sing In Button")
    public DashboardPage clickOnSubmitButton(){
        signInButton.shouldBe(Condition.visible).click();
        return new DashboardPage();
    }
    @Step("Input user name")
    public LoginPage setUserNameInput(String userName){
        userNameInput.shouldBe(Condition.visible).sendKeys(userName);
        return this;
    }
    @Step("Input password")
    public LoginPage setPasswordInput(String password){
        passwordInput.shouldBe(Condition.visible).sendKeys(password);
        return this;
    }
}
