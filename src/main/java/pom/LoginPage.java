package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	//Declaration
		@FindBy(xpath="//h3[text()='Login']")
		private WebElement PageHeader;
		
		@FindBy(id="email")
	     private WebElement emailTF;
		@FindBy(id="password")
		private WebElement passwordTF;
		@FindBy(name="login")
	     private WebElement loginButton;
		//initialization
		public LoginPage(WebDriver driver ) {
			PageFactory.initElements(driver,this);		
		}
		//utilization 
		 public String getPageHeader() {
			 return PageHeader.getText();
		 }
		 public void setEmail(String Email) {
			 emailTF.sendKeys(Email);
		 }
		 public void setPassword(String password) {
			 passwordTF.sendKeys(password);
		 }
		 public void clickLogin() {
			 loginButton.click();
		 }

}
