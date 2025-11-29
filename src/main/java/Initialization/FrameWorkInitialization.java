package Initialization;

import ch.qos.logback.classic.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class FrameWorkInitialization {
    public static WebDriver driver;
//property files
    Properties prop = new Properties();
    Properties alpha = new Properties();
    Properties beta = new Properties();
    Properties cloud = new Properties();
    private Properties currentEnvProperties;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FrameWorkInitialization.class);

    public void loadProperties() throws IOException {
        try{
            FileInputStream fis = new FileInputStream("src/main/config/BrowserConfig.properties");
            FileInputStream alphaFile = new FileInputStream("src/main/resources/alpha.properties");
            FileInputStream betaFile = new FileInputStream("src/main/resources/beta.properties");
            FileInputStream cloudFile = new FileInputStream("src/main/resources/cloud.properties");
            prop.load(fis);
            alpha.load(alphaFile);
            beta.load(betaFile);
            cloud.load(cloudFile);
        } catch (IOException e) {
            throw new IOException ("Failed to load properties file", e);
        }
    }

    public WebDriver getBrowser() {
        String browserName = prop.getProperty("BrowserName");
        if (browserName == null) {
            throw new IllegalArgumentException("browserName not specified in config file");
        }

        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("Headless_status", "false"));
        String locale = prop.getProperty("Locale");
        String argValue = prop.getProperty("argValue");
        String windowSize = prop.getProperty("window_size");


        if (browserName.equalsIgnoreCase("chromium") || browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (!windowSize.isEmpty()) {
                options.addArguments("--window-size="+windowSize);
            }
            if (isHeadless) {
                options.addArguments("--headless=new");
            }
            if (locale != null && !locale.isEmpty()) {
                options.addArguments("--lang=" + locale);
            }

            if (argValue != null && !argValue.isEmpty()) {
                options.addArguments(argValue);
            }
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (!windowSize.isEmpty()) {
                options.addArguments("--window-size="+windowSize);
            }
            if (isHeadless) {
                options.addArguments("--headless=new");
            }
            if (locale != null && !locale.isEmpty()) {
                options.addArguments("--lang=" + locale);
            }

            if (argValue != null && !argValue.isEmpty()) {
                options.addArguments(argValue);
            }
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("webkit") || browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if (!windowSize.isEmpty()) {
                options.addArguments("--window-size="+windowSize);
            }
            if (isHeadless) {
                options.addArguments("--headless=new");
            }
            if (locale != null && !locale.isEmpty()) {
                options.addArguments("--lang=" + locale);
            }

            if (argValue != null && !argValue.isEmpty()) {
                options.addArguments(argValue);
            }
            driver = new EdgeDriver(options);

        } else if (browserName.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();  // Safari does not officially support headless mode, so no option here.
        } else {
            throw new IllegalArgumentException("Unknown browser: " + browserName);
        }
        return driver;
    }

    private Properties env(){
        // Get environment from system property, default to alpha if not set
        String env = System.getProperty("env", "alpha").toLowerCase();

        switch (env) {
            case "alpha":
                currentEnvProperties = alpha;
                break;
            case "beta":
                currentEnvProperties = beta;
                break;
            case "cloud":
                currentEnvProperties = cloud;
                break;
            default:
                logger.warn("Unknown environment '{}', defaulting to alpha", env);
                currentEnvProperties = alpha;
        }
        return currentEnvProperties;
    }

    private String getUrl() {
            return currentEnvProperties.getProperty("Url");
    }

    public void setUp() throws IOException {
        loadProperties();
        currentEnvProperties = env();
        driver = getBrowser();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(getUrl());
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
