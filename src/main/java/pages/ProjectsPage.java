package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class ProjectsPage {
    private SelenideElement title = $(".title");
    private SelenideElement alert = $x("//div[contains(@class, 'alert-success')]");
    private List<SelenideElement> numberIcons = $$("a[href='#']");
    private SelenideElement configure =
            $x("//ul[@class='dropdown-submenu-open']//a[contains(text(),'Configure')]");
    private SelenideElement activity =
            $x("//ul[@class='dropdown-submenu-open']//a[contains(text(),'Activity')]");
    private SelenideElement listing =
            $x("//ul[@class='dropdown-submenu-open']//a[contains(text(),'Listing')]");
    private SelenideElement projectsCount = $(".table-list-header-count");
    private SelenideElement alertNoProject = $x("//p[@class='alert']");

    public ProjectsPage openProjectsPage() {
        return this;
    }

    @Step("Chose given project on page and go on it")
    public ProjectsPage openDropDownInChosenProject(String projectId) {
        numberIcons.stream()
                .filter(e -> e != null)
                .filter(e -> e.getText().contains(projectId))
                .findFirst()
                .get()
                .click();
        return this;
    }

    @Step("Open Project page by click on Configure")
    public ProjectPage clickConfigureToOpenProjectPage() {
        configure.shouldBe(Condition.visible).doubleClick();
        return new ProjectPage();
    }

    public ListProjectPage clickListingToOpenListProjectPage() {
        listing.shouldBe(Condition.visible).doubleClick();
        return new ListProjectPage();
    }


    @Step("Get the count of Projects, which is shown in title of Project Page ")
    public Integer getProjectsCount() {
        String elementText = projectsCount.shouldBe(Condition.visible).getText();
        String number = Arrays.stream(elementText.split("[^0-9]+")).reduce((x, y) -> x.concat(y)).get();
        return Integer.valueOf(number);
    }

    @Step("Get text from Alert about No Project")
    public String getTextFromAlertNoProject() {

        return this.alertNoProject.shouldBe(Condition.visible).getText();
    }


}
