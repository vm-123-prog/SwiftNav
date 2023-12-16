


<p align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./Logo.png">
    <img src="./Logo.png" height=200px>
  </picture>
</p>

---

<h3 align="center">
Official website: https://swiftnav.netlify.com
</h3>

<h3 align="center">
Official Video: https://youtu.be/gFU9TikSTM0
</h3>

# SwiftNav

## Introduction

SwiftNav is an acronym representing **Seamless Wayfinding and Integrated Fast Tracking**. Swift Nav, an advanced Android application distinguished by its modern and intuitive user interface, serves as a sophisticated tool dedicated to the meticulous exploration of reviews related to various businesses or local establishments. With a focus on effectiveness, this application seamlessly integrates cutting-edge technology to enhance the user experience, providing a comprehensive platform for users to delve into reviews and feedback about a diverse range of businesses and local shops. Its user-friendly design and advanced features make it a valuable resource for those seeking insightful information to inform their decisions and experiences in navigating the local business landscape.This Android application currently encompasses three distinct features:

- Geocoding, a pivotal feature within the application, is a sophisticated process that involves the extraction of precise geographic coordinates and comprehensive reviews associated with a given location, all facilitated seamlessly through the input of its designated name. This functionality not only enhances the accuracy of location-based information but also contributes to a more enriched and integrated user experience within the application.

- Reverse Geocoding, a notable feature incorporated within the application's framework, is a sophisticated procedure that entails retrieving the name and comprehensive reviews associated with a specific geographical location based on its coordinates. This functionality contributes significantly to the application's capability to provide users with detailed and contextual information, elevating the overall user experience.

- Business Search, SwiftNav revolutionizes business searches with unparalleled ease. Users simply input the business name and location, seamlessly unlocking a treasure trove of results tailored to their query. Whether seeking petrol stations, cinemas, or any other business type, the app swiftly harnesses its advanced search capabilities to pinpoint relevant establishments around the specified location. The intuitive design ensures a hassle-free experience, empowering users to effortlessly discover and navigate diverse business options within their chosen vicinity. SwiftNav redefines convenience in business exploration, delivering tailored results at the user's fingertips.


## Understanding Application

### Splash Page

<p align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./SwiftNav App Images/Splash Screen.jpg">
    <img src="./SwiftNav App Images/Splash Screen.jpg" height=500px>
  </picture>
</p>

Upon initiating the application, users are greeted by a brief splash page that persists for a duration of three seconds. This initial screen prominently displays essential elements, including the SwiftNav logo, the application's name, and the name of its creator.

### Home Page

<p align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./SwiftNav App Images/Menu Page.jpg">
    <img src="./SwiftNav App Images/Menu Page.jpg" height=500px>
  </picture>
</p>

Within the visual layout of this page, you will encounter the distinctive presence of the **SwiftNav logo**, serving as a visually cohesive representation of the application. Complementing this branding display are three prominently positioned buttons, each labeled with its designated function: **Geocoding**, **Reverse Geocoding** and **Business Search**. These buttons, thoughtfully integrated into the interface, stand as intuitive entry points to access specific functionalities, providing users with a **streamlined and interactive experience** as they navigate the diverse features offered by the **SwiftNav application**.


### Geocoding Page

<p align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./SwiftNav App Images/GeoCoding.jpg">
    <img src="./SwiftNav App Images/GeoCoding.jpg" height=500px>
  </picture>
</p>

Within the confines of this page, you will discover a prominently featured **search bar**, meticulously designed to facilitate the input of addresses for geocoding purposes. Situated beneath the search bar is a **dedicated search button**, which promptly initiates an internet search, yielding precise results aligned with the entered address. The presented results encompass crucial details such as **Latitude, Longitude, City, Postal Code, State, Country, and Know More button for enhanced navigation in Google Maps**. Adding to its **user-friendly functionality**, the page incorporates a built-in feature enabling the **effortless copying** of coordinates to the clipboard, enhancing the overall versatility and ease of use for the **SwiftNav application**.


### Reverse Geocoding

<p align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./SwiftNav App Images/Reverse Geocoding.jpg">
    <img src="./SwiftNav App Images/Reverse Geocoding.jpg" height=500px>
  </picture>
</p>

In a parallel fashion, the reverse geocoding functionality on this page is seamlessly integrated to facilitate a **user-friendly experience**. A dedicated **search bar** awaits user input of geographical coordinates, serving as the initial step in this process. Positioned just beneath, a **responsive search button** promptly initiates an internet search, culminating in the retrieval of precise information corresponding to the provided coordinates. The results, thoughtfully presented, encompass essential details such as the **location's name, city, Postal Code, State, Country and a convenient Know More button for comprehensive exploration in Google Maps**. Notably, this page also incorporates a built-in feature allowing users to **effortlessly copy** the address to the clipboard, adding an extra layer of convenience to the reverse geocoding experience within the **SwiftNav application**.


### Business Search

<p align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./SwiftNav App Images/Business Search.jpg">
    <img src="./SwiftNav App Images/Business Search.jpg" height=500px>
  </picture>
</p>

**SwiftNav's business search functionality** is a sophisticated tool designed to elevate user experience through its intuitive and advanced features. Users are empowered to effortlessly explore various business categories **by entering both the business name and a specific location**. This **dual-input mechanism** ensures precise and tailored results. The application employs cutting-edge algorithms to swiftly identify and present relevant establishments, be it petrol stations, cinemas, or any specified business type, within the designated geographical area.


## Programmatical Aspects

This app is created in Android Studio Bumblebee | 2021.1.1 (Jan 2022). To see the app programming and app UI(stored in .xml), Android Studio must be installed.

Download Links for Android Studio Bumblebee 2021.1.1 Patch 3:

- [Windows(64-bit)](https://redirector.gvt1.com/edgedl/android/studio/install/2021.1.1.23/android-studio-2021.1.1.23-windows.exe)
- [Mac (Apple Silicon)](https://redirector.gvt1.com/edgedl/android/studio/install/2021.1.1.23/android-studio-2021.1.1.23-mac_arm.dmg)
- [Mac (Intel)](https://redirector.gvt1.com/edgedl/android/studio/install/2021.1.1.23/android-studio-2021.1.1.23-mac.dmg)
- [ChromeOS](https://redirector.gvt1.com/edgedl/android/studio/install/2021.1.1.23/android-studio-2021.1.1.23-cros.deb)


Project files are stored in **Project** folder.

Let us first understand the **project structural hierarchy**:

- app
    - manifests
        - AndroidManifest.xml
    - java
        - com.example.swiftnav
            - CustomAdapter
            - MainActivity
            - MainActivity2
            - MainActivity3
            - MainActivity4
            - MainActivity5
        - com.example.swiftnav (androidTest)
        - com.example.swiftnav (test)
    - java (generated)
    - res
        - drawable
        - layout
            - activity_main.xml
            - activity_main2.xml
            - activity_main3.xml
            - activity_main4.xml
            - activity_main5.xml
        - mipmap
        - values
    - res (generated)
- Gradle Scripts

## Project Default Files Changes

Several files are configured according to project requirements, all of them are mentioned here:

- AndroidManifest.xml

```
    <uses-permission android:name="android.permission.INTERNET" />
```
The following line was added, as there was Internet Requirement for the project

- build.gradle(Module)

```
    implementation 'com.amitshekhar.android:android-networking:1.0.2'
    implementation 'com.android.support:multidex:1.0.3'
    implementation 'com.google.android.material:material:<version>'
```
These modifications were made to enchance the UI and add the Networking functionality to the App

- gradle.properties

```
android.enableJetifier=true
```
This makes the app more Fast

- settings.gradle(Project Settings)

```
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}
```
Adding these, make the app compatible to Material Design 

## Issues

Business Search Recycler View item's not getting cleared, while staying on same page and posting another request


## Tools Used

- Android Studio Bumblebee 2021.1.1.23(Project)
- [Figma(Logo)](https://www.figma.com/)
- [JSON Viewer(For Viewing JSON response)](https://jsonviewer.stack.hu/)

# Author

## Vaahin Mevada

- [Youtube](https://www.youtube.com/@VaahinMevada)
- [Website](https://vaahinmevada.netlify.app/)
- [Github](https://github.com/vm-123-prog)
- [LinkedIn](https://in.linkedin.com/in/vaahin-mevada-641bb8285)

