# Quick Start Guide

## Prerequisites Check

```bash
# Check Java
java -version  # Should be 11+

# Check Maven
mvn -version  # Should be 3.6+
```

## Setup (One-Time)

```bash
# 1. Build the project
mvn clean install

# This will:
# - Download all dependencies (SikuliX, JUnit, etc.)
# - Compile source code
# - Run all tests
```

## Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ButtonDetectionTest
mvn test -Dtest=AllButtonDetectionTest

# Run with verbose output
mvn test -X
```

## Project Structure Quick Reference

- **Configuration**: `src/main/java/com/roboclicker/config/Config.java`
- **Utilities**: `src/main/java/com/roboclicker/util/ImageMatcher.java`
- **Tests**: `src/test/java/com/roboclicker/`
- **Images**: `battlenetAssets/`

## Common Commands

```bash
# Clean build
mvn clean

# Compile only
mvn compile

# Run tests
mvn test

# Package project
mvn package

# Install to local repository
mvn install
```

## Configuration

Edit `src/main/java/com/roboclicker/config/Config.java` to customize:
- Timeout values
- Image paths
- Similarity thresholds

## Troubleshooting Quick Fixes

```bash
# Clean and rebuild
mvn clean install

# Force update dependencies
mvn clean install -U

# Skip tests during build
mvn clean install -DskipTests

# Check dependencies
mvn dependency:tree
```

## Next Steps

1. Review `README.md` for detailed documentation
2. Check `SETUP.md` for installation instructions
3. Read `ARCHITECTURE.md` for architecture details
4. Customize `Config.java` for your environment
5. Add your own tests following existing patterns

