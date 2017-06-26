package by.epam.atmentoring.design_patterns.page_object;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Gmail page class (superclass of Inbox, Drafts, Sent mail classes)
 * @author Valiantsin_Ivashynka
 *
 */
public class GmailPage extends Page{
	private static final By INBOX_LINK = By.partialLinkText("Inbox");
	private static final By DRAFTS_LINK = By.partialLinkText("Drafts");
	private static final By SENT_MAIL_LINK = By.partialLinkText("Sent Mail");
	private static final By LOG_OUT = By.linkText("Sign out");
	private static final By SEND_BUTTON = By.xpath("//div[@role='button'][@aria-label='Send ‪(Ctrl-Enter)‬']");
	protected static String sendTime = "";
	private static final By COMPOSE_BUTTON = By.xpath("//div[contains(text(), 'COMPOSE')]");
	private static final By ADDRESSEE_TEXT_FIELD = By.xpath("//textarea[@aria-label='To']");
	private static final By SUBJECT_TEXT_FIELD = By.xpath("//input[@placeholder='Subject']");
	private static final By LETTER_BODY_TEXT_FIELD = By.xpath("//div[@contenteditable='true']");
	private static final By SAVE_AND_CLOSE_BUTTON = By.xpath("//img[@data-tooltip='Save & Close']");
	private static final By SEARCH_FIELD = By.xpath("//input[@dir='ltr']");
	private static final By SEARCH_BUTTON = By.xpath("//button[@aria-label='Search Gmail']");
	private static final By INBOX_PAGE = By.xpath("//title[contains(text(), 'Inbox')]");
	private static final By DRAFTS_PAGE = By.xpath("//title[contains(text(), 'Drafts')]");
	private static final By SENT_MAIL_PAGE = By.xpath("//title[contains(text(), 'Sent Mail')]");
	private static final By SEARCH_RESULT = By.xpath("//div[@role='main']//tr[@aria-labelledby][1]");

	/**
	 * GmailPage class constructor
	 * @param driver
	 */
	public GmailPage(WebDriver driver) {
		super(driver);
	}
	/**
	 * open Inbox page
	 * @return
	 */
	public InboxPage toInboxPage() {
		driver.findElement(INBOX_LINK).click();
		waitElementPresent(INBOX_PAGE);
		return new InboxPage(driver);
	}
	/**
	 * open Drafts page
	 * @return
	 */
	public DraftsPage toDraftsPage() {
		driver.findElement(DRAFTS_LINK).click();
		waitElementPresent(DRAFTS_PAGE);
		return new DraftsPage(driver);
	}
	/**
	 * open Sent mail page
	 * @return
	 */
	public SentMailPage toSentMailPage() {
		driver.findElement(SENT_MAIL_LINK).click();
		waitElementPresent(SENT_MAIL_PAGE);
		return new SentMailPage(driver);
	}
	/**
	 * input query into search field
	 * @return
	 */
	public GmailPage inputSearchQuery(String searchQuery) {
		driver.findElement(SEARCH_FIELD).sendKeys(searchQuery);
		return this;
	}
	/**
	 * click 'Search' button
	 * @return
	 */
	public GmailPage clickSearch() {
		driver.findElement(SEARCH_BUTTON).click();
		return this;
	}
	/**
	 * click 'Compose' button to start a letter
	 * @return
	 */
	public GmailPage clickCompose() {
		waitElementVisible(COMPOSE_BUTTON);
		driver.findElement(COMPOSE_BUTTON).click();
		return this;
	}
	/**
	 * input receiver's address into new letter form
	 * @return
	 */
	public GmailPage inputAddressee(String addressee) {
		waitElementVisible(ADDRESSEE_TEXT_FIELD);
		driver.findElement(ADDRESSEE_TEXT_FIELD).sendKeys(addressee);
		driver.findElement(ADDRESSEE_TEXT_FIELD).sendKeys(" ");
		return this;
	}
	/**
	 * input subject into new letter form
	 * @return
	 */
	public GmailPage inputSubject() {
		waitElementVisible(SUBJECT_TEXT_FIELD);
		driver.findElement(SUBJECT_TEXT_FIELD).sendKeys(SUBJECT);
		return this;
	}
	/**
	 * input text into new letter form
	 * @return
	 */
	public GmailPage inputLetterText() {
		waitElementVisible(LETTER_BODY_TEXT_FIELD);
		driver.findElement(LETTER_BODY_TEXT_FIELD).sendKeys(LETTER_TEXT);
		return this;
	}
	/**
	 * close the letter without sending (it is saved to drafts automatically)
	 * @return
	 */
	public GmailPage closeAndSave() {
		waitElementVisible(SAVE_AND_CLOSE_BUTTON);
		driver.findElement(SAVE_AND_CLOSE_BUTTON).click();
		return this;
	}
	/**
	 * send open letter
	 * @return
	 */
	public GmailPage sendDraft() {
		waitElementVisible(SEND_BUTTON);
		getActionTime();
		driver.findElement(SEND_BUTTON).click();
		return this;
	}
	/**
	 * open 1st search result set item
	 * @return
	 */
	public LetterPage openSearchResult() {
		waitElementPresent(SEARCH_RESULT);
		driver.findElement(SEARCH_RESULT).click();
		return new LetterPage(driver);
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
		waitElementVisible(LOG_OUT);
		driver.findElement(LOG_OUT).click();
	}
}