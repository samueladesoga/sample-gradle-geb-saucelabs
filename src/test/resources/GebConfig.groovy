/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/#configuration
*/


import geb.driver.SauceLabsDriverFactory
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

waiting {
	timeout = 5
}

def sauceLabsBrowser = System.getProperty("geb.saucelabs.browser")
if (sauceLabsBrowser) {
	driver = {
		def username = System.getenv("SAUCE_USERNAME")
		assert username
		def accessKey = System.getenv("SAUCE_ACCESS_KEY")
		assert accessKey
		new SauceLabsDriverFactory().create(sauceLabsBrowser, username, accessKey)
	}
}

environments {
	
	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
        def chromeDriver = new File('/Users/samueladesoga/projects/personal/chromedriver/chromedriver') // add .exe for Windows...
        System.setProperty('webdriver.chrome.driver', chromeDriver.absolutePath)
		driver = { new ChromeDriver() }
	}
	
	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		driver = { new FirefoxDriver() }
	}

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "https://staging-yangah.herokuapp.com"
reportsDir = 'target/geb-reports'
driver = "chrome"