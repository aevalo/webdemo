package org.gradle.demo;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("functional")
public class HelloServletFunctionalTest {
    private WebDriver driver;

    @BeforeAll
    public void setUp() throws Exception {
        ChromeDriverManager.getInstance().setup(); 
        driver = new ChromeDriver();               
    }

    @AfterAll
    public void tearDown() {
        if (driver != null)
            driver.quit();                         
    }

    @Test
    public void sayHello() throws Exception {      
        driver.get("http://localhost:8080/webdemo");

        driver.findElement(By.id("say-hello-text-input")).sendKeys("Dolly");
        driver.findElement(By.id("say-hello-button")).click();

        assertEquals("Hello Page", driver.getTitle());
        assertEquals("Hello, Dolly!!!", driver.findElement(By.tagName("h2")).getText());
    }
}
