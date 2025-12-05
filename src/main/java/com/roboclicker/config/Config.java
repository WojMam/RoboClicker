package com.roboclicker.config;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Configuration class for RoboClicker project.
 * Contains all configurable constants and paths.
 */
public class Config {
    
    // Project root directory
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    
    // Image assets directory - located in test resources following Maven conventions
    public static final String IMAGES_DIR = PROJECT_ROOT + File.separator + "src" + 
            File.separator + "test" + File.separator + "resources" + File.separator + "assets";
    
    // Image file paths
    public static final String BUTTON_IMAGE_1 = IMAGES_DIR + File.separator + "1_button.png";
    public static final String BUTTON_IMAGE_2 = IMAGES_DIR + File.separator + "2_thumbnail.png";
    public static final String BUTTON_IMAGE_3 = IMAGES_DIR + File.separator + "3_button.png";
    public static final String BUTTON_IMAGE_4 = IMAGES_DIR + File.separator + "4_button.png";
    
    // Test configuration
    public static final int TIMEOUT_SECONDS = 10;
    public static final int RETRY_COUNT = 3;
    // Lower similarity for better tolerance to resolution/scale changes
    public static final double SIKULI_SIMILARITY = 0.7; // Image matching similarity (0.0 to 1.0)
    // Additional similarity levels for adaptive matching
    public static final double[] ADAPTIVE_SIMILARITY_LEVELS = {0.7, 0.6, 0.5, 0.4, 0.3};
    
    // Screen configuration
    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;
    
    private Config() {
        // Utility class - prevent instantiation
    }
    
    /**
     * Get the project root directory as a Path object.
     * @return Path to project root
     */
    public static Path getProjectRoot() {
        return Paths.get(PROJECT_ROOT);
    }
    
    /**
     * Get the images directory as a Path object.
     * @return Path to images directory
     */
    public static Path getImagesDirectory() {
        return Paths.get(IMAGES_DIR);
    }
    
    /**
     * Verify that the images directory exists.
     * @return true if directory exists, false otherwise
     */
    public static boolean imagesDirectoryExists() {
        File dir = new File(IMAGES_DIR);
        return dir.exists() && dir.isDirectory();
    }
}

