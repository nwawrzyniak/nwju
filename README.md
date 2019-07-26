# nwawsoft-java-util
A package with functions and data structures that extend the Java programming language.

Data structures include List, Stack, Queue, Tree, Graph and more.

Provides a ton of additional functions for native data types (mainly char and String).

This is the go-to library to include in any project if you want to start fresh with a lot of tools and up your sleeves.

---

To add this library to your project you have a few options:

Method 1) Adding ```nwawsoft-java-util``` as a git submodule:

```git submodule add https://github.com/nwawrzyniak/nwawsoft-java-util lib/nwawsoft-java-util```

Do not forget to load the contents of the submodule after cloning, for example with 

```git submodule update --init --recursive```.

From time to time its also worth considering updating the library. If you want to update **all** git submodules use 

```git submodule update --recursive --remote``` and ```git push```.

Method 2) Adding ```nwawsoft-java-util``` via Maven by adding the following snippets it to your ```pom.xml```:

```
<project>
...
  <repositories>
    <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
    </repository>
  </repositories>
  
  <dependencies>
    <dependency>
      <groupId>com.github.nwawrzyniak</groupId>
      <artifactId>nwawsoft-java-util</artifactId>
      <version>master-SNAPSHOT</version> <!-- or some other version if you want a specific one -->
    </dependency>
  </dependencies>
...
</project>
```

Method 3) Downloading the source files and putting them somewhere in your project structure. This method will not keep the library up-to-date in case of updates.

---

Requests for functions that are general enough to be included in a standard library are welcome and should be posted as an [Issue](https://github.com/nwawrzyniak/nwawsoft-java-util/issues).

This code mess is a mix of fresh, clean, well-documented code and over 10 year old school project snippets. I do not take any warranty for the usage of this library, though I intend to maintain this library as well as possible. Coding style may vary in quality and comments and documentation may vary in quality and existance, mostly depening on the when the code was written. Currently the library is getting a complete overhaul.
