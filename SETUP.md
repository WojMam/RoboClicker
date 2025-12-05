# Setup Guide

## Quick Start

1. **Install Java JDK 11+**
   - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
   - Verify installation: `java -version`

2. **Install Maven 3.6+**
   - Download from [Apache Maven](https://maven.apache.org/download.cgi)
   - Add to PATH
   - Verify installation: `mvn -version`

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run Tests**
   ```bash
   mvn test
   ```

## Detailed Setup

### Java Installation

1. Download Java JDK 11 or higher
2. Install and add to PATH
3. Verify:
   ```bash
   java -version
   javac -version
   ```

### Maven Installation

1. Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to a directory (e.g., `C:\Program Files\Apache\maven`)
3. Add to PATH:
   - Windows: Add `C:\Program Files\Apache\maven\bin` to PATH
   - Linux/Mac: Add to `~/.bashrc` or `~/.zshrc`
4. Verify:
   ```bash
   mvn -version
   ```

### Project Setup

1. Clone or download the project
2. Navigate to project directory
3. Build the project:
   ```bash
   mvn clean install
   ```

This will:
- Download all dependencies (SikuliX, JUnit, etc.)
- Compile source code
- Run all tests

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=ButtonDetectionTest
mvn test -Dtest=AllButtonDetectionTest
```

### Run Tests with Tags

```bash
# Run tests with specific tag
mvn test -Dtest=ButtonDetectionTest -Dgroups=sikuli
```

### Run with Verbose Output

```bash
mvn test -X
```

## Troubleshooting

### Java Issues

**Problem**: `java` command not found
- **Solution**: Add Java to PATH
- **Solution**: Verify installation: `java -version`

**Problem**: Wrong Java version
- **Solution**: Ensure Java 11+ is installed
- **Solution**: Check JAVA_HOME environment variable

### Maven Issues

**Problem**: `mvn` command not found
- **Solution**: Add Maven to PATH
- **Solution**: Verify installation: `mvn -version`

**Problem**: Dependencies not downloading
- **Solution**: Check internet connection
- **Solution**: Verify Maven settings.xml
- **Solution**: Try: `mvn clean install -U` (force update)

### SikuliX Issues

**Problem**: SikuliX not working
- **Solution**: Ensure Java 11+ is installed
- **Solution**: Check Maven dependencies: `mvn dependency:tree`
- **Solution**: Rebuild project: `mvn clean install`

**Problem**: Image recognition not working
- **Solution**: Verify images exist in `battlenetAssets/` directory
- **Solution**: Check image paths in `Config.java`
- **Solution**: Adjust similarity threshold
- **Solution**: Ensure images are visible on screen

### Build Issues

**Problem**: Compilation errors
- **Solution**: Check Java version: `java -version`
- **Solution**: Clean and rebuild: `mvn clean install`
- **Solution**: Check for syntax errors in source files

**Problem**: Tests fail
- **Solution**: Verify images exist and are accessible
- **Solution**: Check that images are visible on screen
- **Solution**: Adjust timeout values in `Config.java`
- **Solution**: Lower similarity threshold for more lenient matching

## Verification

After setup, verify everything works:

```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Build project
mvn clean install

# Run tests
mvn test
```

## IDE Setup

### IntelliJ IDEA

1. Open project: File → Open → Select project directory
2. Maven will automatically import dependencies
3. Run tests: Right-click test class → Run

### Eclipse

1. Import project: File → Import → Maven → Existing Maven Projects
2. Select project directory
3. Run tests: Right-click test class → Run As → JUnit Test

### VS Code

1. Install Java Extension Pack
2. Open project folder
3. Maven dependencies will be automatically downloaded
4. Run tests using JUnit extension

## Next Steps

1. Review `README.md` for detailed documentation
2. Customize `Config.java` for your environment
3. Add your own tests following existing patterns
4. Explore SikuliX documentation for advanced features

