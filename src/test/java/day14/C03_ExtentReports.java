package day14;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.TestBase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class C03_ExtentReports extends TestBase {


    // Hatirlamamiz gereken 3 class, raporlamada bunlari kullanacagiz
    public static ExtentReports extentReports;
    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentTest extentTest;


    @Test
    public void extentReportsTest() {

        // REPORT  PATH
        String currentTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String path = System.getProperty("user.dir")+"/test-output/reports/"+currentTime+"html_report.html";

        // creating HTML report in the path
        extentHtmlReporter = new ExtentHtmlReporter(path);

        // creating extent reports object for generating the Entire reports with configuration
        extentReports = new ExtentReports();

        // ************ RAPORU COSTUMIZE EDEBILIRIZ ************
        extentReports.setSystemInfo("Test Environment", "Regression");
        extentReports.setSystemInfo("Aplication","Techpro Education");
        extentReports.setSystemInfo("Browser","Chrome");
        extentReports.setSystemInfo("Takim","Eagles");
        extentReports.setSystemInfo("Epic","Odeme Sayfasi"); // Epic = Modul
        extentReports.setSystemInfo("Sprint Numarasi","Sprint-145");
        extentReports.setSystemInfo("QA","Busra");

        // ************ EXTRA RAPOR ISMI VE DOCUMAN ISMI EKLEYEBILIRIZ ************
        extentHtmlReporter.config().setDocumentTitle("TechProEd Extent Reports");
        extentHtmlReporter.config().setReportName("Regression Test Sonucu");

        // ************ CONFIGURASION(RAPOR AYARLARI) BITTI ************

        // Raporu projemize ekliyoruz
        extentReports.attachReporter(extentHtmlReporter);

        // Extent Test obje'sini olustur
        extentTest = extentReports.createTest("Extent Report Login Test", "Smoke Test Raporu");

        // Tum ayarlar bitti, Extent test objesi ile loglama(rapora yazdirma) islemini yapabiliriz
        extentTest.pass("Kullanici ana sayfaya gider");
        driver.get("https://www.techproeducation.com");

        //        LMS SAYFASINA GIDELIM
        extentTest.pass("Kullanici LMS sayfasina gider");
        driver.findElement(By.linkText("LMS LOGIN")).click();

        // Test bitti
        extentTest.pass("Test Basariyla Gerceklesti");

        // Raporu goster
        extentReports.flush();


    }

    /*
           ***** Ekran goruntusu alma: *****
       # getScreenShotAs metotu ile alinir. Bu metot Selenium dan gelir
       # getScreenshotAs metotu TakesScreenshot api indan gelir
       # Selenium 4 den itibaren 2 farklı sekilde ekran goruntusu alınabilir: Tum Sayfa, Özel element
       # Olusturmus oldugumuz ReusableMetot yardimizya ister tum sayfanin, istersem belirli bir elementin ekran goruntusunu kolaylıkla alabilirim

           ** Extent Reports :
       # Otomasyon raporları almak icin kullanılır.
       # Extent report ayrı bir API dir. Extent reports seleniumin disindada kullanilabirlar.  Bu API dan gelen metotlar yardımıyla rapor sablonu oluşturabilir.

          ** 3 class kullandık
       # ExtentReports -> sablonu olusturu
       # ExtentHTMLReporter -> sablonu projeye ekler
       # ExtentTest -> raporlama islemini yapar. Loglari rapora yazdırir.
     */
}
