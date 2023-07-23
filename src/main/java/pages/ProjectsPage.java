package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
    private SelenideElement listing =
            $x("//ul[@class='dropdown-submenu-open']//a[contains(text(),'Listing')]");
    private SelenideElement projectsCount = $(".table-list-header-count");
    private SelenideElement alertNoProject = $x("//p[@class='alert']");

    public ProjectsPage openProjectsPage() {
        return this;
    }

    public ProjectsPage openDropDownInChosenProject(String projectId) {
        numberIcons.stream()
                .filter(e -> e != null)
                .filter(e -> e.getText().contains(projectId))
                .findFirst()
                .get()
                .click();
        return this;
    }

    public ProjectPage clickConfigureToOpenProjectPage() {
        configure.shouldBe(Condition.visible).doubleClick();
        return new ProjectPage();
    }
    public ListProjectPage clickListingToOpenListProjectPage() {
        listing.shouldBe(Condition.visible).doubleClick();
        return new ListProjectPage();
    }

    public boolean isContainProjectNumber1(String projectId) {

        List<String> pageTexts = numberIcons.stream()
                .filter(e -> e != null && e.text() != null)
                .map(e -> e.getText())
                .collect(Collectors.toList());
        if (pageTexts == null) {
            return false;
        } else {
            return pageTexts.stream().anyMatch(s -> s.contains(projectId));
        }
    }
    public Integer getProjectsCount(){
        String elementText = projectsCount.shouldBe(Condition.visible).getText();
        String number = Arrays.stream(elementText.split("[^0-9]+")).reduce((x,y) -> x.concat(y)).get();
        return Integer.valueOf(number);
    }
    public String getTextFromAlertNoProject(){
        return this.alertNoProject.shouldBe(Condition.visible).getText();
    }



}
