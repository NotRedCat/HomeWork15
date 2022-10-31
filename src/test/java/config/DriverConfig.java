package config;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${remote}.properties"

})

public interface DriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://www.dns-shop.ru/")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("FIREFOX")
    String getBrowser();

    @Key("browserVersion")
    @DefaultValue("100")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1500x1200")
    String getBrowserSize();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String getRemoteURL();

}