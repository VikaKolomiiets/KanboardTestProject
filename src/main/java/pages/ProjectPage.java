package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ProjectPage {
    private SelenideElement title = $(".title");
    private SelenideElement removeElement = $("a[href='/project/27/remove']");
    private SelenideElement closeButton = $("a[href='/project/27/disable']");
    private SelenideElement yesButton = $("#modal-confirm-button");
    private SelenideElement cancelHRef = $x("//a[contains(text(), 'cancel')]");

    public ProjectsPage removeProjectByClickYesButton(){
        this.removeElement.shouldBe(Condition.visible).click();
        this.yesButton.shouldBe(Condition.visible).click();
        return new ProjectsPage();
    }
    public ProjectPage cancelRemovingProjectAction(){
        this.removeElement.shouldBe(Condition.visible).click();
        this.cancelHRef.shouldBe(Condition.visible).click();
        return this;
    }


}
