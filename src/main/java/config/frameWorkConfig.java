package config;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class frameWorkConfig {
    private static final Logger logger = LoggerFactory.getLogger(frameWorkConfig.class);
    private static volatile frameWorkConfig instance;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    // Private constructor
    private frameWorkConfig() {
        this.playwright = Playwright.create();
        logger.debug("Playwright instance created");
    }

    // Thread-safe singleton
    public static frameWorkConfig getInstance() {
        if (instance == null) {
            synchronized (frameWorkConfig.class) {
                if (instance == null) {
                    instance = new frameWorkConfig();
                }
            }
        }
        return instance;
    }

    // ==================== GETTERS AND SETTERS ====================

    public Playwright getPlaywright() {
        // Recreate Playwright if closed
        if (playwright == null) {
            playwright = Playwright.create();
            logger.info("Playwright instance recreated");
        }
        return playwright;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setContext(BrowserContext context) {
        this.context = context;
    }

    public BrowserContext getContext() {
        return context;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

    // ==================== CLEANUP METHODS ====================

    /**
     * METHOD: Cleanup for EACH scenario
     * Closes Page, Context, Browser but keeps Playwright alive
     * Call this in @After hook (after each scenario)
     */
    public void cleanupScenario() {
        logger.debug("Starting scenario cleanup...");

        // Close Page
        try {
            if (page != null && !page.isClosed()) {
                page.close();
                logger.debug("Page closed");
            }
        } catch (Exception e) {
            logger.warn("Error closing page: {}", e.getMessage());
        }

        // Close Context
        try {
            if (context != null) {
                context.close();
                logger.debug("Context closed");
            }
        } catch (Exception e) {
            logger.warn("Error closing context: {}", e.getMessage());
        }

        // Close Browser
        try {
            if (browser != null && browser.isConnected()) {
                browser.close();
                logger.debug("Browser closed");
            }
        } catch (Exception e) {
            logger.warn("Error closing browser: {}", e.getMessage());
        }

        // Reset references (but keep Playwright)
        page = null;
        context = null;
        browser = null;

        logger.debug("Scenario cleanup completed");
    }

    /**
     * METHOD: Complete cleanup
     * Closes everything including Playwright
     * Call this ONLY in @AfterAll hook (after all tests)
     */
    public void cleanupAll() {
        logger.info("Starting complete cleanup...");

        // First cleanup scenario resources
        cleanupScenario();

        // Then close Playwright
        try {
            if (playwright != null) {
                playwright.close();
                playwright = null;
                logger.info("Playwright instance closed");
            }
        } catch (Exception e) {
            logger.warn("Error closing Playwright: {}", e.getMessage());
        }

        logger.info("Complete cleanup finished");
    }
}
