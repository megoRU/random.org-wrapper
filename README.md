# random.org-wrapper

Java wrapper for the [random.org](https://random.org) API.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Maven

Add via [JitPack](https://jitpack.io/#megoRU/random.org-wrapper):

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.megoRU</groupId>
  <artifactId>random.org-wrapper</artifactId>
  <version>2.1.4</version>
</dependency>
```

## Example

```java
public class Main {
    public static void main(String[] args) {
        RandomWrapper randomWrapper = new RandomWrapper(
            "****-****-4fdf-8146-*********", // API key
            1,  // Number of values to generate
            0,  // Minimum value
            5,  // Maximum value
            false, // With replacement
            false  // Debug mode
        );

        Random random = randomWrapper.sendData();
        System.out.println(random.getResult().getRandom().getData().get(0)); // Output: [2]
    }
}
```

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
