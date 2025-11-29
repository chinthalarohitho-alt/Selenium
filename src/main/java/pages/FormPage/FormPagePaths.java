package pages.FormPage;

public class FormPagePaths {

    public String containsText(String TagName, String Key) {
        return "//"+TagName+"[contains(text(),'"+Key+"')]";
    }

    public String AtTheRateInvalidMessage = "//p[contains(text(),\"Email must contain '@' symbol\")]";
}
