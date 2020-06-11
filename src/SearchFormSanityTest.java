import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class SearchFormSanityTest {

    WebDriver driver;
    ExtentReports extentHtmlReport;
    ExtentTest extentTest;

    @BeforeTest
    public  void setup(){
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        extentHtmlReport = new ExtentReports();
//        extentHtmlReport.
//        extentHtmlReport = new ExtentReports("/Users/vibhavagarwal/Documents/reports/TestResults.html", true);
        System.setProperty("webdriver.chrome.driver", "/Users/vibhavagarwal/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    public void testLabels() throws InterruptedException {
        String frstNmLbl = "First Name :";
        String lstNmLbl = "Last Name :";
        String idLbl = "Id :";
        System.out.println(driver.findElements(By.cssSelector("label")).listIterator().next().getText());

        ListIterator<WebElement> liItr = driver.findElements(By.cssSelector("label")).listIterator();
        Assert.assertEquals(liItr.next().getText(), frstNmLbl, "First Name Label sets correctly");
        Assert.assertEquals(liItr.next().getText(), lstNmLbl, "Last Name Label sets correctly");
        Assert.assertEquals(liItr.next().getText(), idLbl, "Id Label sets correctly");

    }

    @Test
    public void testButtons() throws InterruptedException {
        String addBtn = "Add";
        String delBtn = "Delete";
        System.out.println(driver.findElements(By.cssSelector("button")).listIterator().next().getText());

        ListIterator<WebElement> liItr = driver.findElements(By.cssSelector("button")).listIterator();
        Assert.assertEquals(liItr.next().getText(), addBtn, "Add button sets correctly");
        Assert.assertEquals(liItr.next().getText(), delBtn, "Delete button sets correctly");
    }

    @Test
    public void testColumnNames() throws InterruptedException {
        String frstColName = "First Name";
        String lstColName = "LastName";
        String idColName = "Id";

        ListIterator<WebElement> liEle = driver.findElements(By.className("ag-header-cell-text")).listIterator();
        Assert.assertEquals(liEle.next().getText(), frstColName, "First Name Column");
        Assert.assertEquals(liEle.next().getText(), lstColName, "Last Name Column");
        Assert.assertEquals(liEle.next().getText(), idColName, "Id Column");
    }

    @Test
    public void testAdd() throws InterruptedException {
        String firstName = "Abcde";
        String lastName = "Abc";
        String id = "111";
        driver.findElement(By.id("firstname")).sendKeys(firstName);//.click().then(() =>
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("id")).sendKeys(id);

        Thread.sleep(1000);

        driver.findElement(By.id("add_btn")).click();

        Thread.sleep(1000);

        ListIterator<WebElement> li1 = driver.findElements(By.cssSelector("div[row-id=\"0\"] span.ag-cell-value")).listIterator();
        Assert.assertEquals(li1.next().getText(), firstName, "First name is " +firstName);

        ListIterator<WebElement> li = driver.findElements(By.cssSelector("div[row-id=\"0\"] div.ag-cell-value")).listIterator();
        Assert.assertEquals(li.next().getText(), lastName);
        Assert.assertEquals(li.next().getText(), id);

    }

    @Test
    public void testDelete() throws InterruptedException {
        String firstName = "Abcde";
        String lastName = "Abc";
        String id = "111";
        driver.findElement(By.id("firstname")).sendKeys(firstName);//.click().then(() =>
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("id")).sendKeys(id);

        Thread.sleep(1000);

        driver.findElement(By.id("add_btn")).click();

        Thread.sleep(1000);

        ListIterator<WebElement> li1 = driver.findElements(By.cssSelector("div[row-id=\"0\"] span.ag-cell-value")).listIterator();
        Assert.assertEquals(li1.next().getText(), firstName);

        ListIterator<WebElement> li = driver.findElements(By.cssSelector("div[row-id=\"0\"] div.ag-cell-value")).listIterator();
        Assert.assertEquals(li.next().getText(), lastName);
        Assert.assertEquals(li.next().getText(), id);

        WebElement firstRow = driver.findElements(By.cssSelector("div[row-id=\"0\"] div.ag-cell-value")).listIterator().next();
        firstRow.click();

        Thread.sleep(1000);

        driver.findElement(By.id("del_btn")).click();

        ListIterator<WebElement> liAfterDel1 = driver.findElements(By.cssSelector("div[row-id=\"0\"] span.ag-cell-value")).listIterator();
        Assert.assertNotEquals(liAfterDel1.next().getText(), firstName);
        ListIterator<WebElement> liAfterDel = driver.findElements(By.cssSelector("div[row-id=\"0\"] div.ag-cell-value")).listIterator();
        Assert.assertNotEquals(liAfterDel.next().getText(), lastName);
        Assert.assertNotEquals(liAfterDel.next().getText(), id);

    }

    @Test
    public void testFilter() throws InterruptedException {
        String searchStr = "aa10";
        driver.findElement(By.id("filter-text-box")).sendKeys(searchStr);
        Thread.sleep(1000);

        ListIterator<WebElement> liAfterFilter = driver.findElements(By.cssSelector("div[row-index=\"0\"] span.ag-cell-value")).listIterator();
        Assert.assertTrue(liAfterFilter.next().getText().contains(searchStr));
    }

    @Test
    public void testFirstNameValidation() throws InterruptedException {
        String firstName = "Abcd";
        String lastName = "Abc";
        String id = "111";
        driver.findElement(By.id("firstname")).sendKeys(firstName);//.click().then(() =>
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("id")).sendKeys(id);

        Thread.sleep(1000);

        driver.findElement(By.id("add_btn")).click();

        Thread.sleep(1000);

//        ListIterator<WebElement> li1 = driver.findElements(By.cssSelector("div[row-id=\"0\"] span.ag-cell-value")).listIterator();
//        Assert.assertNotEquals(li1.next().getText(), firstName);

//        ListIterator<WebElement> li = driver.findElements(By.cssSelector("div[row-id=\"0\"] div.ag-cell-value")).listIterator();
//        Assert.assertNotEquals(li.next().getText(), lastName);
//        Assert.assertNotEquals(li.next().getText(), id);

        String xpath = "//*[contains(text(), 'Min chars required is 5')]";

        Assert.assertFalse(driver.findElements(By.xpath(xpath)).isEmpty());
    }

    @Test
    public void testSorting() throws InterruptedException {
        ListIterator<WebElement> li = driver.findElements(By.cssSelector(".ag-header-cell-text")).listIterator();
//        System.out.println(li.next().getText());
        WebElement ele = li.next();
        ele.click();

        ListIterator<WebElement> li1 = driver.findElements(By.cssSelector("div[row-index=\"0\"] span.ag-cell-value")).listIterator();
        ListIterator<WebElement> li2 = driver.findElements(By.cssSelector("div[row-index=\"1\"] span.ag-cell-value")).listIterator();

        int t = (li1.next().getText()).compareTo(li2.next().getText());
        Assert.assertEquals(t<0, true, "Sorted ascending");

        ele.click();

        ListIterator<WebElement> li3 = driver.findElements(By.cssSelector("div[row-index=\"0\"] span.ag-cell-value")).listIterator();
        ListIterator<WebElement> li4 = driver.findElements(By.cssSelector("div[row-index=\"1\"] span.ag-cell-value")).listIterator();

        Assert.assertEquals((li3.next().getText()).compareTo(li4.next().getText()), 1, "Sorted ascending");
    }

    @AfterTest
    public void quitSetUp(){
        driver.quit();
    }
}
