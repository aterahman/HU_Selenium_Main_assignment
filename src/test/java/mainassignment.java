import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class mainassignment {
    static WebDriver driver;
    public static int balance;
    public static int transaction[] = new int[100];
    public static int i = 0;
    static TakesScreenshot tsc;

    public static void main(String[] args) throws InterruptedException, NoSuchElementException {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32 (1)\\chromedriver.exe");
        driver = new ChromeDriver();
        balance = 0;
        tsc = (TakesScreenshot)driver;
        openurl();
        createacc();
        openacc();
        login();
        deposit();
        withdraw();
        transactions();
        driverquit();
    }

    //method to take screenshot
    public static void screenshot()
    {
        File scr = tsc.getScreenshotAs(OutputType.FILE);
    }

    @BeforeTest
    //Method to open url
    public static void openurl() throws InterruptedException {
        //opens url
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        //maximizes the window size
        driver.manage().window().maximize();

    }

    //method to find element using xpath
    public static WebElement findelement(String xp) throws NoSuchElementException {
        //Find web element using xpath provided
        WebElement e = driver.findElement(By.xpath(xp));
        return e;
    }

    //method to add a new customer on the site
    @Test
    public static void createacc() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        //clicking on Bank Manager Login Button
        driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div[2]/button")).click();


        //clicking on Add Customer tab
        findelement("/html/body/div/div/div[2]/div/div[1]/button[1]").click();

        //entering first name
        findelement("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input").sendKeys("Bruce");

        //entering last name
        findelement("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input").sendKeys("Wayne");

        //entering postal code
        findelement("/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input").sendKeys("2606");

        //clicking on the add account button
        findelement("/html/body/div/div/div[2]/div/div[2]/div/div/form/button").click();

        //interacting with alert box
        driver.switchTo().alert().accept();


    }

    //method to open a new account for added customer
    @Test
    public static void openacc() throws InterruptedException {
        //going to open account tab
        findelement("/html/body/div/div/div[2]/div/div[1]/button[2]").click();

        //selecting customer name from the dropdown list
        Select selectname = new Select(findelement("//*[@id=\"userSelect\"]"));
        selectname.selectByVisibleText("Bruce Wayne");

        //selecting currency from the dropdown list
        Select selectcurrency = new Select(findelement("//*[@id=\"currency\"]"));
        selectcurrency.selectByVisibleText("Dollar");

        //clicking on the process button
        findelement("/html/body/div/div/div[2]/div/div[2]/div/div/form/button").click();


        //noting down the account number
        String alerttext = driver.switchTo().alert().getText();
        String accnumber = "";

        for (int i = 0; i < alerttext.length(); i++) {
            char ch = alerttext.charAt(i);
            if (Character.isDigit(ch))
                accnumber = accnumber + ch;
        }

        //accepting the alert box message after noting down number
        driver.switchTo().alert().accept();
        Assert.assertTrue(true);
    }

    //logging in with the newly created account
    @Test
    public static void login() throws InterruptedException {
        //going back to the homepage
        findelement("/html/body/div/div/div[1]/button[1]").click();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        //clicking the customer login button
        findelement("/html/body/div/div/div[2]/div/div[1]/div[1]/button").click();

        //selecting customer from the dropdown list
        Select selectname = new Select(findelement("//*[@id=\"userSelect\"]"));
        selectname.selectByVisibleText("Bruce Wayne");

        //click on Login button
        findelement("/html/body/div/div/div[2]/div/form/button").click();

        Assert.assertTrue(true);
    }

    //method to deposit money into account
    @Test
    public static void deposit() throws InterruptedException {
        String balanceonpage = "";

        //clicking on deposit tab
        findelement("/html/body/div/div/div[2]/div/div[3]/button[2]").click();

        //entering value in the Amount to Be Deposited textbox
        findelement("/html/body/div/div/div[2]/div/div[4]/div/form/div/input").sendKeys("1000");
        balance = balance + 1000;

        //click on deposit buttom
        findelement("/html/body/div/div/div[2]/div/div[4]/div/form/button").click();

        //recording the transaction
        transaction [i] = 1000 ;
        i++;

        Thread.sleep(500);

        //checking if balance has been updated accordingly
        balanceonpage = findelement("/html/body/div/div/div[2]/div/div[2]/strong[2]").getText();

        try {
            Assert.assertEquals(Integer.toString(balance), (balanceonpage));
            System.out.println("Test Passed");
        }
        catch (Throwable e)
        {
            System.out.println("Test Failed");
        }

        //entering value in the Amount to Be Deposited textbox
        findelement("/html/body/div/div/div[2]/div/div[4]/div/form/div/input").sendKeys("1000");
        balance = balance + 1000;

        //click on deposit buttom
        findelement("/html/body/div/div/div[2]/div/div[4]/div/form/button").click();

        //recording the transaction
        transaction [i] = 1000 ;
        i++;

        //checking if balance has been updated accordingly
        balanceonpage = findelement("/html/body/div/div/div[2]/div/div[2]/strong[2]").getText();

        try {
            Assert.assertEquals(Integer.toString(balance), (balanceonpage));
            System.out.println("Passed");
        }
        catch (Throwable e)
        {
            System.out.println("Test Failed");
        }


    }

    //method to withdraw money from account
    @Test
    public static void withdraw() throws InterruptedException {
        String balanceonpage = "";
        //clicking on withdraw tab
        findelement("/html/body/div/div/div[2]/div/div[3]/button[3]").click();

        Thread.sleep(500);

        //entering amount to withdraw
        findelement("/html/body/div/div/div[2]/div/div[4]/div/form/div/input").sendKeys("500");
        balance = balance - 500;

        //click on withdraw button
        findelement("/html/body/div/div/div[2]/div/div[4]/div/form/button").click();

        //recording the transaction
        transaction [i] = -500 ;
        i++;

        //checking if balance has been updated accordingly
        balanceonpage = findelement("/html/body/div/div/div[2]/div/div[2]/strong[2]").getText();

        try {
            Assert.assertEquals(Integer.toString(balance), balanceonpage);
            System.out.println("Test passed");
        }
        catch (Throwable e)
        {
            System.out.println("test failed");
        }

        Thread.sleep(1000);

        //clickinng on withdraw tab
        findelement("/html/body/div/div/div[2]/div/div[3]/button[3]").click();

        //withdrawing more cash than balance
        findelement("/html/body/div/div/div[2]/div/div[4]/div/form/div/input").sendKeys("10000");

        //click on withdraw button
        findelement("/html/body/div/div/div[2]/div/div[4]/div/form/button").click();

        //getting the error message
        String errormessaage =  findelement("/html/body/div/div/div[2]/div/div[4]/div/span").getText();
        System.out.println(errormessaage);

        Assert.assertTrue(true);

    }


    //method to verify transactions
    @Test
    public static void transactions() {
        int j = 0;
        String type="";
        String amt="";
        //clciking on transactions tab
        findelement("/html/body/div/div/div[2]/div/div[3]/button[1]").click();

        //verifying 1st transaction
        type =findelement("/html/body/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[3]").getText();

        if (type.equals("Credit"))
        {
            amt = findelement("/html/body/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[2]").getText();
            if(Integer.toString(transaction[0]).equals(amt))
                System.out.println("Transaction 1 verified");
        }

        //verifying 2nd transaction
        type =findelement("/html/body/div/div/div[2]/div/div[2]/table/tbody/tr[2]/td[3]").getText();
        if (type.equals("Credit"))
        {
            amt = findelement("/html/body/div/div/div[2]/div/div[2]/table/tbody/tr[2]/td[2]").getText();
            if(Integer.toString(transaction[1]).equals(amt))
                System.out.println("Transaction 2 verified");
        }

        //verifying 3rd transaction
        type =findelement("/html/body/div/div/div[2]/div/div[2]/table/tbody/tr[3]/td[3]").getText();
        if (type.equals("Debit"))
        {
            transaction[2] = -transaction[2];
            amt = findelement("/html/body/div/div/div[2]/div/div[2]/table/tbody/tr[3]/td[2]").getText();
            if(Integer.toString(transaction[2]).equals(amt))
                System.out.println("Transaction 3 verified");
        }

        //clicking on logout button
        findelement("/html/body/div/div/div[1]/button[2]").click();


        Assert.assertTrue(true);
    }


    @AfterTest
    //method to close and quit driver
    public static void driverquit() {
        driver.close();
        driver.quit();

        Assert.assertTrue(true);
    }

}