package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.DriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
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
        System.setProperty("properties", "remote");
        DriverConfig config = ConfigFactory.create(DriverConfig.class, System.getProperties());

        if (System.getProperty("remote_url") != null) {
            capabilities.setCapability("browserName", config.getBrowser());
            Configuration.browserSize = config.getBrowserSize();
            Configuration.baseUrl = config.getBaseUrl();
            Configuration.browserVersion = config.getBrowserVersion();
        }
        else {
            System.setProperty("properties", "local");
            DriverConfig configLoc = ConfigFactory.create(DriverConfig.class, System.getProperties());
            capabilities.setCapability("browserName", configLoc.getBrowser());
            Configuration.browserSize = configLoc.getBrowserSize();
            Configuration.baseUrl = configLoc.getBaseUrl();
            Configuration.browserVersion = configLoc.getBrowserVersion();
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


