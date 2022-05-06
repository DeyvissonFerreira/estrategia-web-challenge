package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static WebDriverWait wait;

	@BeforeClass()
	@Parameters({ "plataforma", "browser"})
	protected void defineBrowser(String plataforma, String browser) {
		DesiredCapabilities cap = new DesiredCapabilities();
		if (driver.get() == null) {
			switch (browser) {
			case "Chrome":
				if (plataforma.equalsIgnoreCase("Windows")) {
					System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
				} else if (plataforma.equalsIgnoreCase("Mac")) {
					System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
				} else if (plataforma.equalsIgnoreCase("Linux")) {
					System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
				}
				cap = DesiredCapabilities.chrome();
				ChromeOptions chOptions = new ChromeOptions();
				chOptions.addArguments("--incognito");
				chOptions.setHeadless(true);
				cap.setCapability(ChromeOptions.CAPABILITY, chOptions);
				driver.set(new ChromeDriver(chOptions));
				break;
			}
		}

		wait = new WebDriverWait(driver.get(), 30);
	}
	
	@AfterClass
	protected void quitDriver() {
		driver.get().quit();
		driver.set(null);
	}

	public static void acessaURL(String url) {
		driver.get().manage().window().maximize();
		driver.get().get(url);
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static WebDriverWait getWait() {
		return wait;
	}
}