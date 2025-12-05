# Architecture Documentation

## Project Structure

The RoboClicker project follows clean architecture principles with clear separation of concerns:

```
RoboClicker/
├── battlenetAssets/              # Image assets (external resources)
│   └── *.png                     # Button images for detection
│
├── src/
│   ├── main/java/com/roboclicker/
│   │   ├── config/               # Configuration layer
│   │   │   └── Config.java       # Centralized configuration
│   │   └── util/                 # Utility layer
│   │       └── ImageMatcher.java # Image matching utilities
│   └── test/java/com/roboclicker/
│       ├── ButtonDetectionTest.java    # Individual tests
│       └── AllButtonDetectionTest.java # Integration tests
│
├── pom.xml                       # Maven project configuration
└── README.md                     # Documentation
```

## Architecture Layers

### 1. Configuration Layer (`com.roboclicker.config`)

**Purpose**: Centralized configuration management

**Components**:
- `Config.java`: Contains all configurable constants
  - Image paths
  - Timeout values
  - Similarity thresholds
  - Screen configuration

**Benefits**:
- Single source of truth for configuration
- Easy to modify without changing code
- Type-safe constants

### 2. Utility Layer (`com.roboclicker.util`)

**Purpose**: Reusable utility classes for common operations

**Components**:
- `ImageMatcher.java`: Image matching and interaction utilities
  - Wait for images
  - Find images
  - Click on images
  - Similarity threshold management

**Benefits**:
- Code reusability
- Abstraction of SikuliX complexity
- Easy to extend with new functionality

### 3. Test Layer (`com.roboclicker`)

**Purpose**: Test classes for automation scenarios

**Components**:
- `ButtonDetectionTest.java`: Individual button detection tests
- `AllButtonDetectionTest.java`: Comprehensive integration tests

**Benefits**:
- Clear test organization
- Easy to add new test scenarios
- Follows JUnit 5 best practices

## Design Principles

### Clean Code
- **Readable**: Clear naming conventions, comprehensive JavaDoc
- **Maintainable**: Modular structure, separation of concerns
- **Testable**: Isolated test cases, reusable utilities

### Clean Architecture
- **Dependency Rule**: Inner layers don't depend on outer layers
- **Separation of Concerns**: Each layer has a single responsibility
- **Open/Closed Principle**: Easy to extend without modification

### SOLID Principles
- **Single Responsibility**: Each class has one clear purpose
- **Open/Closed**: Open for extension, closed for modification
- **Dependency Inversion**: Depend on abstractions, not concretions

## Key Classes

### Config
- **Purpose**: Configuration constants
- **Responsibilities**: 
  - Define image paths
  - Define timeout values
  - Define similarity thresholds
- **Usage**: Static constants accessed throughout the project

### ImageMatcher
- **Purpose**: Image matching operations
- **Responsibilities**:
  - Wait for images on screen
  - Find images on screen
  - Click on images
  - Manage similarity thresholds
- **Dependencies**: SikuliX Screen API

### ButtonDetectionTest
- **Purpose**: Test button detection functionality
- **Responsibilities**:
  - Test individual button detection
  - Verify image matching works correctly
  - Test utility methods
- **Dependencies**: ImageMatcher, Config

## Adding New Features

### Adding a New Utility Method

1. Open `ImageMatcher.java`
2. Add new method following existing patterns
3. Add JavaDoc documentation
4. Add logging for debugging
5. Update tests if needed

### Adding a New Test

1. Create new test class in `src/test/java/com/roboclicker/`
2. Follow JUnit 5 conventions
3. Use `ImageMatcher` for image operations
4. Use `Config` for configuration values
5. Add appropriate tags and display names

### Adding New Configuration

1. Open `Config.java`
2. Add new constant following naming conventions
3. Add JavaDoc comment
4. Update tests if needed

## Dependencies

### Core Dependencies
- **SikuliX 2.0.5**: Image recognition library
- **JUnit 5.10.0**: Testing framework
- **SLF4J 1.7.36**: Logging framework

### Build Tools
- **Maven 3.6+**: Build and dependency management
- **Java 11+**: Programming language

## Best Practices

1. **Configuration**: Always use `Config.java` for configurable values
2. **Utilities**: Use `ImageMatcher` for all image operations
3. **Logging**: Use SLF4J logger for debugging
4. **Documentation**: Add JavaDoc to all public methods
5. **Error Handling**: Always check for null returns
6. **Testing**: Write tests for all new functionality

## Future Enhancements

Potential areas for extension:
- Additional image operations (double-click, right-click, drag)
- Screen capture utilities
- Image comparison utilities
- Configuration file support (YAML/JSON)
- Test reporting enhancements
- Parallel test execution
- CI/CD integration examples

