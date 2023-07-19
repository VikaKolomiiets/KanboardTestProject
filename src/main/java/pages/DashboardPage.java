package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class DashboardPage {
    private SelenideElement pageTitle = $(".title-container");

    private SelenideElement title = $(".title");
    private SelenideElement avatarDropDown = $x("//div[@class='avatar avatar-20 avatar-inline']");
    private SelenideElement logoutRef = $x("//a[@href='/logout']");


    public String getTitleContainerName(){
        return pageTitle.shouldBe(Condition.visible).getText();
    }

    public LoginPage logOutFromDashboardPage(){
        avatarDropDown.shouldBe(Condition.visible).click();
        logoutRef.shouldBe(Condition.visible).click();
        return new LoginPage();
    }
}
