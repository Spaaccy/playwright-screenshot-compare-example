## Prerequisites

- **JDK 8+** - Java Development Kit
- **IntelliJ IDEA** - Recommended IDE for development
- **Allure** - For test reporting integration

# Playwright Screenshot Compare

A Java library for visual regression testing with advanced screenshot comparison capabilities.

## Installation
 
1. Download JAR file from our repo and save it to your projects "src/main/resources"

2. Add the JAR to your project:

### Maven
```xml
<dependency>
    <groupId>com.screenshots</groupId>
    <artifactId>playwright-screenshot-compare-tool</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/src/main/resources/playwright-screenshot-compare-1.0.0.jar</systemPath>
</dependency>
```

### Gradle
```gradle
dependencies {
    implementation files('lib/playwright-screenshot-compare-1.0.0.jar')
}
```

## Basic Usage

### With Threshold
```java
// Allow 5% difference
ComparisonResult result = PlaywrightScreenshotCompare.compare(
    "expected.png", 
    "actual.png", 
    0.05
);
```

### Ignore Colors (Grayscale)
```java
ComparisonResult result = PlaywrightScreenshotCompare.compareIgnoringColors(
    "expected.png", 
    "actual.png"
);
```

### Ignore Transparent Areas
```java
ComparisonResult result = PlaywrightScreenshotCompare.compareIgnoringTransparency(
    "expected.png", 
    "actual.png"
);
```

### Ignore Specific Regions
```java
// Ignore a single region (x, y, width, height)
ComparisonResult result = PlaywrightScreenshotCompare.compareIgnoringRegion(
    "expected.png", 
    "actual.png", 
    0, 0, 100, 50  // Ignore header area
);
```

## Advanced Configuration

```java
import com.screenshot.ComparisonOptions;

ComparisonOptions options = new ComparisonOptions.Builder()
        .threshold(0.1)                                    // 10% tolerance (0.0-1.0)
        .ignoreAntialiasing(false)                         // Don't ignore anti-aliasing
        .ignoreColors(true)                                // Convert to grayscale
        .diffColor(255, 0, 0)                             // Red color for differences (RGB)
        .alpha(0.2)                                       // 20% transparency for diff overlay
        .saveDiff(true)                                   // Save difference image
        .diffPrefix("visual-diff")                        // Custom filename prefix
        .useTransparencyAsMask(true)                      // Ignore transparent areas
        .maskMode(MaskMode.EXCLUDE)                       // How to handle masked regions
        .addMaskRegion(0, 0, 100, 50)                     // Add single mask region
        .addMaskRegion(new MaskRegion(200, 100, 150, 75)) // Add mask region object
        .build();

ComparisonResult result = PlaywrightScreenshotCompare.compare(
    "expected.png", 
    "actual.png", 
    options
);
```

## Understanding Results

```java
ComparisonResult result = PlaywrightScreenshotCompare.compare("img1.png", "img2.png");

// Check if images match
if (result.isMatches()) {
    System.out.println("Images match!");
} else {
    System.out.println("Difference: " + result.getEffectiveDiffPercentage() + "%");
    System.out.println("Diff image saved at: " + result.getDiffPath());
}

// Error handling
if (result.hasError()) {
    System.err.println("Error: " + result.getError());
}
```

## Allure Integration

The library automatically integrates with Allure reporting to provide detailed visual test results.

### What Gets Attached to Allure Reports

- **Expected Image** - The baseline screenshot
- **Actual Image** - The current screenshot being compared
- **Diff Image** - Visual highlighting of differences (if any)
- **Comparison Details** - Numerical results and statistics
- **Masking Information** - Details about ignored regions

### Usage in Tests

```java
@Test
public void testScreenshotComparison() {
    ComparisonResult result = PlaywrightScreenshotCompare.compare(
        "expected.png", 
        "actual.png"
    );
    
    // Allure automatically captures:
    // - Step annotation
    // - All images as attachments
    // - Detailed comparison metrics
    
    Assert.assertTrue("Screenshots should match", result.isMatches());
}
```

### Example Allure Report Output
![image.png](images/image.png)
