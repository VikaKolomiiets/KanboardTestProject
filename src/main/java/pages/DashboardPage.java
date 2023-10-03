package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class DashboardPage {
    private SelenideElement pageTitle = $(".title-container");
    private SelenideElement title = $(".title");
    private SelenideElement avatarDropDown = $(".avatar-letter");
    private SelenideElement projectCreateRef = $("[class=page-header] a[href='/project/create']");
    private SelenideElement requestWindowTitle = $x("//h2[contains(text(), 'project')]");
    private SelenideElement inputNameFormProject = $("#form-name");
    private SelenideElement inputIdentifierFormProject = $("#form-identifier");
    private SelenideElement inputTaskLimitFormProject = $("#form-task_limit");
    private SelenideElement checkBoxFormProject = $x("//*[contains(@name, 'swimlane')]");
    private SelenideElement buttonSubmitFormProject = $x("//button[@type='submit']");
    private SelenideElement cancelRefFormProject = $x("//a[text()='cancel']");
    private SelenideElement errorProjectForm = $(".form-errors");
    private SelenideElement moveTaskInDropDown = $x("//div[@id='dropdown']//a[contains(text(), 'Move to project')] ");
    private SelenideElement closeTaskInDropDown = $x("//div[@id='dropdown']//a[contains(text(), 'Close this task')] ");
    private SelenideElement removeTaskInDropDown = $x("//div[@id='dropdown']//a[contains(text(), 'Remove')] ");
    private SelenideElement buttonYes = $("#modal-confirm-button");
    private SelenideElement listing = $x("//div[@id='dropdown']//a[contains(text(),'Listing')]");

    @Step("Find Project Or Task By Given Number On DashboardPage")
    public SelenideElement getByNumberOnPage(String number) {
        String selector = "//*[contains(text(), '#" + number + "')]";
        return $x(selector);
    }

    public SelenideElement getInputByProjectName(String name) {
        String selector = "//input[contains(@placeholder, '" + name + "')]";
        return $x(selector);
    }

    public SelenideElement getLogoutRef() {
        List<SelenideElement> logoutList = $$("a[href='/logout']");
        return logoutList.get(1);
    }
    @Step("Get title name from Container")
    public String getTitleContainerName() {
        return pageTitle.shouldBe(Condition.visible).getText();
    }
    @Step("Get title name from Remove Task Form")
    public String getTitleRemoveTaskForm() {
        return $x("//*[contains(text(), 'Remove a task')]").getText();
    }
    @Step("Get title name from Close Task Form")
    public String getTitleCloseTaskForm() {
        return $x("//*[contains(text(), 'Close a task')]").getText();
    }
    @Step("Get title name from Move Task Form")
    public String getTitleMoveTaskForm() {
        return $x("//h2[contains(text(), 'Move the')]").getText();
    }
    @Step("Log out from Dashboard page")
    public LoginPage logOutFromDashboardPage() {
        avatarDropDown.shouldBe(Condition.visible).click();
        getLogoutRef().shouldBe(Condition.visible).click();
        return new LoginPage();
    }
    @Step("Fill in form to create new Project by given data")
    public DashboardPage fillNewProjectForm(String projectName, String identifier, Integer taskLimit) {
        inputNameFormProject.sendKeys(projectName);
        inputIdentifierFormProject.sendKeys(identifier);
        inputTaskLimitFormProject.sendKeys(taskLimit.toString());
        checkBoxFormProject.shouldBe(Condition.visible).click();
        return this;
    }
    @Step("Open form to create new Project")
    public DashboardPage openNewProjectForm() {
        projectCreateRef.shouldBe(Condition.visible).click();
        return this;
    }
    @Step("Submit creation of new Project by clicking on save button")
    public void clickSaveButtonNewProjectForm() {
        this.buttonSubmitFormProject.should(Condition.visible).click();
    }
    @Step("Submit creation of new Task by clicking on save button")
    public TaskPage clickSaveButtonAndOpenTaskPage() {
        this.buttonSubmitFormProject.should(Condition.visible).click();
        return new TaskPage();
    }
    @Step("Submit by clicking on yes button")
    public void clickYesButton() {
        this.getButtonYes().doubleClick();
    }

    @Step("Cancel creation of new Project in the form")
    public void clickCancelInNewProjectForm() {
        this.cancelRefFormProject.shouldBe(Condition.visible).click();
    }

    @Step("Get Message from error alert")
    public String getErrorProjectFormText() {
        return this.errorProjectForm.shouldBe(Condition.visible).getText();
    }

    @Step("Move To ProjectPage By Click On Its Number")
    public ProjectsPage clickOnProjectNumber(String projectNumber) {
        this.getByNumberOnPage(projectNumber)
                .shouldBe(Condition.visible)
                .doubleClick();
        return new ProjectsPage();
    }

    @Step("By Given Project Number Open DropDown And Choose 'Listing'")
    public ProjectListingPage openProjectListing(String projectNumber) {
        this.getByNumberOnPage(projectNumber).click();
        this.listing.shouldBe(Condition.visible).doubleClick();
        return new ProjectListingPage();
    }

    @Step("By Given Task Number Open DropDown And Choose 'Remove'")
    public DashboardPage getRemoveTaskForm(String taskNumber) {
        this.getByNumberOnPage(taskNumber).shouldBe(Condition.visible).click();
        this.removeTaskInDropDown.shouldBe(Condition.visible).doubleClick();
        return this;
    }

    @Step("By Given Task Number Open DropDown And Choose 'Close this task'")
    public DashboardPage getCloseTaskForm(String taskNumber) {
        this.getByNumberOnPage(taskNumber).shouldBe(Condition.visible).click();
        this.closeTaskInDropDown.shouldBe(Condition.visible).doubleClick();
        return this;
    }

    @Step("By Given Task Number Open DropDown And Choose 'Move to project'")
    public DashboardPage getMoveTaskForm(String taskNumber) {
        this.getByNumberOnPage(taskNumber).shouldBe(Condition.visible).click();
        this.moveTaskInDropDown.shouldBe(Condition.visible).doubleClick();
        return this;
    }

    @Step("Choose Input Cell And Sent it Given Project Name")
    public DashboardPage chooseAnotherProjectForTest(String name) {
        SelenideElement input = this.getInputByProjectName(name);
        input.shouldBe(Condition.visible).click();
        input.sendKeys(name);
        return this;
    }

}
