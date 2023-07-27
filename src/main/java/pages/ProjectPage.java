package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class ProjectPage {
    private SelenideElement title = $(".title");
    private SelenideElement yesButton = $("#modal-confirm-button");
    private SelenideElement cancelHRef = $x("//a[contains(text(), 'cancel')]");
    private SelenideElement configureActionMenu = $x("//a[contains(@title, 'Configure this project')]");
    private List<SelenideElement> actionMenuElements = $$(".js-modal-elarg");
    private SelenideElement taskNameInput = $("#form-title");
    private SelenideElement saveButton = $("button[type='submit']");
    private List<SelenideElement> createElements = $$x("//a[contains(@href, '/task/create')]");
    private List<SelenideElement> tableCells = $$("tbody tr td");
    private List<SelenideElement> activities = $$(".activity-title");


    public SelenideElement getRemoveElementByProjectNumber(String projectNumber) {
        String selector = "a[href='/project/" + projectNumber + "/remove']";
        return $(selector);
    }

    @Step("Given Project click remove and submit it by clicking yes button")
    public ProjectsPage removeProject(String projectNumber) {
        this.getRemoveElementByProjectNumber(projectNumber)
                .shouldBe(Condition.visible)
                .doubleClick();
        this.yesButton.shouldBe(Condition.visible).doubleClick();
        return new ProjectsPage();
    }

    public ProjectPage cancelOfRemovingProject(String projectNumber) {
        this.getRemoveElementByProjectNumber(projectNumber)
                .shouldBe(Condition.visible)
                .doubleClick();
        this.cancelHRef.shouldBe(Condition.visible).click();
        return this;
    }

    public ProjectPage clickOnCreateNewTaskInActionMenu() {
        this.configureActionMenu.shouldBe(Condition.visible).click();
        SelenideElement element = this.createElements.get(1);
        element.shouldBe(Condition.visible).click();
        return this;
    }

    public ProjectPage setNameInCreateNewTaskForm(String name) {
        this.taskNameInput.shouldBe(Condition.visible).sendKeys(name);
        return this;
    }

    public ProjectPage clickSubmitButtonInCreateTaskForm() {
        this.saveButton.shouldBe(Condition.visible).doubleClick();
        return this;
    }

    public ProjectPage createNewTask(String name) {
        clickOnCreateNewTaskInActionMenu()
                .setNameInCreateNewTaskForm(name)
                .clickSubmitButtonInCreateTaskForm();
        return this;
    }

    public boolean isContainTextInTableBody(String word) {
        String text = this.tableCells.get(3).shouldBe(Condition.visible).getText();
        return text.contains(word);
    }

}
