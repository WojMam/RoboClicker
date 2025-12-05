package com.roboclicker.pages;

import com.roboclicker.config.Config;
import com.roboclicker.util.ImageMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main page object representing the primary application interface.
 * Contains all UI actions for the main application window.
 */
public class MainPage extends BasePage {
    
    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);
    
    /**
     * Constructor that initializes the MainPage with an ImageMatcher.
     * @param imageMatcher ImageMatcher instance to use for image operations
     */
    public MainPage(ImageMatcher imageMatcher) {
        super(imageMatcher);
    }
    
    /**
     * Click the Games tab button.
     * This action clicks on the games tab to navigate to the games section.
     * @return true if the games tab was successfully clicked, false otherwise
     */
    public boolean clickGamesTab() {
        logger.info("Executing action: Click Games Tab");
        return imageMatcher.clickImage(Config.BUTTON_IMAGE_1, Config.TIMEOUT_SECONDS);
    }
    
    /**
     * Open the Vanguard page.
     * This action clicks on the Vanguard thumbnail to open the Vanguard game page.
     * @return true if the Vanguard page was successfully opened, false otherwise
     */
    public boolean openVanguardPage() {
        logger.info("Executing action: Open Vanguard Page");
        return imageMatcher.clickImage(Config.BUTTON_IMAGE_2, Config.TIMEOUT_SECONDS);
    }
    
    /**
     * Open the WoW (World of Warcraft) tab.
     * This action clicks on the WoW tab to navigate to World of Warcraft section.
     * @return true if the WoW tab was successfully opened, false otherwise
     */
    public boolean openWoWTab() {
        logger.info("Executing action: Open WoW Tab");
        return imageMatcher.clickImage(Config.BUTTON_IMAGE_3, Config.TIMEOUT_SECONDS);
    }
    
    /**
     * Open the configuration gear/settings.
     * This action clicks on the configuration/settings gear icon to open settings.
     * @return true if the configuration gear was successfully clicked, false otherwise
     */
    public boolean openConfigurationGear() {
        logger.info("Executing action: Open Configuration Gear");
        return imageMatcher.clickImage(Config.BUTTON_IMAGE_4, Config.TIMEOUT_SECONDS);
    }
}

