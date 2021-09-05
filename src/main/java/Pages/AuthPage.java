package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.$$x;

public class AuthPage {
    SelenideElement loginInput = $x("//input[@id='accountName']");
    SelenideElement passInput = $x("//input[@id='password']");
    SelenideElement authButton = $x("//button[contains(text(), 'Авторизоваться')]");

    public void login(String login, String password){
        loginInput.sendKeys(login);
        passInput.sendKeys(password);
        authButton.click();
    }
}