package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.Map;


public class TestBase {

    @BeforeAll
    static void configure() throws MalformedURLException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://www.dns-shop.ru/";
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        if (System.getProperty("remote_url") != null) {

            capabilities.setCapability("browserName", System.getProperty("browser_name"));
            capabilities.setCapability("browserVersion", System.getProperty("browser_version"));
            Configuration.browserSize = System.getProperty("browser_size");
            Configuration.remote = System.getProperty("remote_url");
        } else {
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("browserVersion", "100.0");
            Configuration.browserSize = "1800x1200";

        }
    }


    @AfterEach
    void addAttachments() {
        Attach.screenshotsAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}


