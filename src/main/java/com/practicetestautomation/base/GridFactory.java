package com.practicetestautomation.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class GridFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private String browser;
	private String platform;
	private Logger log;

	public GridFactory(String browser,String platform, Logger log) {
		this.browser = browser.toLowerCase();
		this.platform=platform;
		this.log = log;
	}


	public WebDriver createDriver() {
		log.info("connecting to node with: " + browser);

		DesiredCapabilities capabilities = new DesiredCapabilities();


		switch (browser) {
		case "chrome":
			capabilities.setBrowserName("chrome");
			break;

		case "firefox":
			capabilities.setBrowserName("firefox");
			break;

		default:
			capabilities.setBrowserName("chrome");
			break;
		}


		switch (platform) {
			case "Windows 11":
				capabilities.setPlatform(Platform.WIN11);
				break;

			case "MAC":
				capabilities.setPlatform(Platform.MAC);
				break;

			default:
				capabilities.setPlatform(Platform.WIN11);
				break;
		}

		URL url=null;
        try {
            url = new URL("http://localhost:4444");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

		driver.set(new RemoteWebDriver(url, capabilities));

        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
		return driver.get();
	}
}
