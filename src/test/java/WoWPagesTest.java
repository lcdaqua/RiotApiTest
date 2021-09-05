import Configuration.ConfigurationForTests;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class WoWPagesTest extends ConfigurationForTests {
    WebDriver driver;


    @BeforeTest
    public void before() throws MalformedURLException {
        init();
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.driverManagerEnabled = false;
        Configuration.browser = "chrome";
        Configuration.browserVersion = "90";
        Configuration.browserCapabilities = new DesiredCapabilities();
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities.setCapability("enableVideo", false);
        Configuration.browserCapabilities.setCapability("sessionTimeout", "30m");
        Configuration.browserCapabilities.setCapability("env", new ArrayList() {{
            add("LANG=ru_RU.UTF-8");
            add("LANGUAGE=ru:RU");
            add("LC_ALL=ru_RU.UTF-8");
        }});
        Configuration.browserSize = "1920x1080";
        Configuration.startMaximized = true;
        Configuration.fastSetValue = true;
        Configuration.timeout = 40000;
        open("https://worldofwarcraft.com/ru-ru/");

    }

    @Test
    public void authTest() {
        mainPage.clickAUthButton();
        sleep(15 * 1000);
        authPage.login("just.sdeb@gmail.com", "5587907bM!");
    }
}
