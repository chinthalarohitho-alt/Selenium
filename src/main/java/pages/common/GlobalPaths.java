package pages.common;

/**
 * Locator helper methods - only commonly used patterns.
 * For simple locators like "#id" or ".class", use them directly.
 */
public class GlobalPaths {

    // ==================== CSS HELPERS ====================

    /**
     * CSS by ID
     * Example: byId("email") → "#email"
     */
    public String byId(String id) {
        return "#" + id;
    }

    /**
     * CSS by class
     * Example: byClass("btn-primary") → ".btn-primary"
     */
    public String byClass(String className) {
        return "." + className;
    }

    /**
     * CSS with attribute
     * Example: cssAttr("input", "type", "email") → "input[type='email']"
     */
    public String cssAttr(String tag, String attribute, String value) {
        return String.format("%s[%s='%s']", tag, attribute, value);
    }

    /**
     * CSS with partial attribute match (contains)
     * Example: cssAttrContains("div", "class", "error") → "div[class*='error']"
     */
    public String cssAttrContains(String tag, String attribute, String value) {
        return String.format("%s[%s*='%s']", tag, attribute, value);
    }

    /**
     * CSS with data attribute
     * Example: dataAttr("button", "testid", "submit") → "button[data-testid='submit']"
     */
    public String dataAttr(String tag, String dataAttrName, String value) {
        return String.format("%s[data-%s='%s']", tag, dataAttrName, value);
    }

    /**
     * CSS descendant selector (space = any level)
     * Example: descendant(".form", "input") → ".form input"
     */
    public String descendant(String parent, String child) {
        return parent + " " + child;
    }

    /**
     * CSS direct child selector (> = immediate child)
     * Example: directChild(".form", "input") → ".form > input"
     */
    public String directChild(String parent, String child) {
        return parent + " > " + child;
    }

    // ==================== XPATH HELPERS ====================

    /**
     * XPath with exact text match
     * Example: exactText("button", "Submit") → "//button[text()='Submit']"
     */
    public String exactText(String tag, String text) {
        return String.format("//%s[text()='%s']", tag, text);
    }

    /**
     * XPath with partial text (contains)
     * Example: containsText("p", "Error") → "//p[contains(text(),'Error')]"
     */
    public String containsText(String tag, String text) {
        return String.format("//%s[contains(text(),'%s')]", tag, text);
    }

    /**
     * XPath with attribute
     * Example: xpathAttr("input", "name", "email") → "//input[@name='email']"
     */
    public String xpathAttr(String tag, String attribute, String value) {
        return String.format("//%s[@%s='%s']", tag, attribute, value);
    }

    /**
     * XPath with partial attribute match
     * Example: xpathAttrContains("div", "class", "error") → "//div[contains(@class,'error')]"
     */
    public String xpathAttrContains(String tag, String attribute, String value) {
        return String.format("//%s[contains(@%s,'%s')]", tag, attribute, value);
    }

    /**
     * XPath with text AND attribute
     * Example: textAndAttr("button", "Submit", "type", "submit")
     *          → "//button[text()='Submit' and @type='submit']"
     */
    public String textAndAttr(String tag, String text, String attribute, String value) {
        return String.format("//%s[text()='%s' and @%s='%s']", tag, text, attribute, value);
    }

    /**
     * XPath parent (go up one level)
     * Example: parent("//button[@id='submit']") → "//button[@id='submit']/.."
     */
    public String parent(String childXpath) {
        return childXpath + "/..";
    }

    /**
     * XPath following sibling (next element at same level)
     * Example: followingSibling("//label[@for='email']", "input")
     *          → "//label[@for='email']/following-sibling::input"
     */
    public String followingSibling(String elementXpath, String siblingTag) {
        return elementXpath + "/following-sibling::" + siblingTag;
    }

    /**
     * XPath ancestor (go up to specific parent)
     * Example: ancestor("//input[@id='email']", "form")
     *          → "//input[@id='email']/ancestor::form"
     */
    public String ancestor(String elementXpath, String ancestorTag) {
        return elementXpath + "/ancestor::" + ancestorTag;
    }

    // ==================== PLAYWRIGHT TEXT SELECTORS ====================

    /**
     * Playwright has-text selector
     * Example: hasText("button", "Sign In") → "button:has-text('Sign In')"
     */
    public String hasText(String tag, String text) {
        return String.format("%s:has-text('%s')", tag, text);
    }

    /**
     * Playwright text selector (partial match)
     * Example: text("Welcome") → "text=Welcome"
     */
    public String text(String text) {
        return "text=" + text;
    }

    // ==================== USEFUL COMBINED SELECTORS ====================

    /**
     * Element with text inside container
     * Example: withinContainer("#form", "button", "Submit")
     *          → "#form button:has-text('Submit')"
     */
    public String withinContainer(String container, String element, String text) {
        return String.format("%s %s:has-text('%s')", container, element, text);
    }

    /**
     * Input by placeholder
     * Example: placeholder("Enter email") → "[placeholder='Enter email']"
     */
    public String placeholder(String text) {
        return String.format("[placeholder='%s']", text);
    }

    /**
     * Input by name attribute
     * Example: inputName("username") → "input[name='username']"
     */
    public String inputName(String name) {
        return String.format("input[name='%s']", name);
    }

    /**
     * Input by type
     * Example: inputType("email") → "input[type='email']"
     */
    public String inputType(String type) {
        return String.format("input[type='%s']", type);
    }

    /**
     * Link by href
     * Example: linkHref("/dashboard") → "a[href='/dashboard']"
     */
    public String linkHref(String href) {
        return String.format("a[href='%s']", href);
    }

    /**
     * Link by partial href (contains)
     * Example: linkHrefContains("dashboard") → "a[href*='dashboard']"
     */
    public String linkHrefContains(String hrefPart) {
        return String.format("a[href*='%s']", hrefPart);
    }

    // ==================== FORM HELPERS ====================

    /**
     * Checkbox by name
     * Example: checkbox("remember") → "input[type='checkbox'][name='remember']"
     */
    public String checkbox(String name) {
        return String.format("input[type='checkbox'][name='%s']", name);
    }

    /**
     * Radio button by name and value
     * Example: radio("gender", "male") → "input[type='radio'][name='gender'][value='male']"
     */
    public String radio(String name, String value) {
        return String.format("input[type='radio'][name='%s'][value='%s']", name, value);
    }

    /**
     * Select dropdown by name
     * Example: select("country") → "select[name='country']"
     */
    public String select(String name) {
        return String.format("select[name='%s']", name);
    }

    /**
     * Textarea by name
     * Example: textarea("comments") → "textarea[name='comments']"
     */
    public String textarea(String name) {
        return String.format("textarea[name='%s']", name);
    }

    /**
     * Label by for attribute
     * Example: labelFor("email") → "label[for='email']"
     */
    public String labelFor(String forAttribute) {
        return String.format("label[for='%s']", forAttribute);
    }

    // ==================== TABLE HELPERS ====================

    /**
     * Table row by index (1-based)
     * Example: tableRow("#users-table", 2) → "#users-table tr:nth-child(2)"
     */
    public String tableRow(String tableSelector, int rowIndex) {
        return String.format("%s tr:nth-child(%d)", tableSelector, rowIndex);
    }

    /**
     * Table cell by row and column (1-based)
     * Example: tableCell("#users-table", 2, 3) → "#users-table tr:nth-child(2) td:nth-child(3)"
     */
    public String tableCell(String tableSelector, int row, int col) {
        return String.format("%s tr:nth-child(%d) td:nth-child(%d)", tableSelector, row, col);
    }

    /**
     * Table row containing text
     * Example: tableRowWithText("#users-table", "John") → "#users-table tr:has-text('John')"
     */
    public String tableRowWithText(String tableSelector, String text) {
        return String.format("%s tr:has-text('%s')", tableSelector, text);
    }
}
