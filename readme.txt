
 locator types:
        driver.findElement(By.id("login_field")) ;
        driver.findElement (By.name("password")) ;
        driver.findElement(By.className("header-logo")) ;
        driver.findElement(By.linkText("Forgot password?")) ;
        driver.findElement(By.partialLinkText("Create an"));
        driver.findElement(By.tagName('h1')) ;
        driver.findElement(By.xpath("//label[contains(text), 'Username or email address' )]"));
        WebElement txt = driver.findElement(By.cssSelector("input[name=' commit' ]")) ;

//      dropdown:
          Select s = new Select(txt);
          List<WebElement> e = s.getOptions();
          for(WebElement element: e)
             {
                 System.out.println(element.getText());
              }

        driver.switchTo().frame("frm1");
        driver.switchTo().defaultContent();

//      ss:
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File(" ");
        FileUtils.copyFile(sourceFile,destFile);

        /*  MoveToElement or MouseHover,
            Click,
            Double Click,
            Right click or Context Click
         */
        Actions actions = new Actions(driver);
        actions.click().perform();
        actions.doubleClick().perform();
        actions.contextClick().perform();
        actions.moveToElement("locator").perform();
