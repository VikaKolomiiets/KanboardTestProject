package pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
@Getter
public class TaskPage {
    private SelenideElement title = $(".title");
    public String getTaskNameOnProjectPage(){
        return $("#task-summary h2").getText();
    }
}
