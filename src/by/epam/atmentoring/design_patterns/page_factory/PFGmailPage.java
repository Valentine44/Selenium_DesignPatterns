package by.epam.atmentoring.design_patterns.page_factory;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
/**
 * Page Factory Gmail page class (superclass of Inbox, Drafts, Sent mail classes)
 * @author Valiantsin_Ivashynka
 *
 */
public class PFGmailPage extends PFPage{
	
	@FindBy (partialLinkText = "Inbox")
	WebElement inboxLink;
	
	@FindBy (partialLinkText = "Drafts")
	WebElement draftsLink;
	
	@FindBy (partialLinkText = "Sent Mail")
	WebElement sentMailLink;
	
	@FindBy (partialLinkText = "Sign out")
	WebElement logOut;
	
	@FindBy (xpath = "//div[@role='button'][@aria-label='Send ‪(Ctrl-Enter)‬']")
	WebElement sendButton;
	
	protected static String sendTime = "";
	
	@FindBy (xpath = "//div[contains(text(), 'COMPOSE')]")
	WebElement composeButton;
	
	@FindBy (xpath = "//textarea[@aria-label='To']")
	WebElement addresseeTextField;
	
	@FindBy (xpath = "//input[@placeholder='Subject']")
	WebElement subjectTextField;
	
	@FindBy (xpath = "//div[@contenteditable='true']")
	WebElement letterBodyTextField;
	
	@FindBy (xpath = "//img[@data-tooltip='Save & Close']")
	WebElement saveAndCloseButton;
	
	@FindBy (xpath = "//input[@dir='ltr']")
	WebElement searchField;
	
	@FindBy (xpath = "//button[@aria-label='Search Gmail']")
	WebElement searchButton;
	
	@FindBy (xpath = "//title[contains(text(), 'Inbox')]")
	WebElement inboxPage;
	
	@FindBy (xpath = "//title[contains(text(), 'Drafts')]")
	WebElement draftsPage;
	
	@FindBy (xpath = "//title[contains(text(), 'Sent Mail')]")
	WebElement sentMailPage;
	
	@FindBy (xpath = "//div[@role='main']//tr[@aria-labelledby][1]")
	WebElement searchResult;
	
	/**
	 * PFGmailPage class constructor
	 * @param driver
	 */
	public PFGmailPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * open Inbox page
	 * @return
	 */
	public PFInboxPage toInboxPage() {
		inboxLink.click();
		return new PFInboxPage(driver);
	}
	/**
	 * open Drafts page
	 * @return
	 */
	public PFDraftsPage toDraftsPage() {
		draftsLink.click();
		return new PFDraftsPage(driver);
	}
	/**
	 * open Sent mail page
	 * @return
	 */
	public PFSentMailPage toSentMailPage() {
		sentMailLink.click();
		return new PFSentMailPage(driver);
	}
	/**
	 * input query into search field
	 * @return
	 */
	public PFGmailPage inputSearchQuery(String searchQuery) {
		searchField.sendKeys(searchQuery);
		return this;
	}
	/**
	 * click 'Search' button
	 * @return
	 */
	public PFGmailPage clickSearch() {
		searchButton.click();
		return this;
	}
	/**
	 * click 'Compose' button to start a letter
	 * @return
	 */
	public PFGmailPage clickCompose() {
		waitElementVisible(composeButton);
		composeButton.click();
		return this;
	}
	/**
	 * input receiver's address into new letter form
	 * @return
	 */
	public PFGmailPage inputAddressee(String addressee) {
		waitElementVisible(addresseeTextField);
		addresseeTextField.sendKeys(addressee);
		addresseeTextField.sendKeys(" ");
		return this;
	}
	/**
	 * input subject into new letter form
	 * @return
	 */
	public PFGmailPage inputSubject() {
		waitElementVisible(subjectTextField);
		subjectTextField.sendKeys(SUBJECT);
		return this;
	}
	/**
	 * input text into new letter form
	 * @return
	 */
	public PFGmailPage inputLetterText() {
		waitElementVisible(letterBodyTextField);
		letterBodyTextField.sendKeys(LETTER_TEXT);
		return this;
	}
	/**
	 * close the letter without sending (it is saved to drafts automatically)
	 * @return
	 */
	public PFGmailPage closeAndSave() {
		waitElementVisible(saveAndCloseButton);
		saveAndCloseButton.click();
		return this;
	}
	/**
	 * send open letter
	 * @return
	 */
	public PFGmailPage sendDraft() {
		waitElementVisible(sendButton);
		getActionTime();
		sendButton.click();
		return this;
	}
	/**
	 * open 1st search result set item
	 * @return
	 */
	public PFLetterPage openSearchResult() {
		waitElementVisible(searchResult);
		searchResult.click();
		return new PFLetterPage(driver);
	}
	/**
	 * get the time when email is sent
	 */
	public void getActionTime() {
		sendTime = (new SimpleDateFormat("hh:mm a").format(new Date()).toLowerCase()).replaceFirst("^0+(?!$)", "");
	}
	/**
	 * log out from Gmail
	 */
	public void logOut() {
		driver.findElement(By.cssSelector("a[title^='Google Account: ']")).click();
		waitElementVisible(logOut);
		logOut.click();
	}
}