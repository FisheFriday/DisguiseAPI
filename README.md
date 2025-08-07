<p align="center">
	<img src = "https://img.shields.io/badge/Supports-1.8.8%20--%201.21.4-%3Cbrightgreen%3E">
	<img src = "https://img.shields.io/badge/Added%20Up%20To-1.21.7-yellow">
	<img src = "https://img.shields.io/badge/-Library-blue">
	<img src = "https://img.shields.io/badge/-Easy%20to%20use-orange">
</p>

<img src="https://imgur.com/xCRKtdh.png" width="120px" align="right"></img>
# DisguiseAPI
### This is a fork of DisguiseAPI to support up to 1.21.7!
Disguise is an open-source library which manipulates with player skins in Minecraft using NMS.
<br>
The standard Spigot implementation does not provide support for changing skins, so using this API
you can easily control player skins.

### Dependency 

To install this repository, you should follow the next steps:

1. Clone this repository: ``git clone https://github.com/ITSPINGER/DisguiseAPI.git``
2. Enter into the directory folder: ``cd DisguiseAPI``
3. Build the project using Maven: ``mvn clean install``

After the project has finished building, you may now use the project in your projects.

#### Maven
```xml
<dependency>
  <groupId>net.pinger.disguise</groupId>
  <artifactId>API</artifactId>
  <version>1.4.0</version> <!-- At time of writing, 1.4.0 is the latest version. See the pom.xml for the latest version -->
  <scope>provided</scope> <!-- No need for compiling it within the jar since it is already included within the plugin -->
</dependency>
```

#### Gradle
```gradle
dependencies {
    // No need for compiling it within the jar since it is already included within the plugin
    compileOnly 'net.pinger.disguise:API:1.4.0'
}
```

### Documentation

- <a href = "https://itspinger.github.io/DisguiseAPI/">Javadoc:</a> Documentation for the current release 
- <a href = "https://github.com/itspinger/DisguiseAPI/blob/master/CHANGELOG.md">Changelog:</a> Information about the latest updates
- <a href = "https://github.com/itspinger/DisguiseAPI/wiki">User guide:</a> A well written user guide on how to use this library

### License 

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

### Plugins

Down below plugins implementing this library will be mentioned, along with the download link. If you want your plugin to be
included in this list, write me a message on Pinger#5246 (Discord)

- <a href = "https://www.spigotmc.org/resources/disguise.84079/">DisguisePlus</a>
- <a href = "">Hynick</a>

### Fork Info

The reason why this fork was created was because of how a lot of users that bought DisguisePlus or Hynick were mislead to with "constant" updates.
So this is why this fork was created with support up to 1.21.7
I'm happy if the owner saw this and approve the pull request and the users that bough those 2 plugins able to use it in their server.
Anyway, thats all, I won't be updating this to 1.21.8 or more modern versions unless I feel like to.
Note that I do not own the API, so some features might not work. I've approved that 1.21.4 will work, but not for 1.21.5+
### Use this plugin api fork with intent that 1.21.5+ is not tested! Just added to the plugin api.
