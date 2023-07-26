package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProjectListingPage {
    private SelenideElement alertAfterRemoveTask = $(".alert");

    public String getTextFromAlert(){
        return this.alertAfterRemoveTask.getText();
    }

}
