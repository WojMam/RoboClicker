package com.roboclicker;

import com.roboclicker.config.Config;
import com.roboclicker.util.ImageMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.sikuli.script.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class that detects all button images in sequence.
 * Demonstrates detecting multiple buttons on screen.
 */
@DisplayName("All Button Detection Test")
public class AllButtonDetectionTest {
    
    private static final Logger logger = LoggerFactory.getLogger(AllButtonDetectionTest.class);
    private ImageMatcher imageMatcher;
    
    @BeforeEach
    void setUp() {
        logger.info("Setting up test environment");
        imageMatcher = new ImageMatcher(Config.SIKULI_SIMILARITY);
        
        assertTrue(Config.imagesDirectoryExists(), 
            "Images directory does not exist: " + Config.IMAGES_DIR);
    }
    
    @Test
    @Tag("sikuli")
    @Tag("button-detection")
    @Tag("integration")
    @DisplayName("Detect All Button Images")
    void testDetectAllButtons() {
        logger.info("Starting comprehensive button detection test");
        
        String[] buttonImages = {
            Config.BUTTON_IMAGE_1,
            Config.BUTTON_IMAGE_2,
            Config.BUTTON_IMAGE_3,
            Config.BUTTON_IMAGE_4
        };
        
        int foundCount = 0;
        
        for (int i = 0; i < buttonImages.length; i++) {
            logger.info("Searching for button image {}: {}", i + 1, buttonImages[i]);
            
            Match match = imageMatcher.waitForImage(
                buttonImages[i], 
                Config.TIMEOUT_SECONDS
            );
            
            if (match != null) {
                foundCount++;
                logger.info("Button image {} found at location: ({}, {})", 
                    i + 1, match.getX(), match.getY());
            } else {
                logger.warn("Button image {} not found", i + 1);
            }
        }
        
        logger.info("Found {}/{} button images", foundCount, buttonImages.length);
        
        // At least one button should be found
        assertTrue(foundCount > 0, 
            "No button images were found on screen");
        
        logger.info("Comprehensive button detection test completed");
    }
}

