# 2D-platformer
2D platformer boss-fighting game created with Java

## Installation and Setup
This tutorial uses Visual Studio Code:
- Java
- JavaFX
- SceneBuilder

### Setting up JDK
1. Go to [Oracle](https://www.oracle.com/java/technologies/downloads/) and click the download button for your operating system
2. Read and accept all licenses and you will soon have the JDK downloaded

### Setting up Visual Studio Code
1. Go to the [download](https://code.visualstudio.com/download) page for VSC
2. Click the download button for your operating system
3. Upon installation of VSC, select the extensions tab on the left-hand side
4. Search for `Extension Pack for Java` and download it
5. Search for `JavaFX Support` and download it

### Setting up JavaFX
1. Go to the [download](https://gluonhq.com/products/javafx/) page for JavaFX
2. Click the download button for the respective package that has Architecture `x64` and Type `SDK` for your operating system
3. Read and accept all licenses
4. Unzip your downloaded zip file and keep track of the unzipped files; you will need it later

### Setting up the project
1. Download the project from GitHub
2. Click the Explorer tab on the top left of Visual Studio Code
3. Click Open Folder and add the project that you just downloaded
4. Press `CTRL+SHIFT+P` to open the command palette and search for `>Java: Configure Java Runtime`
5. Under `Java Version`, select the previously downloaded JDK
6. Navigate through the project until you find the file under the `src` folder called `Start.java`
7. Upon selecting the `Start.java` file in VSC, a tab for `JAVA PROJECTS` should appear on the bottom left of VSC
8. Open the `JAVA PROJECTS` tab, select the project, and hover over `Referenced Libraries`
9. Select the `+` symbol, navigate to your previously downloaded JavaFX files, go to the `lib` folder, and select all of the `.jar` files
10. Navigate to the run option on the top of VSC and select `Add Configuration...`
11. Open the newly created `launch.json` file
12. Between the second set of curly braces, you will find two attributes called `request` and `mainClass`
13. Between those two attributes, add the following attribute to it <br />
`"vmArgs": "-Xms4096m -verbose:gc --module-path \"/path/to/javafx-sdk-20.0.1/lib\" --add-modules javafx.controls,javafx.fxml",`
15. Change the `module-path` to your respective path for the `lib` folder from the previously downloaded JavaFX files
16. Save all files and reload VSC
17. Navigate back to `Start.java` and press F5 to run the project


### Time frame:
May 2022 - July 2022
