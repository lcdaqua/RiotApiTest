package Configuration;

import Pages.AuthPage;
import Pages.MainPage;

public class ConfigurationForTests {
    public static MainPage mainPage;
    public static AuthPage authPage;

    public void init(){
        mainPage = new MainPage();
        authPage = new AuthPage();
    }
}
