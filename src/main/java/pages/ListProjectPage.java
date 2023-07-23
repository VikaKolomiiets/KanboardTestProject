package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ListProjectPage {
    private SelenideElement createSecondTask = $(".table-list-title");


    public void clickOnCreateNewTask(){
        createSecondTask.shouldBe(Condition.visible).doubleClick();
    }

}
