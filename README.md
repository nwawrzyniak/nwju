# nwju (nwawsoft-java-util)
A package with functions and data structures that extend the Java programming language.

Data structures include List, Stack, Queue, Tree, Graph and more.

Provides a lot of additional functions for native data types (mainly char and String).

---
## Documentation
You can find the full JavaDoc [on this site](https://www.nwawsoft.com/nwju/docs/).

---
## Adding this library to your project
There are multiple ways to include this library in your project.
### Method 1) Using Maven:
Add the following two snippets somewhere between the `<project>` and `</project>` tag of your `pom.xml`:
```
  <repositories>
    <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
    </repository>
  </repositories>
```
```
  <dependencies>
    <dependency>
      <groupId>com.github.nwawrzyniak</groupId>
      <artifactId>nwju</artifactId>
      <version>v1.0.1</version>
    </dependency>
  </dependencies>
```
### Method 2) Download the .jar (no automatic updates)
The newest version can always be found on the [GitHub Releases page](https://github.com/nwawrzyniak/nwju/releases/latest) or [here, as a direct download](https://github.com/nwawrzyniak/nwju/releases/latest/download/nwju.jar).

### Method 3) Build from source
This can be done via `git clone https://github.com/nwawrzyniak/nwju.git` or by [downloading the source files](https://github.com/nwawrzyniak/nwju/archive/refs/heads/master.zip)

---
## Improving this library
Requests for functions that are general enough to be included in a standard library are welcome and should be [reported as an Issue](https://github.com/nwawrzyniak/nwju/issues).

---
## Disclaimer
I do not take any warranty for the usage of this library, though I intend to maintain this library as well as possible and respond to feedback.
