package genericlibraries;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class contains all reusable methods to perform web driver actions
 * @author kbnla
 *
 */


public class WebDriverUtility {
	WebDriver driver;
	public 	WebDriver launchBrowser(String browser) {
		switch(browser) {
		
		case"chrome":
			System.setProperty("webdriver.chrome.driver","./src/main/resources/chromedriver.exe");
			driver= new ChromeDriver();
			break;
		case"firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case"edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
			default:
				System.out.println("Invalid browser info");
			
			
		}
		driver.manage().window().maximize();
		return driver;
	}
	/**
	 * This method is used to navigate to the application
	 * @param url
	 */
	
	public void navigateToApp(String url) {
		driver.get(url);
	}
	/**
	 * This method is an Implicit wait statement
	 * @param time
	 */
	public void waitTillElementFound(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	/**
	 * This method is used to wait until element visible
	 * @param time
	 * @param element
	 * @return
	 */

	public WebElement explicitWait(long time,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
		
	public WebElement explicitWait(WebElement element,long time) {
		WebDriverWait wait = new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}


	/**
	 * This method is used to wait until element is enabled
	 */
	public Boolean explicitWait1(String title,long time) {
		WebDriverWait wait = new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.titleContains(title));
	}
	/**
	 * This method is used to mouse hover on an element
	 * @param element
	 */
	public void mouseHoverToElement1(WebElement element) {
		Actions actions= new Actions(driver);
		actions.contextClick(element).perform();
	}
	/**
	 * This method is used to double click on an element
	 * @param element
	 */
	public void DoubleClickOnElement(WebElement element) {
		Actions actions= new Actions(driver);
		
		actions.contextClick(element).perform();
	}
	/**
	 * This method is used to right click an element
	 * @param element
	 */
	public void RightClick(WebElement element) {
		Actions actions= new Actions(driver);
		actions.contextClick(element).perform();
	}
	/**
	 * This method is used to drag and drop an element
	 * @param element
	 * @param target
	 */
	
	public void dragAndDropElement(WebElement element,WebElement target) {
		Actions actions= new Actions(driver);
		actions.dragAndDrop(element,target).perform();
	}	/**
	 * This method is used to select an element from drop down  based on index 
	 * @param element 
	 * @param index
	 */
	public void handleDropDown(WebElement element,int index ) {
		Select select = new Select(element);
		select.selectByIndex(index);
		
	}
	/**
	 * THis method is used to select an element from dropDown based on value
	 * @param element
	 * @param value
	 */
	public void handleDropdown(WebElement element,String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	/**
	 * THis method is used to select an element  from dropDown based on text
	 * @param element
	 * @param text
	 */
	public void handleDropDown(WebElement element,String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		
	}
	/**
	 * THis method is used to switch to frame based on index
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * THis method is used to switch to frame based on idOrName
	 * @param idOrName
	 */
	
		public void switchToFrame(	String idOrName) {
			driver.switchTo().frame(idOrName);
		
		
		}
		/**
		 * this method is used to switch to frame based on frame element reference
		 * @param frameElement
		 */
		
		public void switchToFrame(WebElement frameElement) {
			driver.switchTo().frame(frameElement);
		}
		/**
		 * this method is used to switch back to the frame
		 */

		public void switchBackFromFrame() {
			driver.switchTo().defaultContent();
	

		}
		public void takeScreenshot(WebDriver driver,String className, JavaUtility jutil) {
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			File dest= new File("./Screenshot/"+className+"_"+jutil.GetCurrentTime()+".png");
			try {
				FileUtils.copyDirectory(src, dest);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		/**
		 * TGis method is used to scroll till the element
		 * @param element
		 */
			
			public void scrollTillElement(WebElement element) {
				JavascriptExecutor js=(JavascriptExecutor) driver;
				js.executeScript("argument[0].scrollIntoView(true)",element);
			}
			
				/**
				 * THGis method is used to handle alert
				 * @param status
				 */
				
			public void handleAlert(String status) {
				Alert alert = driver.switchTo().alert();
				if(status.equalsIgnoreCase("ok"))
					alert.accept();
				else
					alert.dismiss();
				
			}
			/** 
			 * THis method is used to handle childBrowser alert
			 */
			public void  handleChildBrowser() {
				Set<String> set= driver.getWindowHandles();
				for(String windowId:set) {
					driver.switchTo().window(windowId);
				}
				
				}
			/**
			 * THis method returns parent browser address
			 * @return
			 */
			public String getParentWindowId() {
				
				return driver.getWindowHandle();
		
				
			}
			/**
			 * This method used to switch to specified window
			 * @param windowID
			 */
			
			public void switchToWindow(String windowID) {
				driver.switchTo().window(windowID);
				
			}
			/**
			 * THis method is used to close the window
			 */
			public void closeCurrentWindow() {
				driver.close();
			}
			/**
			 * This method is used to close all the windows
			 */
			public void closeAllWindow() {
				driver.quit();
			}

}
