package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

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
    private SelenideElement closeTaskInDropDown = $x("//div[@id='dropdown']//a[contains(text(), 'Close this task')] ");
    private SelenideElement removeTaskInDropDown = $x("//div[@id='dropdown']//a[contains(text(), 'Remove')] ");
    private SelenideElement buttonYes = $("#modal-confirm-button");
   // private SelenideElement listingProjectDropDown = $x("//div[@class='dropdown'] //a[contains(text(), 'Listing')]");
   private SelenideElement listing =
           $x("//div[@id='dropdown']//a[contains(text(),'Listing')]");

    public SelenideElement getByNumberOnPage(String number) {
        String selector = "//*[contains(text(), '#" + number + "')]";
        return $x(selector);
    }


    public SelenideElement getLogoutRef() {
        List<SelenideElement> logoutList = $$("a[href='/logout']");
        return logoutList.get(1);
    }

    public String getTitleContainerName() {
        return pageTitle.shouldBe(Condition.visible).getText();
    }

    public String getTitleRemoveTaskForm(){
        return $x("//*[contains(text(), 'Remove a task')]").getText();
    }

    public LoginPage logOutFromDashboardPage() {
        avatarDropDown.shouldBe(Condition.visible).click();
        getLogoutRef().shouldBe(Condition.visible).click();
        return new LoginPage();
    }

    public DashboardPage fillNewProjectForm(String projectName, String identifier, Integer taskLimit) {
        inputNameFormProject.sendKeys(projectName);
        inputIdentifierFormProject.sendKeys(identifier);
        inputTaskLimitFormProject.sendKeys(taskLimit.toString());
        checkBoxFormProject.shouldBe(Condition.visible).click();
        return this;
    }

    public DashboardPage openNewProjectForm() {
        projectCreateRef.shouldBe(Condition.visible).click();
        return this;
    }

    public void clickSaveButtonNewProjectForm() {
        buttonSubmitFormProject.should(Condition.visible).click();
    }

    public void clickCancelInNewProjectForm() {
        cancelRefFormProject.shouldBe(Condition.visible).click();
    }

    public String getErrorProjectFormText() {
        return errorProjectForm.shouldBe(Condition.visible).getText();
    }

    public ProjectsPage clickOnProjectNumber(String projectNumber) {
        this.getByNumberOnPage(projectNumber)
                .shouldBe(Condition.visible)
                .doubleClick();
        return new ProjectsPage();
    }

    public DashboardPage getRemoveTaskForm(String number){
        this.getByNumberOnPage(number).shouldBe(Condition.visible).click();
        this.removeTaskInDropDown.shouldBe(Condition.visible).doubleClick();
        return this;
    }

    public ProjectListingPage openProjectListing(String projectNumber){
        this.getByNumberOnPage(projectNumber).click();
        this.listing.shouldBe(Condition.visible).doubleClick();
        return new ProjectListingPage();
    }
}
