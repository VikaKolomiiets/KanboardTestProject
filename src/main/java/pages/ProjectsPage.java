package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ProjectsPage {
    private SelenideElement title = $(".title");
    private SelenideElement alert = $x("//div[contains(@class, 'alert-success')]");


}
