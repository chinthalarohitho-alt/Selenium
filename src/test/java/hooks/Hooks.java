package hooks;

import Initialization.FrameWorkInitialization;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;

public class Hooks {
   private static final Logger logger = (Logger) LoggerFactory.getLogger(Hooks.class);
   FrameWorkInitialization FM = new FrameWorkInitialization();

   @Before
   public void setup() throws IOException {
      FM.setUp();
   }


   @After
   public void teardown(){
      FM.tearDown();
   }


   @AfterAll
   public static void printReportPaths() {
      String htmlPath = Paths.get("target/cucumber-reports.html").toAbsolutePath().toString();
      System.out.println("\n" + "=".repeat(120));
      System.out.println("🎉 CUCUMBER REPORTS GENERATED SUCCESSFULLY!");
      System.out.println("📊 HTML Report: file://" + htmlPath);
      System.out.println("=".repeat(120));

      try {
         String[] cmd = {"allure", "generate", "allure-results", "-o", "allure-report", "--clean"};
         Process process = Runtime.getRuntime().exec(cmd);
         process.waitFor();

         String reportPath = Paths.get("allure-report/index.html").toAbsolutePath().toString();

         System.out.println( "=".repeat(120));
         System.out.println("🎉 ALLURE REPORT GENERATED!");
         System.out.println("Open Allure: file://" + reportPath);
         System.out.println("You can run: allure serve allure-results");
         System.out.println("=".repeat(120));
      } catch (Exception e) {
         System.out.println("Failed to generate Allure report: " + e.getMessage());
      }
   }

}
