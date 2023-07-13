package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement pageTitle = $(".title-container");

    public String getTitleContainerName(){

        return pageTitle.shouldBe(Condition.visible).getText();
    }
}
