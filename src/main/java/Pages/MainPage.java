package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    SelenideElement authButton = $x("//*[@class='SiteNav-menuListLinkText' and text()='Авторизация']");
    SelenideElement exitButton = $x("//*[@class=\"SiteNav-pageLink\" and text()=\"Выход\"]");
    ElementsCollection topMenuButtons = $$x("//span[@class='SiteNav-menuListLinkText']");

    public void clickAUthButton(){
        authButton.click();
    }

    public String getCharacterName(){
        sleep(10 * 1000);
        String text = topMenuButtons.get(topMenuButtons.size() - 1).getText();
        return text;
    }

    public void exit(){
        sleep(5 * 1000);
        topMenuButtons.get(topMenuButtons.size() - 1).click();
        exitButton.click();
    }
}
