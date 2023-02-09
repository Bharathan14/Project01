package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected static WebDriver driver;

	public static WebDriver chromeBrowser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
	}

	public static WebDriver browserLaunch(String bname) {
		if (bname.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (bname.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (bname.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		return driver;
	}

	public static WebDriver browserLaunch2(String bname) {
		switch (bname) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		return driver;
	}

	public static void urlLaunch(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public static void implicitlyWait(int a) {
		driver.manage().timeouts().implicitlyWait(a, TimeUnit.SECONDS);
	}

	public static void sendkeys(WebElement e, String value) {
		e.sendKeys(value);
	}

	public static void click(WebElement e) {
		e.click();
	}

	public static String getcurrenturl() {
		String currenturl = driver.getCurrentUrl();
		return currenturl;
	}

	public static String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public static void quit() {
		driver.quit();
	}

	public static void clear(WebElement e) {
		e.clear();
	}

	public static void moveToelement(WebElement e) {
		Actions a = new Actions(driver);
		a.moveToElement(e).perform();
	}

	public static void dragAndDrop(WebElement src, WebElement tar) {
		Actions a = new Actions(driver);
		a.dragAndDrop(src, tar).perform();
	}

	public static void selectByIndex(WebElement e, int a) {
		Select s = new Select(e);
		s.selectByIndex(a);
	}
	public static void selectByValue(WebElement e, String i) {
		Select s = new Select(e);
		s.selectByValue(i);

	}
	public static void selectbyvisibletext(WebElement e, String a) {
		Select s = new Select(e);
		s.selectByVisibleText(a);

	}
	
	public static String getText(WebElement e) {
		String text= e.getText();
		return text;
		
	}
	public static String getAttributes(WebElement e) {
		String attribute = e.getAttribute("value");
        return attribute;
	}
	
	public static  void refresh() {
    driver.navigate().refresh();
		
	}	
	
       public static String readExcel (String filename, String Sheet,int row, int c) throws IOException {
		
		File f=new File("C:\\Users\\Smiley\\eclipse-workspace\\Maven1st\\src\\test\\resources\\Excel\\"+filename+".xlsx");
		FileInputStream st=new FileInputStream(f);
        Workbook w=new XSSFWorkbook(st);
		Sheet s = w.getSheet(Sheet);
		Row r = s.getRow(row);
		Cell cell = r.getCell(c);
		int type = cell.getCellType();
		String value=null;                                              //type 0 = numbers,Date
   	                                                                    //type 1 = String
		if(type==1) {   
			  value = cell.getStringCellValue();
		}
		else {
			if(DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat sd=new SimpleDateFormat("dd-MMM-yyyy");
				value = sd.format(dateCellValue);
			}
			else {
				double numericCellValue = cell.getNumericCellValue();
				long num=(long)numericCellValue;
				value = String.valueOf(num);
			}
			
		}
		return value;
		
	}}	
	 

