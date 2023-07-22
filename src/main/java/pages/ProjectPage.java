package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ProjectPage {
    private SelenideElement title = $(".title");
    private SelenideElement yesButton = $("#modal-confirm-button");
    private SelenideElement cancelHRef = $x("//a[contains(text(), 'cancel')]");

    public SelenideElement getRemoveElementByProjectNumber(String projectNumber){
        String selector = "a[href='/project/" + projectNumber + "/remove']";
        return $(selector);
    }

    public SelenideElement getCloseElementByProjectNumber(String projectNumber){
        String selector = "a[href='/project/" + projectNumber + "/disable']";
        return $(selector);
    }

    public ProjectsPage removeProject(String projectNumber){
        this.getRemoveElementByProjectNumber(projectNumber)
                .shouldBe(Condition.visible)
                .doubleClick();
        this.yesButton.shouldBe(Condition.visible).doubleClick();
        return new ProjectsPage();
    }
    public ProjectPage cancelOfRemovingProject(String projectNumber){
        this.getRemoveElementByProjectNumber(projectNumber)
                .shouldBe(Condition.visible)
                .doubleClick();
        this.cancelHRef.shouldBe(Condition.visible).click();
        return this;
    }

}
