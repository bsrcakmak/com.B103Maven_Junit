package practice_2;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.List;

public class P05 extends TestBase {
    @Test
    public void test01() {
    // https://the-internet.herokuapp.com/iframe adresine gidiniz
        driver.get("https://the-internet.herokuapp.com/iframe");

    // An iFrame conteining... başlığının altındaki Text Box’a “Techproeducation” yazin.
        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iframe);
        WebElement textBox = driver.findElement(By.xpath("//p"));
        textBox.clear();
        textBox.sendKeys("Techproeducation");

    // TextBox’in altinda bulunan “Elemental Selenium” linkinin gorunur oldugunu test edin
        driver.switchTo().defaultContent(); // iframe den ciktik
        WebElement elementalSeleniumLinki = driver.findElement(By.xpath("//*[text()='Elemental Selenium']"));
        Assert.assertTrue(elementalSeleniumLinki.isDisplayed());

    // Elemental Selenium linkine tıklayın
        elementalSeleniumLinki.click();
        List<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        System.out.println(windowHandles);
        driver.switchTo().window(windowHandles.get(1));

        /*   ---Set ile de ayni seyi yapabiliriz---
        Set<String> butunSekmeler = driver.getWindowHandles();
        // Açılan sayfada sayfa başlığını yazdırınız

         for (String w : butunSekmeler) {
             if (!w.equals(ilkSayfa)) {
                driver.switchTo().window(w);
             }
         }
         System.out.println(driver.getTitle());
         String ikinciSayfa = driver.getWindowHandle();
         */

    // Açılan sayfada sayfa başlığını yazdırınız
        System.out.println("Elemental Selenyum Sayfa Basligi : "+driver.getTitle());

    // Elemental Selenium başlığı altındaki "Source Labs" linkinin gorunur olduğunu test edin
        WebElement sourceLabsLinki = driver.findElement(By.xpath("//*[text()='Sauce Labs']"));
        Assert.assertTrue(sourceLabsLinki.isDisplayed());

    // Source labs linkine tıklayın
        System.out.println("-------------");
        sourceLabsLinki.click();
        List<String> windowHandles2 = new ArrayList<String>(driver.getWindowHandles());
        System.out.println(windowHandles2);
        driver.switchTo().window(windowHandles2.get(2));

    // Açılan sayfada sayfa başlığını yazdırınız
        System.out.println("Source Labs Sayfa Basligi : "+driver.getTitle());

    // ilk sekmeye geri dönelim ve url'ini yazdıralım
        driver.switchTo().window(windowHandles2.get(0));
        System.out.println("Ilk Sayfa URL'I"+driver.getCurrentUrl());

    // ilk sekmeyi kapatalım
        driver.close();  // ilk sekme kapaninca ikinci sekmeye gecti otomatkmen
        driver.switchTo().window(windowHandles2.get(2));
        // geriye kalan iki sekmeden ikincisine gectik, ilk bastaki indexi 2 old. icin yine get(2) yaptik
        System.out.println(driver.getCurrentUrl());
    }
}
