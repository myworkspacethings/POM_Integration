package com.qa.opencart.util;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.base.BasePage;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(this.driver);
	}

	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if (BasePage.highlight.equals("true")) {
			jsUtil.flash(element);
		}
		return element;
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doActionSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		Actions action = new Actions(driver);
		action.sendKeys(element, value).perform();
	}

	public void doActionClick(By locator) {
		WebElement element = getElement(locator);
		Actions action = new Actions(driver);
		action.click(element).perform();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	// **********************Select DropDown option using Select HTML Tag************************
	public void doDropDownSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doDropDownSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doDropDownSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	// ********************Select DropDown option from thelist********************
	public ArrayList<String> getAllDropDownOptionsList(By locator) {

		ArrayList<String> optionsValueList = new ArrayList<String>();

		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement ele : optionsList) {
			String text = ele.getText();
			optionsValueList.add(text);
			System.out.println(text);
		}
		return optionsValueList;
	}

	public void selectOptionFromDropDown(By locator, String value) {

		Select select = new Select(getElement(locator));
		List<WebElement> optionList = select.getOptions();

		for (int i = 0; i < optionList.size(); i++) {
			String text = optionList.get(i).getText();

			if (text.equals(value)) {
				optionList.get(i).click();
			}
		}
	}

	public void selectDropDownWithoutSelectClass(By locator, String value) {
		List<WebElement> optionList = driver.findElements(locator);

		for (WebElement ele : optionList) {
			String text = ele.getText();

			if (text.equals(value)) {
				ele.click();
				break;
			}
		}
	}

	// ****************************RightClick**************************************
	public void doRightClick(By locator, By options, String value) throws InterruptedException {

		WebElement rightClickMe_Button = getElement(locator);

		Actions action = new Actions(driver);
		action.contextClick(rightClickMe_Button).perform();

		Thread.sleep(1000);

		List<WebElement> rightClickOptions = driver.findElements(options);

		for (WebElement ele : rightClickOptions) {
			String text = ele.getText();
			System.out.println(text);

			if (text.equals(value)) {
				ele.click();
				break;
			}

		}

	}

	/**
	 * This method is used to return single selection, multi selection and select
	 * all Please pass the unique values
	 * 
	 * @param locator
	 * @param         value...
	 */
	public void selectChoiceFromDropDown(By locator, String... value) {

		List<WebElement> choiceList = driver.findElements(locator);

		if (!value[0].equals("all")) {

			for (int i = 0; i < choiceList.size(); i++) {
				String text = choiceList.get(i).getText();

				for (int j = 0; j < value.length; j++) {
					if (text.equals(value[j])) {
						choiceList.get(i).click();
						break;
					}
				}
			}
		} else {
			try {
				for (int k = 0; k < choiceList.size(); k++) {
					choiceList.get(k).click();
				}
			} catch (Exception e) {
			}

		}

	}

	// *****************************Move To Element************************
	public void moveToElement(By locator, By locator1, String text) throws InterruptedException {
		WebElement login_ParentMenuElement = getElement(locator);

		Actions action = new Actions(driver);
		action.moveToElement(login_ParentMenuElement).perform();

		WebElement members_ParentMenu = driver.findElement(locator1);
		action.moveToElement(members_ParentMenu).perform();

		Thread.sleep(2000);
		driver.findElement(By.linkText(text)).click();
	}

	// **************************Check Web Element is present************************
	public boolean checkWebElementPresent(By locator) {

		int elementCount = driver.findElements(locator).size();
		System.out.println("Total Element Count: " + elementCount);

		if (elementCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	// **************************DragAndDrop**********************************
	public WebElement getElementBySource(By source) {
		return driver.findElement(source);
	}

	public WebElement getElementByDestination(By destination) {
		return driver.findElement(destination);
	}

	public void doDragAndDrop(By source, By destination) {

		WebElement sourceElement = getElementBySource(source);
		WebElement destinationElement = getElementByDestination(destination);

		Actions action = new Actions(driver);

//		action.clickAndHold(sourceElement).moveToElement(destinationElement).release().build().perform();
		action.dragAndDrop(sourceElement, destinationElement).perform();
	}

	// *********************************WaitUtils****************************
	
	public List<WebElement> visibilityOfAllElements(int timeouts, By locator) {
		WebDriverWait wait=new WebDriverWait(driver, timeouts);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void getPageLinksText(int timeouts, By locator) {
		List<WebElement>linksText=visibilityOfAllElements(timeouts, locator);
		linksText.stream().forEach(ele -> System.out.println(ele.getText()));
//	   visibilityOfAllElements(timeouts, locator).stream().forEach(ele-> System.out.println(ele.getText()));

	}
	
	public void getPageLinksAttribute(int timeouts, By locator) {
		visibilityOfAllElements(timeouts, locator).stream().forEach(ele-> System.out.println(ele.getAttribute("href")));
	}
	
	public WebElement waitForElementToBeLocated(int timeouts, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementToBeVisible(By locator, int timouts) {
		WebElement element=getElement(locator);
		WebDriverWait wait=new WebDriverWait(driver, timouts);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public String waitForPageTitleToBePresent(int timeouts, String text) {
		WebDriverWait wait1 = new WebDriverWait(driver, timeouts);
		wait1.until(ExpectedConditions.titleContains(text));
		return driver.getTitle();
	}

	public Alert waitForAlertToBePresent(int timeouts) {
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public Boolean waitForUrl(int timeouts, String value) {
		WebDriverWait wait=new WebDriverWait(driver, timeouts);
		return wait.until(ExpectedConditions.urlContains(value));
	}
	
	public void clickWhenReady(int timeouts, By locator) {
		WebDriverWait wait= new WebDriverWait(driver, timeouts);
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
//	************************************FluentWait*************************************************
	public WebElement waitForElementWithFluentWait(int timouts, int pollingTime, By locator) {
		Wait<WebDriver>wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timouts))
					.pollingEvery(Duration.ofSeconds(pollingTime))
						.ignoring(NullPointerException.class);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	//***************************Retrying Element******************************************
	public WebElement retryingElement(By locator) {
		WebElement element = null;
		int attempts = 0;

		while (attempts < 30) {

			try {
				element = driver.findElement(locator);
				break;
			}

			catch (StaleElementReferenceException e1) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e2) {
				}
			}
			
			catch (NoSuchElementException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e2) {
				}
				
				System.out.println("Element is not found in attempts: " + (attempts + 1));
			}
			attempts++;
		}
		return element;
	}
}