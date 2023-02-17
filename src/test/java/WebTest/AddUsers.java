package WebTest;

import ExtentReport.Reporting;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;


public class AddUsers {

    private WebDriver driver;
    ExtentReports reports;
    public Reporting repo = new Reporting();

    @BeforeClass
    public void setUp() {

        // Set up the Chrome driver

        System.setProperty("webdriver.chrome.driver", "src/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static void takeScreenshot(WebDriver driver, ExtentTest test) throws IOException {
        String fileName = "screenshot_" + System.currentTimeMillis() + ".png";
        String directory = System.getProperty("user.dir") + "/screenshots/";
        new File(directory).mkdirs();
        String path = directory + fileName;
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(path));
        test.addScreenCaptureFromPath(path);
    }
    @BeforeTest
    public void init(){
        reports = repo.initializeExtentReports("reports/report.html");
    }

    // Add the first user and validate that it was added successfully
    @Test (priority = 1)
    public void AddUser1() throws InterruptedException, IOException {

        //Create Extent Reports

        ExtentTest test= reports.createTest("way2automation");
        test.assignAuthor("Mulaudzi Thompho Evens");

        // Navigating to the website

        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");

        // Validating the user list table
        WebElement userListTable = driver.findElement(By.cssSelector("table.table.table-striped"));
        wait.until(ExpectedConditions.visibilityOf(userListTable));
        System.out.println("User List Table Displayed");
        Thread.sleep(300);


        // Clicking on Add user button
        WebElement addUserButton = driver.findElement(By.cssSelector("button.btn.btn-link.pull-right"));
        addUserButton.click();
        Thread.sleep(300);

        // Adding first user
        WebElement usernameInput = driver.findElement(By.cssSelector("input[name='FirstName']"));
        String username1 = "FNAme1";
        usernameInput.sendKeys(username1);
        Thread.sleep(300);

        WebElement lastnameInput = driver.findElement(By.cssSelector("input[name='LastName']"));
        lastnameInput.sendKeys("LName1");
        Thread.sleep(300);

        WebElement usernamessInput = driver.findElement(By.cssSelector("input[name='UserName']"));
        usernamessInput.sendKeys("admin@mail.com");
        Thread.sleep(300);

        WebElement passwordInput = driver.findElement(By.cssSelector("input[name='Password']"));
        passwordInput.sendKeys("pass1");
        Thread.sleep(300);

        WebElement customerField = driver.findElement(By.cssSelector("input[name='optionsRadios'][value='15']"));
        customerField.sendKeys("Company AAA");
        customerField.click();

        WebElement roleDropdown = driver.findElement(By.cssSelector("select[name='RoleId']"));
        roleDropdown.click();
        Thread.sleep(300);
        WebElement roleOption = driver.findElement(By.cssSelector("option[value='2']"));
        roleOption.click();
        Thread.sleep(300);

        driver.findElement(By.name("Email")).sendKeys("admin@mail.com");
        Thread.sleep(300);

        driver.findElement(By.name("Mobilephone")).sendKeys("082555");
        Thread.sleep(300);

        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        Thread.sleep(300);

        // Validate that the user1 is added to the User List Table

        WebElement newUser = driver.findElement(By.xpath("//td[contains(text(),'" + username1 + "')]"));
        if (newUser.isDisplayed()) {
            System.out.println("User " + username1 + " added successfully.");
            takeScreenshot(driver, test);
            test.pass("User " + username1 + " added successfully.");
        } else {
            System.out.println("User " + username1 + " was not added.");
            takeScreenshot(driver, test);
            test.fail("User " + username1 + " was not added.");
        }

    }

    @BeforeMethod
    public void navigateToPage() {
        driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");
    }

    // Add the Second user and validate that it was added successfully
    @Test (priority = 2)
        public void AddUser2() throws InterruptedException, IOException {
        ExtentTest test= reports.createTest("way2automation");
        test.assignAuthor("Mulaudzi Thompho Evens");


        // Clicking on Add user button
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement addUserButton = driver.findElement(By.cssSelector("button.btn.btn-link.pull-right"));
        wait.until(ExpectedConditions.visibilityOf(addUserButton));
        addUserButton.click();
        Thread.sleep(300);

        // Adding second user
        WebElement usernameInput = driver.findElement(By.cssSelector("input[name='FirstName']"));
        String username2 = "FName2";
        usernameInput.sendKeys(username2);
        Thread.sleep(300);




        WebElement lastnameInput = driver.findElement(By.cssSelector("input[name='LastName']"));
        lastnameInput.sendKeys("LName2");
        Thread.sleep(300);

        WebElement emailInput = driver.findElement(By.cssSelector("input[name='UserName']"));
        emailInput.sendKeys("customer@mail.com");
        Thread.sleep(300);

        WebElement passwordInput = driver.findElement(By.cssSelector("input[name='Password']"));
        passwordInput.sendKeys("pass2");
        Thread.sleep(300);
        WebElement customerField = driver.findElement(By.cssSelector("input[name='optionsRadios'][value='16']"));
        customerField.sendKeys("Company BBB");
        customerField.click();

        WebElement roleDropdown = driver.findElement(By.cssSelector("select[name='RoleId']"));
        roleDropdown.click();
        Thread.sleep(300);
        WebElement roleOption = driver.findElement(By.cssSelector("option[value='1']"));
        roleOption.click();
        Thread.sleep(300);

        driver.findElement(By.name("Email")).sendKeys("customer@mail.com");
        Thread.sleep(300);

        driver.findElement(By.name("Mobilephone")).sendKeys("083444");
        Thread.sleep(300);

        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        Thread.sleep(3000);

        // Validate that the user2 is added to the User List Table
        WebElement newUser = driver.findElement(By.xpath("//td[contains(text(),'" + username2 + "')]"));
        if (newUser.isDisplayed()) {
            System.out.println("User " + username2 + " added successfully.");
            takeScreenshot(driver, test);
            test.pass("User " + username2 + " added successfully.");
        } else {
            System.out.println("User " + username2 + " was not added.");
            takeScreenshot(driver, test);
            test.fail("User " + username2 + " was not added.");
        }

    }

    // Close the browser after all the tests are finished

    @AfterTest
    public void aftertest() throws InterruptedException {

        Thread.sleep(300);
        reports.flush();
    }
    @AfterClass
    public void tearDown() {
        driver.quit();

    }
}
