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
 * Test class for button detection using SikuliX.
 * Tests image-based button detection on screen.
 */
@DisplayName("Button Detection Tests")
public class ButtonDetectionTest {
    
    private static final Logger logger = LoggerFactory.getLogger(ButtonDetectionTest.class);
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
    @Tag("button-detection")
    @DisplayName("Detect Button Image 1 using SikuliX")
    void testDetectButtonImage1() {
        logger.info("Starting button detection test for image 1");
        logger.info("Looking for button image: {}", Config.BUTTON_IMAGE_1);
        
        Match match = imageMatcher.waitForImage(
            Config.BUTTON_IMAGE_1, 
            Config.TIMEOUT_SECONDS
        );
        
        assertNotNull(match, 
            "Button image 1 not found on screen within " + Config.TIMEOUT_SECONDS + " seconds");
        
        logger.info("Button image 1 successfully detected at location: ({}, {})", 
            match.getX(), match.getY());
        logger.info("Test completed successfully");
    }
    
    @Test
    @Tag("sikuli")
    @Tag("button-detection")
    @DisplayName("Detect Button Image 2 using SikuliX")
    void testDetectButtonImage2() {
        logger.info("Starting button detection test for image 2");
        logger.info("Looking for button image: {}", Config.BUTTON_IMAGE_2);
        
        Match match = imageMatcher.waitForImage(
            Config.BUTTON_IMAGE_2, 
            Config.TIMEOUT_SECONDS
        );
        
        assertNotNull(match, 
            "Button image 2 not found on screen within " + Config.TIMEOUT_SECONDS + " seconds");
        
        logger.info("Button image 2 successfully detected at location: ({}, {})", 
            match.getX(), match.getY());
        logger.info("Test completed successfully");
    }
    
    @Test
    @Tag("sikuli")
    @Tag("button-detection")
    @DisplayName("Detect Button Image 3 using SikuliX")
    void testDetectButtonImage3() {
        logger.info("Starting button detection test for image 3");
        logger.info("Looking for button image: {}", Config.BUTTON_IMAGE_3);
        
        Match match = imageMatcher.waitForImage(
            Config.BUTTON_IMAGE_3, 
            Config.TIMEOUT_SECONDS
        );
        
        assertNotNull(match, 
            "Button image 3 not found on screen within " + Config.TIMEOUT_SECONDS + " seconds");
        
        logger.info("Button image 3 successfully detected at location: ({}, {})", 
            match.getX(), match.getY());
        logger.info("Test completed successfully");
    }
    
    @Test
    @Tag("sikuli")
    @Tag("button-detection")
    @DisplayName("Detect Button Image 4 using SikuliX")
    void testDetectButtonImage4() {
        logger.info("Starting button detection test for image 4");
        logger.info("Looking for button image: {}", Config.BUTTON_IMAGE_4);
        
        Match match = imageMatcher.waitForImage(
            Config.BUTTON_IMAGE_4, 
            Config.TIMEOUT_SECONDS
        );
        
        assertNotNull(match, 
            "Button image 4 not found on screen within " + Config.TIMEOUT_SECONDS + " seconds");
        
        logger.info("Button image 4 successfully detected at location: ({}, {})", 
            match.getX(), match.getY());
        logger.info("Test completed successfully");
    }
    
    @Test
    @Tag("sikuli")
    @Tag("image-matching")
    @DisplayName("Verify Image Matcher Utility")
    void testImageMatcherUtility() {
        logger.info("Testing ImageMatcher utility class");
        
        // Test image existence check
        boolean exists = imageMatcher.imageExists(Config.BUTTON_IMAGE_1);
        logger.info("Image existence check result: {}", exists);
        
        // Test find image
        Match match = imageMatcher.findImage(Config.BUTTON_IMAGE_1);
        if (match != null) {
            logger.info("Image found at: ({}, {})", match.getX(), match.getY());
        }
        
        // Verify similarity setting
        imageMatcher.setSimilarity(0.9);
        assertEquals(0.9, imageMatcher.getSimilarity(), 
            "Similarity threshold should be set to 0.9");
        
        logger.info("ImageMatcher utility test completed");
    }
}

