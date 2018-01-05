package com.yottabyte.webDriver;

import com.yottabyte.constants.WebDriverConst;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 ����* This is an customized webdriver event listener.
 ����* Now it implements onException method: webdriver will take a screenshot
 ����* when it meets an exception. It's good but not so usable. And when we use
 ����* WebDriverWait to wait for an element appearing, the webdriver will throw
 ����* exception always and the onException will be excuted again and again, which
 ����* generates a lot of screenshots.
 ����* Put here for study
 ����* Usage:
 ����* WebDriver driver = new FirefoxDriver();
 ����* WebDriverEventListener listener = new CustomWebDriverEventListener();
 ����* return new EventFiringWebDriver(driver).register(listener);
 ����*
 ����* @author qa
 ����*
    */
public class EventListener extends AbstractWebDriverEventListener {
    private By lastFindBy;
    private String originalValue;

    public void autoScreenShot(Throwable ex){
        String filename = generateRandomFilename(ex);
        try {
            byte[] btDataFile = Base64.decodeBase64(extractScreenShot(ex).getBytes());
            File of = new File(filename);
            File parent = of.getParentFile();

            if(parent!=null&&!parent.exists()){
                parent.mkdirs();
            }
            FileOutputStream osf = new FileOutputStream(of);
            osf.write(btDataFile);
            osf.flush();
            osf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成文件
     * @param ex
     * @return
     */
    private String generateRandomFilename(Throwable ex) {
        String sp = File.separator;
        String ErrorImgFilePath = System.getProperty("user.dir") + sp + "target" + sp +
                "cucumber-html-reports" + sp + "embeddings" + sp + "error_img";
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd-hh-mm-ss");
        String dateString = formatter.format(new Date());
        String filename = ex.getMessage();
        int i = filename.indexOf('\n');
        filename = filename.substring(0, i).replaceAll("\\s", "_").replaceAll("\"","@")
                .replaceAll(":", "") ;
        filename = ErrorImgFilePath + "" + sp + "" + "@@" + dateString + "@@" + filename + ".png";
        System.out.println("\n" + "filename : " + filename + "\n");
        return filename;
    }

    /**
     * 生成截图
     * @param ex
     * @return
     */
    private String extractScreenShot(Throwable ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof ScreenshotException) {
            return ((ScreenshotException) cause).getBase64EncodedScreenshot();
        }
        return null;
    }



    @Override
    public void onException(Throwable ex, WebDriver arg1 ) {
        autoScreenShot(ex);
        if(ex.getClass().equals(NoSuchElementException.class)){
            System.out.println("\n" + "WebDriver error: Element not found: " + lastFindBy + "\n");
        }else if (ex.getClass().equals(TimeoutException.class)){
            System.out.println("\n" + "WebDriver error: Element Time Out: " + lastFindBy + "\n");
        }else {
            System.out.println("WebDriver error:"+ ex);
        }
    }


    /**
     * 知道记录浏览器各种操作
     */
    public void beforeNavigateTo(String url, WebDriver selenium){
        System.out.println("\n" + "WebDriver navigating to: '"+url+"'" + "\n");
    }
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        originalValue = element.getAttribute("value");
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        System.out.println("\n" + "WebDriver changing value in element found: "+lastFindBy+" from '"+originalValue+"' to '"+element.getAttribute("value")+"'" + "\n");
    }

    public void beforeFindBy(By by, WebElement element, WebDriver selenium){
        lastFindBy = by;
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(selenium)
                    .withTimeout(WebDriverConst.WAIT_FOR_ELEMENT_TIMEOUT_WHEN_PAGE_LOADING, TimeUnit.MILLISECONDS)
                    .pollingEvery(WebDriverConst.WAIT_FOR_ELEMENT_POLLING_DURING, TimeUnit.MILLISECONDS)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(TimeoutException.class);
            wait.until((new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return document.readyState === 'complete'");
                }
            }));
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(lastFindBy)
            );
            selenium.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //reset implicitlyWait
        } catch (NoSuchElementException exception) {
//            throw exception;
        } catch (TimeoutException e){
//            throw e;
        }
    }
    public void beforeNavigateBack(WebDriver selenium){}
    public void beforeNavigateForward(WebDriver selenium){}
    public void beforeClickOn(WebElement element, WebDriver selenium){}
    public void beforeScript(String script, WebDriver selenium){}
    public void afterClickOn(WebElement element, WebDriver selenium){
        String locator=element.toString().split("-> ")[1];
        System.out.println("\n" + "WebDriver clicking on: '"+locator.substring(0, locator.length()-1)+"'" + "\n");
    }
    public void afterFindBy(By by, WebElement element, WebDriver selenium){
        if (by != null && element != null){
            element = element.findElement(by);
            ((JavascriptExecutor) selenium).executeScript("arguments[0].scrollIntoView();", element);
        }else if (by != null && element == null){
            try {
                element = selenium.findElement(by);
                ((JavascriptExecutor) selenium).executeScript("arguments[0].scrollIntoView();", element);
            }catch (NoSuchElementException e){
                System.out.println("Element " + by + " doesn't exist!");
            }
        }
    }
    public void afterNavigateBack(WebDriver selenium){}
    public void afterNavigateForward(WebDriver selenium){}
    public void beforeNavigateRefresh(WebDriver webDriver) {}
    public void afterNavigateRefresh(WebDriver webDriver) {}
    public void afterNavigateTo(String url, WebDriver selenium){}
    public void afterScript(String script, WebDriver selenium){}
    }