# random.org-wrapper

Java wrapper for working with the API [random.org](https://random.org)

### Add to MAVEN
https://jitpack.io/#megoRU/random.org-wrapper

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
	<version>2.0</version>
</dependency>
```

### Example

```java
RandomWrapper randomWrapper = new RandomWrapper(
"****-****-4fdf-8146-*********", //API_KEY
1, // n
0, // min
5, // max
false, // replacement
false); // debugging

Random random = randomWrapper.sendData();
System.out.println(random.getResult().getRandom().getData().get(0)); //Output: [2]
```

