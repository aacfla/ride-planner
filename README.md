`mvn package` to compile to a jar. (We may setup a continuous integration at some point)

## Dev Environment

1. Install [Maven](https://maven.apache.org/download.cgi).

2. Clone this repository.

3. Import/Create Maven project from this existing source.

   IntelliJ:
      1. File > New > Project from existing sources
      2. Open `pom.xml`
      3. Default configuration should be fine
      4. Select a Java SDK. You may have to click the plus and select the jdk directory
      5. Whatever project name
      6. Wait for the project to finish importing and dependencies to finish downloading (any "processes running" should be done)
      7. Open `test/java/Playground.java`. Run > Run, select Playground. The project should compile and the Playground file should be executed

4. To set up credentials on a local machine, add file to:
   * `~/.aws/config` on Linux, macOS, or Unix
   * `C:\Users\[USERNAME]\.aws\config` on Windows
