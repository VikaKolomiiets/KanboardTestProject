package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

@Getter
public class ProjectsPage {
    private SelenideElement title = $(".title");
    private SelenideElement alert = $x("//div[contains(@class, 'alert-success')]");
    private List<SelenideElement> numberIcons = $$("a[href='#']");
    private SelenideElement configure = $x("//ul[@class='dropdown-submenu-open']//a[contains(text(),'Configure')]");


    public ProjectsPage openProjectsPage(){
        return this;
    }

    public ProjectsPage openDropDownInChosenProject(String projectId){
        numberIcons.stream()
                .filter(e -> e!=null)
                .filter(e -> e.getText().contains(projectId))
                .findFirst()
                .get()
                .click();
        return this;
    }
    public ProjectPage clickConfigureToOpenProjectPage(){
        configure.shouldBe(Condition.visible).doubleClick();
        return new ProjectPage();
    }

    public boolean isContainProjectNumber(String projectId){
        return numberIcons.stream()
                .filter(e -> e!=null && e.text()!=null)
                .map(e -> e.getText())
                .anyMatch(s -> s.contains(projectId));
    }

}
