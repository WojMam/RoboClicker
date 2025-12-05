# RoboClicker

A Java-based automation testing project using SikuliX for image-based UI automation. This project demonstrates how to detect and interact with UI elements using image recognition.

## Project Overview

RoboClicker is designed to automate UI interactions using image recognition capabilities provided by SikuliX. The project follows clean code and clean architecture principles, making it easy to extend and maintain.

## Features

- **Pure Java**: No Python or Robot Framework dependencies
- **SikuliX Integration**: Advanced image recognition using SikuliX Java library
- **JUnit 5**: Modern testing framework for test execution
- **Clean Architecture**: Well-organized project structure following best practices
- **Extensible Design**: Easy to add new tests and functionality
- **Comprehensive Logging**: SLF4J logging for debugging and monitoring

## Prerequisites

- **Java JDK 11** or higher
- **Maven 3.6+**
- **SikuliX** (automatically downloaded via Maven)

## Project Structure

```
RoboClicker/
├── battlenetAssets/          # Image assets for button detection
│   ├── 1_button.png
│   ├── 2_thumbnail.png
│   ├── 3_button.png
│   └── 4_button.png
├── src/
│   ├── main/java/com/roboclicker/
│   │   ├── config/
│   │   │   └── Config.java          # Configuration constants
│   │   └── util/
│   │       └── ImageMatcher.java    # Image matching utility
│   └── test/java/com/roboclicker/
│       ├── ButtonDetectionTest.java  # Individual button detection tests
│       └── AllButtonDetectionTest.java # Comprehensive test
├── pom.xml                   # Maven project configuration
├── .gitignore
└── README.md
```

## Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd RoboClicker
```

### 2. Build the Project

```bash
mvn clean install
```

This will:
- Download all dependencies (including SikuliX)
- Compile the source code
- Run all tests

### 3. Run Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ButtonDetectionTest

# Run tests with specific tags
mvn test -Dtest=ButtonDetectionTest -Dgroups=sikuli
```

## Configuration

### Image Paths

Image paths are configured in `src/main/java/com/roboclicker/config/Config.java`:

```java
public static final String IMAGES_DIR = PROJECT_ROOT + File.separator + "battlenetAssets";
public static final String BUTTON_IMAGE_1 = IMAGES_DIR + File.separator + "1_button.png";
```

### Test Settings

Modify constants in `Config.java` to adjust:

- `TIMEOUT_SECONDS`: Maximum wait time for image detection (default: 10 seconds)
- `SIKULI_SIMILARITY`: Image matching similarity threshold (default: 0.8)
- `RETRY_COUNT`: Number of retry attempts (default: 3)

## Usage

### Basic Image Detection

```java
import com.roboclicker.util.ImageMatcher;
import com.roboclicker.config.Config;

ImageMatcher matcher = new ImageMatcher();
Match match = matcher.waitForImage(Config.BUTTON_IMAGE_1, 10);
if (match != null) {
    System.out.println("Image found at: " + match.getX() + ", " + match.getY());
}
```

### Clicking on Images

```java
ImageMatcher matcher = new ImageMatcher();
boolean clicked = matcher.clickImage(Config.BUTTON_IMAGE_1, 10);
if (clicked) {
    System.out.println("Successfully clicked the button");
}
```

### Custom Similarity Threshold

```java
ImageMatcher matcher = new ImageMatcher(0.9); // 90% similarity
Match match = matcher.findImage(Config.BUTTON_IMAGE_1);
```

## Test Classes

### ButtonDetectionTest

Tests for detecting individual button images:
- `testDetectButtonImage1()` - Detects button image 1
- `testDetectButtonImage2()` - Detects button image 2
- `testDetectButtonImage3()` - Detects button image 3
- `testDetectButtonImage4()` - Detects button image 4
- `testImageMatcherUtility()` - Tests utility methods

### AllButtonDetectionTest

Comprehensive test that attempts to detect all button images in sequence.

## Writing New Tests

### Creating a New Test Class

1. Create a new test class in `src/test/java/com/roboclicker/`:

```java
package com.roboclicker;

import com.roboclicker.config.Config;
import com.roboclicker.util.ImageMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sikuli.script.Match;

import static org.junit.jupiter.api.Assertions.*;

public class MyNewTest {
    private ImageMatcher imageMatcher;
    
    @BeforeEach
    void setUp() {
        imageMatcher = new ImageMatcher(Config.SIKULI_SIMILARITY);
    }
    
    @Test
    void testMyImage() {
        Match match = imageMatcher.waitForImage(Config.BUTTON_IMAGE_1, 10);
        assertNotNull(match, "Image should be found");
    }
}
```

2. Run the test:
```bash
mvn test -Dtest=MyNewTest
```

## Best Practices

1. **Image Assets**: Store all image assets in `battlenetAssets/` directory
2. **Configuration**: Use `Config.java` for all configurable values
3. **Utilities**: Use `ImageMatcher` class for image operations
4. **Logging**: Use SLF4J logger for debugging
5. **Error Handling**: Always check for null returns from image matching methods
6. **Timeouts**: Set appropriate timeouts based on expected UI response times

## Troubleshooting

### SikuliX Issues

- **Problem**: SikuliX not working
  - **Solution**: Ensure Java 11+ is installed
  - **Solution**: Check that Maven dependencies are downloaded correctly
  - **Solution**: Verify images exist in `battlenetAssets/` directory

### Image Not Found

- **Problem**: Tests fail with "Image not found"
  - **Solution**: Verify image file exists and path is correct
  - **Solution**: Adjust similarity threshold (lower value = more lenient matching)
  - **Solution**: Ensure the image is visible on screen when test runs
  - **Solution**: Check image format (PNG recommended)

### Build Issues

- **Problem**: Maven build fails
  - **Solution**: Ensure Maven 3.6+ is installed: `mvn -version`
  - **Solution**: Check Java version: `java -version` (should be 11+)
  - **Solution**: Clean and rebuild: `mvn clean install`

## Dependencies

- **SikuliX 2.0.5**: Image recognition library
- **JUnit 5.10.0**: Testing framework
- **SLF4J 1.7.36**: Logging framework

## Contributing

When adding new features:
1. Follow the existing project structure
2. Maintain clean code principles
3. Add appropriate JavaDoc comments
4. Update this README if needed
5. Test your changes thoroughly

## License

[Specify your license here]

## Support

For issues and questions, please [create an issue in the repository].

