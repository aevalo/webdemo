package org.gradle.demo.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class GreeterTest {
    private Greeter greeter = null;

    @BeforeAll
    public void setUp() throws Exception {
        greeter = Greeter.getInstance();
        assertNotNull(greeter, "Geeter instance should be available.");
    }

    @Test
	@DisplayName("geet() - null parameter")
    public void greetNull() {
        assertEquals("Hello, World!!!", greeter.greet(null));
    }

    @Test
	@DisplayName("greet() - empty parameter")
    public void greetEmpty() {
        assertEquals("Hello, World!!!", greeter.greet(""));
    }

    @Test
	@DisplayName("greet() - valid parameter")
    public void greetValid() throws Exception {
        assertEquals("Hello, Woz!!!", greeter.greet("Woz"));
    }
}
