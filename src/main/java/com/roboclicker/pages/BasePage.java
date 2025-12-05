package com.roboclicker.pages;

import com.roboclicker.util.ImageMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all page objects.
 * Provides common functionality and ImageMatcher instance for all pages.
 */
public abstract class BasePage {
    
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected final ImageMatcher imageMatcher;
    
    /**
     * Constructor that initializes the ImageMatcher.
     * @param imageMatcher ImageMatcher instance to use for image operations
     */
    protected BasePage(ImageMatcher imageMatcher) {
        this.imageMatcher = imageMatcher;
        logger.debug("Initializing page: {}", this.getClass().getSimpleName());
    }
    
    /**
     * Get the ImageMatcher instance.
     * @return ImageMatcher instance
     */
    protected ImageMatcher getImageMatcher() {
        return imageMatcher;
    }
}

