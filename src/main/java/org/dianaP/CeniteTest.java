package org.dianaP;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CeniteTest {

    protected static WebDriver driver;

        @Before
        public void init() {
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver();
        }

        @Test
        public void testGoogleSearch() throws InterruptedException {

            driver.get("http://www.google.com");
            Thread.sleep(5000);  // Let the user actually see something!
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("podarimiprikazka");
            searchBox.submit();
            Thread.sleep(5000);  // Let the user actually see something!
        }

    @Test
    public void testPodariLoad() throws InterruptedException {

        driver.get("http://www.podarimiprikazka.com/izdania.php");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement cenaRazkaz = driver.findElement(By.xpath("//*[@id=\"v5FC-textbox_\"]/table/tbody/tr[4]/td[2]"));

        assertNotNull(cenaRazkaz);
        assertTrue(cenaRazkaz.getText().contains("1000"));

    }
    @Test
    //tests if sth is available
    public void testPortFolio() throws InterruptedException {
        driver.get("http://www.podarimiprikazka.com/portfolio.php");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement osTrov = driver.findElement(By.xpath("//*[@id=\"v5FC-textbox_\"]/ul[1]/li[1]/a/b"));
        assertNotNull(osTrov);
        assertTrue(osTrov.getText().contains("Островът - това съм аз"));
    }
    @Test
    //tests click on link
    public void testLinkLilith() throws InterruptedException{
    driver.get("http://www.podarimiprikazka.com/index.php");
    Thread.sleep(5000);
    driver.get("http://www.podarimiprikazka.com/index.php");
    WebElement linkLilith = driver.findElement(By.xpath("//*[@id=\"v5FC-textbox_\"]/center[2]/a"));
    linkLilith.click();
        Thread.sleep(10000);
    }

    @Test
    //login
    public void testFormAbv() throws Exception{
    driver.get("https://www.abv.bg/");
    Thread.sleep(5000);
    driver.findElement(By.id("username")).sendKeys("gigu");
    driver.findElement(By.id("password")).sendKeys("gigu2");
    WebElement SignIn = driver.findElement(By.xpath("//*[@id=\"loginBut\"]"));
    SignIn.click();
        Thread.sleep(10000);
        //check element on new page, if exists, you are in
        WebElement zdr = driver.findElement(By.xpath("//*[@id=\"middlePagePanel\"]/div[1]"));

        assertTrue( zdr.getText().contains("Здравейте,"));

}

@Test
//wrong password


public void wrongPass() throws Exception {
   driver.get("https://www.abv.bg/");
    Thread.sleep(5000);
    driver.findElement(By.id("username")).sendKeys("344");
    driver.findElement(By.id("password")).sendKeys("222");
    WebElement SignIn = driver.findElement(By.xpath("//*[@id=\"loginBut\"]"));
    SignIn.click();
    Thread.sleep(10000);
    WebElement greshnaPar = driver.findElement(By.xpath("//*[@id=\"form.errors\"]"));
    assertTrue(greshnaPar.getText().contains("Грешен"));
}
@Test
//registriraj se i proveri svobodno ime
public void registrirajSe() throws Exception{
     driver.get("https://passport.abv.bg/app/profiles/registration");
     Thread.sleep(5000);
     driver.findElement(By.id("regformUsername")).sendKeys("simonasimonafabi");

    WebElement proveriButton = driver.findElement(By.className("abv-button"));

    proveriButton.click();
    Thread.sleep(5000);
    WebElement svobodnoIme = driver.findElement(By.xpath("//*[@id=\"regform\"]/div[1]/div[2]"));
    assertTrue(svobodnoIme.getText().contains("свободно"));

}
@Test
//password a few elements, should exist more
public void passMalkoznaci() throws Exception{
   driver.get("https://passport.abv.bg/app/profiles/registration");
   Thread.sleep(5000);
   driver.findElement(By.id("password")).sendKeys(AbvModels.malkoBukviPass);
    Thread.sleep(5000);
   WebElement Password2 = driver.findElement(By.id("password2"));
 Password2.click();
 Thread.sleep(5000);
 WebElement messageErrorLessSymbols = driver.findElement(By.xpath("//*[@id=\"regform\"]/div[3]/div[2]"));
 assertTrue(messageErrorLessSymbols.getText().contains("8 символа."));
Thread.sleep(5000);
}
@Test
//checks if right message for error on not same pass is given
public void passNotSamePass() throws Exception{

driver.get("https://passport.abv.bg/app/profiles/registration");
Thread.sleep(5000);
driver.findElement(By.id("password")).sendKeys(AbvModels.rightPassword);
Thread.sleep(5000);
driver.findElement(By.id("password2")).sendKeys(AbvModels.wrongPassword);
WebElement mobPhone = driver.findElement(By.id("mobilePhone"));
mobPhone.click();
WebElement MessagepassNotSamePass = driver.findElement(By.xpath("//*[@id=\"regform\"]/div[4]/div[2]"));
assertTrue(MessagepassNotSamePass.getText().contains("Паролата не съвпада"));
Thread.sleep(5000);
}
@Test
//pass coincides
public void SamePass() throws Exception{
driver.get("https://passport.abv.bg/app/profiles/registration");
driver.findElement(By.id("password")).sendKeys(AbvModels.rightPassword);
Thread.sleep(5000);
driver.findElement(By.id("password2")).sendKeys(AbvModels.rightPassword);
Thread.sleep(5000);
WebElement mobPhone = driver.findElement(By.id("mobilePhone"));
mobPhone.click();
Thread.sleep(5000);
WebElement silen = driver.findElement(By.xpath("//*[@id=\"statuspass\"]/tbody/tr/td[3]/span"));
Thread.sleep(5000);
String sigurna = "сигурна";
assertEquals(sigurna, silen.getText());
//assertTrue(silen.getText().contains("сигурна"));

}



@AfterClass
        public static void tearDown(){
            driver.quit();
        }

    }
