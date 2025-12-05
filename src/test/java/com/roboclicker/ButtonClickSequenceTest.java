package com.roboclicker;

import com.roboclicker.config.Config;
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
 */
@DisplayName("Button Click Sequence Test")
public class ButtonClickSequenceTest {
    
    private static final Logger logger = LoggerFactory.getLogger(ButtonClickSequenceTest.class);
    private ImageMatcher imageMatcher;
    
    @BeforeEach
    void setUp() {
        logger.info("Setting up test environment");
        imageMatcher = new ImageMatcher(Config.SIKULI_SIMILARITY);
        
        // Verify images directory exists
        assertTrue(Config.imagesDirectoryExists(), 
            "Images directory does not exist: " + Config.IMAGES_DIR);
    }
    
    @Test
    @Tag("sikuli")
    @Tag("button-click")
    @Tag("sequence")
    @DisplayName("Click Buttons 1, 2, 3, 4 in Sequence")
    void testClickButtonsInSequence() {
        logger.info("Starting sequential button click test");
        logger.info("This test will find and click buttons 1, 2, 3, and 4 in order");
        
        // Array of button images to click in sequence
        String[] buttonImages = {
            Config.BUTTON_IMAGE_1,
            Config.BUTTON_IMAGE_2,
            Config.BUTTON_IMAGE_3,
            Config.BUTTON_IMAGE_4
        };
        
        String[] buttonNames = {"Button 1", "Button 2", "Button 3", "Button 4"};
        
        // Click each button in sequence
        for (int i = 0; i < buttonImages.length; i++) {
            logger.info("Step {}: Looking for {} at path: {}", i + 1, buttonNames[i], buttonImages[i]);
            
            // Wait for and click the button
            boolean clicked = imageMatcher.clickImage(buttonImages[i], Config.TIMEOUT_SECONDS);
            
            assertTrue(clicked, 
                String.format("%s not found or could not be clicked within %d seconds", 
                    buttonNames[i], Config.TIMEOUT_SECONDS));
            
            logger.info("Successfully clicked {}", buttonNames[i]);
            
            // Add a small delay between clicks to allow UI to respond
            if (i < buttonImages.length - 1) {
                try {
                    Thread.sleep(500); // 500ms delay between clicks
                    logger.debug("Waiting 500ms before next click");
                } catch (InterruptedException e) {
                    logger.warn("Thread sleep interrupted: {}", e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
        
        logger.info("All buttons clicked successfully in sequence");
        logger.info("Test completed successfully");
    }
    
    @Test
    @Tag("sikuli")
    @Tag("button-click")
    @Tag("sequence")
    @DisplayName("Click Button 1, then Button 2, then Button 3, then Button 4")
    void testClickButton1Then2Then3Then4() {
        logger.info("Starting explicit sequential button click test");
        
        // Step 1: Find and click Button 1
        logger.info("Step 1: Finding and clicking Button 1");
        boolean button1Clicked = imageMatcher.clickImage(Config.BUTTON_IMAGE_1, Config.TIMEOUT_SECONDS);
        assertTrue(button1Clicked, 
            "Button 1 not found or could not be clicked within " + Config.TIMEOUT_SECONDS + " seconds");
        logger.info("Button 1 clicked successfully");
        
        // Small delay
        sleep(500);
        
        // Step 2: Find and click Button 2
        logger.info("Step 2: Finding and clicking Button 2");
        boolean button2Clicked = imageMatcher.clickImage(Config.BUTTON_IMAGE_2, Config.TIMEOUT_SECONDS);
        assertTrue(button2Clicked, 
            "Button 2 not found or could not be clicked within " + Config.TIMEOUT_SECONDS + " seconds");
        logger.info("Button 2 clicked successfully");
        
        // Small delay
        sleep(500);
        
        // Step 3: Find and click Button 3
        logger.info("Step 3: Finding and clicking Button 3");
        boolean button3Clicked = imageMatcher.clickImage(Config.BUTTON_IMAGE_3, Config.TIMEOUT_SECONDS);
        assertTrue(button3Clicked, 
            "Button 3 not found or could not be clicked within " + Config.TIMEOUT_SECONDS + " seconds");
        logger.info("Button 3 clicked successfully");
        
        // Small delay
        sleep(500);
        
        // Step 4: Find and click Button 4
        logger.info("Step 4: Finding and clicking Button 4");
        boolean button4Clicked = imageMatcher.clickImage(Config.BUTTON_IMAGE_4, Config.TIMEOUT_SECONDS);
        assertTrue(button4Clicked, 
            "Button 4 not found or could not be clicked within " + Config.TIMEOUT_SECONDS + " seconds");
        logger.info("Button 4 clicked successfully");
        
        logger.info("All buttons clicked in sequence: 1 -> 2 -> 3 -> 4");
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

