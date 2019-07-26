# nwawsoft-java-util
A package with functions and data structures that extend the Java programming language.

Data structures include List, Stack, Queue, Tree, Graph and more.

Provides a ton of additional functions for native data types (mainly char and String).

This is the go-to library to include in any project if you want to start fresh with a lot of tools and up your sleeves.

---
## Adding this library to your project
### Method 1) Adding ```nwawsoft-java-util``` as a ```git submodule```:
```
git submodule add https://github.com/nwawrzyniak/nwawsoft-java-util lib/nwawsoft-java-util
```
Do not forget to load the contents of the submodule after cloning, for example with 
```
git submodule update --init --recursive
```
From time to time its also worth considering updating the library. If you want to update **all** git submodules use 
```
git submodule update --recursive --remote
git commit -am "Updated git submodules."
git push
```
### Method 2) Adding ```nwawsoft-java-util``` via Maven and ```pom.xml```:
Add the following two snippets somewhere between the ```<project>``` and ```</project>``` tag of your ```pom.xml```
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
      <artifactId>nwawsoft-java-util</artifactId>
      <version>master-SNAPSHOT</version> <!-- or some other version if you want a specific one -->
    </dependency>
  </dependencies>
```
### Method 3) Adding ```nwawsoft-java-util``` via ```git subtree```:
```git subtree add --prefix lib/nwawsoft-java-util https://github.com/nwawrzyniak/nwawsoft-java-util.git master --squash```
In that case, you would update the library like this:
```git subtree pull --prefix lib/nwawsoft-java-util https://github.com/nwawrzyniak/nwawsoft-java-util.git master --squash```
### Method 4) Cloning into myProjectRoot/lib/nwawsoft-java-util:
To clone this repository to a smart place in your project structure, assuming you are in a terminal in your project root, make the following calls
```
mkdir lib
cd lib/
git clone https://github.com/nwawrzyniak/nwawsoft-java-util.git
```
To update the library if it was installed this way use 
```
cd lib/nwawsoft-java-util
git pull
```
### Method 5) Downloading the source files manually (no easy updating):
[Dowload as .zip](https://github.com/nwawrzyniak/nwawsoft-java-util/archive/master.zip)

It is recommended to put the files in a new subdirectory inside of ```myProjectRoot/lib/```, preferably called ```nwawsoft-java-util``` to never mix up the contents of your lib directory.

To update the library if it was installed this way you have to redownload the current version via the same link and copy the new files over the old ones.

---
## Improving this library
Requests for functions that are general enough to be included in a standard library are welcome and should be posted as an [Issue](https://github.com/nwawrzyniak/nwawsoft-java-util/issues).

---
## Disclaimer
This code mess is a mix of fresh, clean, well-documented code and over 10 year old school project snippets. I do not take any warranty for the usage of this library, though I intend to maintain this library as well as possible. Coding style may vary in quality and comments and documentation may vary in quality and existance, mostly depening on the when the code was written. Currently the library is getting a complete overhaul and I cannot guarantee that everything will stay 100% backwards compatible.
