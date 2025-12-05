package com.roboclicker.util;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
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
     * Wait for an image to appear on screen with adaptive similarity matching.
     * Tries multiple similarity thresholds to handle resolution/scale changes.
     * @param imagePath Path to the image file
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return Match object if found, null otherwise
     */
    public Match waitForImage(String imagePath, int timeoutSeconds) {
        return waitForImageWithAdaptiveSimilarity(imagePath, timeoutSeconds);
    }
    
    /**
     * Wait for an image with adaptive similarity - tries multiple thresholds.
     * This makes detection more robust when window resolution changes.
     * Uses a smart strategy: quick find() checks first, then wait() if needed.
     * @param imagePath Path to the image file
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return Match object if found, null otherwise
     */
    private Match waitForImageWithAdaptiveSimilarity(String imagePath, int timeoutSeconds) {
        logger.info("Waiting for image with adaptive similarity: {}", imagePath);
        
        if (!new File(imagePath).exists()) {
            logger.error("Image file does not exist: {}", imagePath);
            return null;
        }
        
        // Try with progressively lower similarity thresholds
        // This handles cases where images are scaled down or resolution changes
        double[] similarityLevels = {similarity, 0.7, 0.6, 0.5, 0.4};
        
        long startTime = System.currentTimeMillis();
        long timeoutMillis = timeoutSeconds * 1000L;
        
        // First, try quick find() checks with all similarity levels (non-blocking)
        // This is much faster than wait() and helps identify the right similarity quickly
        logger.debug("Performing quick find() checks with all similarity levels");
        for (double sim : similarityLevels) {
            try {
                Pattern pattern = new Pattern(imagePath);
                pattern.similar((float) sim);
                Match match = screen.find(pattern);
                if (match != null) {
                    logger.info("Image found immediately at location: ({}, {}) with similarity: {}", 
                        match.getX(), match.getY(), sim);
                    return match;
                }
            } catch (FindFailed e) {
                // Continue to next similarity level
            }
            
            // Check if we've exceeded total timeout
            if (System.currentTimeMillis() - startTime > timeoutMillis) {
                logger.warn("Timeout exceeded during quick find checks");
                return null;
            }
        }
        
        // If quick find didn't work, use wait() with remaining time
        // Calculate remaining time
        long elapsed = System.currentTimeMillis() - startTime;
        long remainingTime = Math.max(1, (timeoutMillis - elapsed) / 1000); // Convert to seconds
        
        logger.debug("Quick find failed, using wait() with remaining {} seconds", remainingTime);
        
        // Try wait() with each similarity level, but use shorter timeouts per attempt
        // This prevents spending too much time on wrong similarity levels
        int timePerAttempt = Math.max(1, (int) (remainingTime / similarityLevels.length));
        
        for (double sim : similarityLevels) {
            // Check if we still have time
            if (System.currentTimeMillis() - startTime > timeoutMillis) {
                logger.warn("Timeout exceeded");
                return null;
            }
            
            logger.debug("Trying wait() with similarity: {} (timeout: {}s)", sim, timePerAttempt);
            
            try {
                Pattern pattern = new Pattern(imagePath);
                pattern.similar((float) sim);
                
                Match match = screen.wait(pattern, timePerAttempt);
                if (match != null) {
                    logger.info("Image found at location: ({}, {}) with similarity: {}", 
                        match.getX(), match.getY(), sim);
                    return match;
                }
            } catch (FindFailed e) {
                logger.debug("Image not found with similarity {}: {}", sim, e.getMessage());
                // Continue to next similarity level
            }
        }
        
        logger.warn("Image not found within {} seconds with any similarity threshold", timeoutSeconds);
        return null;
    }
    
    /**
     * Check if an image exists on screen with adaptive similarity.
     * @param imagePath Path to the image file
     * @return true if image is found, false otherwise
     */
    public boolean imageExists(String imagePath) {
        logger.debug("Checking if image exists: {}", imagePath);
        
        if (!new File(imagePath).exists()) {
            logger.error("Image file does not exist: {}", imagePath);
            return false;
        }
        
        // Try with multiple similarity levels
        double[] similarityLevels = {similarity, 0.7, 0.6, 0.5};
        
        for (double sim : similarityLevels) {
            try {
                Pattern pattern = new Pattern(imagePath);
                pattern.similar((float) sim);
                Match match = screen.find(pattern);
                if (match != null) {
                    logger.debug("Image found with similarity: {}", sim);
                    return true;
                }
            } catch (FindFailed e) {
                // Continue to next similarity level
            }
        }
        
        logger.debug("Image not found with any similarity threshold");
        return false;
    }
    
    /**
     * Find an image on screen and return its location with adaptive similarity.
     * @param imagePath Path to the image file
     * @return Match object if found, null otherwise
     */
    public Match findImage(String imagePath) {
        logger.info("Searching for image: {}", imagePath);
        
        if (!new File(imagePath).exists()) {
            logger.error("Image file does not exist: {}", imagePath);
            return null;
        }
        
        // Try with multiple similarity levels
        double[] similarityLevels = {similarity, 0.7, 0.6, 0.5, 0.4};
        
        for (double sim : similarityLevels) {
            try {
                Pattern pattern = new Pattern(imagePath);
                pattern.similar((float) sim);
                Match match = screen.find(pattern);
                if (match != null) {
                    logger.info("Image found at location: ({}, {}) with similarity: {}", 
                        match.getX(), match.getY(), sim);
                    return match;
                }
            } catch (FindFailed e) {
                logger.debug("Image not found with similarity {}: {}", sim, e.getMessage());
                // Continue to next similarity level
            }
        }
        
        logger.warn("Image not found with any similarity threshold");
        return null;
    }
    
    /**
     * Click on an image when it appears on screen with adaptive similarity.
     * @param imagePath Path to the image file
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return true if click was successful, false otherwise
     */
    public boolean clickImage(String imagePath, int timeoutSeconds) {
        logger.info("Attempting to click image with adaptive similarity: {}", imagePath);
        
        Match match = waitForImageWithAdaptiveSimilarity(imagePath, timeoutSeconds);
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
        
        logger.warn("Cannot click image - image not found with any similarity threshold");
        return false;
    }
    
    /**
     * Wait for an image with retry logic and multiple similarity attempts.
     * This is the most robust method for finding images that may be scaled.
     * @param imagePath Path to the image file
     * @param timeoutSeconds Maximum time to wait in seconds
     * @param maxRetries Maximum number of retry attempts
     * @return Match object if found, null otherwise
     */
    public Match waitForImageWithRetry(String imagePath, int timeoutSeconds, int maxRetries) {
        logger.info("Waiting for image with retry logic: {} (max retries: {})", imagePath, maxRetries);
        
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            logger.debug("Attempt {}/{} to find image", attempt, maxRetries);
            
            Match match = waitForImageWithAdaptiveSimilarity(imagePath, timeoutSeconds);
            if (match != null) {
                logger.info("Image found on attempt {}", attempt);
                return match;
            }
            
            if (attempt < maxRetries) {
                try {
                    Thread.sleep(500); // Brief pause between retries
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.warn("Retry interrupted");
                    break;
                }
            }
        }
        
        logger.warn("Image not found after {} retry attempts", maxRetries);
        return null;
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

