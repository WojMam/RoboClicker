package com.roboclicker.util;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Utility class for image matching operations using SikuliX.
 * Provides methods for finding and interacting with images on screen.
 */
public class ImageMatcher {
    
    private static final Logger logger = LoggerFactory.getLogger(ImageMatcher.class);
    private final Screen screen;
    private double similarity;
    
    /**
     * Constructor with default similarity threshold.
     */
    public ImageMatcher() {
        this.screen = new Screen();
        this.similarity = 0.8; // Default similarity
    }
    
    /**
     * Constructor with custom similarity threshold.
     * @param similarity Similarity threshold (0.0 to 1.0)
     */
    public ImageMatcher(double similarity) {
        this.screen = new Screen();
        this.similarity = similarity;
    }
    
    /**
     * Wait for an image to appear on screen.
     * @param imagePath Path to the image file
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return Match object if found, null otherwise
     */
    public Match waitForImage(String imagePath, int timeoutSeconds) {
        logger.info("Waiting for image: {}", imagePath);
        
        if (!new File(imagePath).exists()) {
            logger.error("Image file does not exist: {}", imagePath);
            return null;
        }
        
        try {
            Match match = screen.wait(imagePath, timeoutSeconds);
            if (match != null) {
                logger.info("Image found at location: ({}, {})", match.getX(), match.getY());
            }
            return match;
        } catch (FindFailed e) {
            logger.warn("Image not found within {} seconds: {}", timeoutSeconds, e.getMessage());
            return null;
        }
    }
    
    /**
     * Check if an image exists on screen.
     * @param imagePath Path to the image file
     * @return true if image is found, false otherwise
     */
    public boolean imageExists(String imagePath) {
        logger.debug("Checking if image exists: {}", imagePath);
        
        if (!new File(imagePath).exists()) {
            logger.error("Image file does not exist: {}", imagePath);
            return false;
        }
        
        try {
            Match match = screen.find(imagePath);
            return match != null;
        } catch (FindFailed e) {
            logger.debug("Image not found: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Find an image on screen and return its location.
     * @param imagePath Path to the image file
     * @return Match object if found, null otherwise
     */
    public Match findImage(String imagePath) {
        logger.info("Searching for image: {}", imagePath);
        
        if (!new File(imagePath).exists()) {
            logger.error("Image file does not exist: {}", imagePath);
            return null;
        }
        
        try {
            Match match = screen.find(imagePath);
            if (match != null) {
                logger.info("Image found at location: ({}, {})", match.getX(), match.getY());
            }
            return match;
        } catch (FindFailed e) {
            logger.warn("Image not found: {}", e.getMessage());
            return null;
        }
    }
    
    /**
     * Click on an image when it appears on screen.
     * @param imagePath Path to the image file
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return true if click was successful, false otherwise
     */
    public boolean clickImage(String imagePath, int timeoutSeconds) {
        logger.info("Attempting to click image: {}", imagePath);
        
        Match match = waitForImage(imagePath, timeoutSeconds);
        if (match != null) {
            try {
                match.click();
                logger.info("Successfully clicked image at location: ({}, {})", match.getX(), match.getY());
                return true;
            } catch (Exception e) {
                logger.error("Failed to click image: {}", e.getMessage());
                return false;
            }
        }
        
        logger.warn("Cannot click image - image not found");
        return false;
    }
    
    /**
     * Set the similarity threshold for image matching.
     * @param similarity Similarity threshold (0.0 to 1.0)
     */
    public void setSimilarity(double similarity) {
        if (similarity < 0.0 || similarity > 1.0) {
            logger.warn("Similarity value {} is out of range [0.0, 1.0], using default 0.8", similarity);
            this.similarity = 0.8;
        } else {
            this.similarity = similarity;
            logger.info("Similarity threshold set to: {}", similarity);
        }
    }
    
    /**
     * Get the current similarity threshold.
     * @return Current similarity threshold
     */
    public double getSimilarity() {
        return similarity;
    }
    
    /**
     * Get the Screen instance.
     * @return Screen instance
     */
    public Screen getScreen() {
        return screen;
    }
}

