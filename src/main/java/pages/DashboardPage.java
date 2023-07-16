package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
@Getter
public class DashboardPage {
    private SelenideElement pageTitle = $(".title-container");

    private SelenideElement title = $(".title");


    public String getTitleContainerName(){
        return pageTitle.shouldBe(Condition.visible).getText();
    }
}
