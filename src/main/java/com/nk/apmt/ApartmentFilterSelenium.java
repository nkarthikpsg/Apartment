package com.nk.apmt;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.nk.apmt.dto.ApartmentDetail;
import com.nk.apmt.dto.ApartmentList;

import io.github.pramcharan.wd.binary.downloader.WebDriverBinaryDownloader;
import io.github.pramcharan.wd.binary.downloader.enums.Browser;

public class ApartmentFilterSelenium
{
    RemoteWebDriver driver = null;

    public ApartmentFilterSelenium(RemoteWebDriver driver)
    {
        this.driver = getDriver();
        ;

    }

    public ApartmentList start(String searchUrl)
    {
        openPage(searchUrl);

        List<WebElement> apmtList = getApmtList();

        ApartmentList apartmentList = new ApartmentList();
        for (WebElement apmt : apmtList)
        {
            ApartmentDetail aparmentDetail = getApmtDetail(apmt);
            apartmentList.addApartment(aparmentDetail);
        }

        System.out.println(apartmentList);

        return apartmentList;

    }

    private ApartmentDetail getApmtDetail(WebElement apmt)
    {
        ApartmentDetail aparmentDetail = new ApartmentDetail();

        aparmentDetail.setName(
                apmt.findElement(By.className("property-title")).findElement(By.className("js-placardTitle"))
                        .getText());
        aparmentDetail.setPriceRange(apmt.findElement(By.className("price-range")).getText());
        aparmentDetail.setDetailUrl(apmt.findElement(By.className("property-link")).getAttribute("href"));
        aparmentDetail.setImageUrl(apmt.findElement(By.className("item")).getAttribute("style"));

        return aparmentDetail;
    }

    private List<WebElement> getApmtList()
    {
        WebElement placardContainer = driver.findElementById("placards");

        List<WebElement> aptList = placardContainer.findElements(By.className("mortar-wrapper"));
        return aptList;
    }

    private void openPage(String searchUrl)
    {
        driver.get(searchUrl);

        scrollToEnd();
    }

    private void scrollToEnd()
    {
        JavascriptExecutor js = driver;
        WebElement Element = driver.findElement(By.id("paging"));

        // Scrolling down the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", Element);

        //        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void openListPage(String searchUrl)
    {
    }

    public static RemoteWebDriver getDriver()
    {
        WebDriverBinaryDownloader.create().downloadLatestBinaryAndConfigure(Browser.FIREFOX);

        RemoteWebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        //        ChromeOptions options = new ChromeOptions();
        //        options.addArguments("--start-maximized");
        //        driver = new ChromeDriver(options);

        return driver;
    }

}
