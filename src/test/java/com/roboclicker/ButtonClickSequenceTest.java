package com.roboclicker;

import com.roboclicker.config.Config;
import com.roboclicker.pages.MainPage;
import com.roboclicker.util.ImageMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for sequential button clicking using SikuliX.
 * Tests finding and clicking buttons 1, 2, 3, and 4 in sequence.
 * Uses Page Object Model pattern for better code organization.
 */
@DisplayName("Button Click Sequence Test")
public class ButtonClickSequenceTest {
    
    private static final Logger logger = LoggerFactory.getLogger(ButtonClickSequenceTest.class);
    private MainPage mainPage;
    
    @BeforeEach
    void setUp() {
        logger.info("Setting up test environment");
        ImageMatcher imageMatcher = new ImageMatcher(Config.SIKULI_SIMILARITY);
        mainPage = new MainPage(imageMatcher);
        
        // Verify images directory exists
        assertTrue(Config.imagesDirectoryExists(), 
            "Images directory does not exist: " + Config.IMAGES_DIR);
    }
    
    @Test
    @Tag("sikuli")
    @Tag("button-click")
    @Tag("sequence")
    @DisplayName("Click Games Tab, Open Vanguard, Open WoW Tab, Open Configuration")
    void testClickButtonsInSequence() {
        logger.info("Starting sequential UI action test");
        logger.info("This test will execute: Games Tab -> Vanguard Page -> WoW Tab -> Configuration");
        
        // Step 1: Click Games Tab
        logger.info("Step 1: Clicking Games Tab");
        boolean gamesTabClicked = mainPage.clickGamesTab();
        assertTrue(gamesTabClicked, 
            "Games Tab not found or could not be clicked within " + Config.TIMEOUT_SECONDS + " seconds");
        sleep(500);
        
        // Step 2: Open Vanguard Page
        logger.info("Step 2: Opening Vanguard Page");
        boolean vanguardOpened = mainPage.openVanguardPage();
        assertTrue(vanguardOpened, 
            "Vanguard page not found or could not be opened within " + Config.TIMEOUT_SECONDS + " seconds");
        sleep(500);
        
        // Step 3: Open WoW Tab
        logger.info("Step 3: Opening WoW Tab");
        boolean wowTabOpened = mainPage.openWoWTab();
        assertTrue(wowTabOpened, 
            "WoW Tab not found or could not be opened within " + Config.TIMEOUT_SECONDS + " seconds");
        sleep(500);
        
        // Step 4: Open Configuration Gear
        logger.info("Step 4: Opening Configuration Gear");
        boolean configOpened = mainPage.openConfigurationGear();
        assertTrue(configOpened, 
            "Configuration gear not found or could not be opened within " + Config.TIMEOUT_SECONDS + " seconds");
        
        logger.info("All UI actions completed successfully in sequence");
        logger.info("Test completed successfully");
    }
    
    @Test
    @Tag("sikuli")
    @Tag("button-click")
    @Tag("sequence")
    @DisplayName("Execute Full Navigation Sequence")
    void testFullNavigationSequence() {
        logger.info("Starting full navigation sequence test");
        logger.info("This test executes the complete navigation flow");
        
        // Execute the full sequence of UI actions
        logger.info("Executing: Games Tab -> Vanguard Page -> WoW Tab -> Configuration");
        
        assertTrue(mainPage.clickGamesTab(), 
            "Failed to click Games Tab");
        sleep(500);
        
        assertTrue(mainPage.openVanguardPage(), 
            "Failed to open Vanguard Page");
        sleep(500);
        
        assertTrue(mainPage.openWoWTab(), 
            "Failed to open WoW Tab");
        sleep(500);
        
        assertTrue(mainPage.openConfigurationGear(), 
            "Failed to open Configuration Gear");
        
        logger.info("Full navigation sequence completed successfully");
        logger.info("Test completed successfully");
    }
    
    /**
     * Helper method to sleep for a specified number of milliseconds.
     * @param milliseconds Number of milliseconds to sleep
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            logger.warn("Thread sleep interrupted: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}

