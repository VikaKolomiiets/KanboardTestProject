import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
public class LoginPage {
    //private final String url = "https://localhost/login";
    private SelenideElement userNameInput = $("#form-username");
    private SelenideElement passwordInput = $("#form-password");
    private SelenideElement signInButton = $x("//button[@type='submit']");

    public DashboardPage
}
