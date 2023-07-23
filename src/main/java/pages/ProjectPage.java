package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class ProjectPage {
    private SelenideElement title = $(".title");
    private SelenideElement yesButton = $("#modal-confirm-button");
    private SelenideElement cancelHRef = $x("//a[contains(text(), 'cancel')]");
    private SelenideElement configureActionMenu = $(".action-menu");
    private List<SelenideElement> actionMenuElements = $$(".js-modal-elarg");
    private SelenideElement taskNameInput = $("#form-title");
    private SelenideElement submitButton = $("button[type='submit']");

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
    public ProjectPage clickOnCreateNewTaskInActionMenu(){
        this.configureActionMenu.shouldBe(Condition.visible).doubleClick();
        SelenideElement add = null;
        for (SelenideElement element : this.actionMenuElements) {
            if(element.getText().contains("Add a new")){
                add = element;
            }
        }
        add.doubleClick();
        return this;
    }
    public ProjectPage setNameInCreateNewTaskForm(String name){
        this.taskNameInput.shouldBe(Condition.visible).sendKeys(name);
        return this;
    }
    public ProjectPage clickSubmitButtonInCreateNewTaskForm(){
        this.submitButton.shouldBe(Condition.visible).doubleClick();
        return this;
    }

    public ProjectPage createNewTask(String name){
        clickOnCreateNewTaskInActionMenu()
                .setNameInCreateNewTaskForm(name)
                .clickSubmitButtonInCreateNewTaskForm();
        return this;
    }


}
