package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class DashboardPage {
    private SelenideElement pageTitle = $(".title-container");

    private SelenideElement title = $(".title");
    private SelenideElement avatarDropDown = $(".avatar-letter");

    public SelenideElement getLogoutRef() {
        List<SelenideElement> logoutList = $$("a[href='/logout']");
        return logoutList.get(1);
    }



    public String getTitleContainerName(){
        return pageTitle.shouldBe(Condition.visible).getText();
    }

    public LoginPage logOutFromDashboardPage(){
        avatarDropDown.shouldBe(Condition.visible).click();
        getLogoutRef().shouldBe(Condition.visible).click();
        return new LoginPage();
    }
}
