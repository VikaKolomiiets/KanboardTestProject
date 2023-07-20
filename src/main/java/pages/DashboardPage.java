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
    private SelenideElement projectCreateRef = $("[class=page-header] a[href='/project/create']");
    private SelenideElement requestWindowTitle = $x("//h2[contains(text(), 'project')]");
    private SelenideElement inputNameFormProject = $("#form-name");
    private SelenideElement inputIdentifierFormProject = $("#form-identifier");
    private SelenideElement inputTaskLimitFormProject = $("#form-task_limit");
    private SelenideElement checkBoxFormProject = $x("//*[contains(@name, 'swimlane')]");
    private SelenideElement buttonSubmitFormProject  = $("//button[@type='submit']");
    private SelenideElement cancelRefFormProject = $x("//a[text()='cancel']");

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
    public DashboardPage fillInNewProjectForm(String projectName, String identifier, Integer taskLimit){
        inputNameFormProject.sendKeys(projectName);
        inputIdentifierFormProject.sendKeys(identifier);
        inputTaskLimitFormProject.sendKeys(taskLimit.toString());
        checkBoxFormProject.shouldBe(Condition.visible).click();
        return this;
    }
    public DashboardPage openNewProjectRequestForm(){
        projectCreateRef.shouldBe(Condition.visible).click();
        requestWindowTitle.shouldBe(Condition.exactValue("New project"));
        return this;
    }
    public void clickSaveButtonNewProjectForm(){
        buttonSubmitFormProject.shouldBe(Condition.visible).click();
    }
    public void clickCancelInNewProjectForm(){
        cancelRefFormProject.shouldBe(Condition.visible).click();
    }

}
