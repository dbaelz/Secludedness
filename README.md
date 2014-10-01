Secludedness
================================
Secludedness is a cross-platform game build with [libgdx](libgdx.badlogicgames.com). For further information see my [Webpage/Blog](http://dbaelz.de).

Status
-------------
Secludedness uses [Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html) and it's [current version](VERSION) is 0.1.0! Anything may change and the game is WIP.

Build
-------------
The projects can be build with the integrated Gradle wrapper. See [libgdx project wiki](https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline) for usage of the wrapper.

Signing
-------------
To sign your Android app with gradle use the property [Secludedness.signing](https://github.com/dbaelz/Secludedness/blob/master/build.gradle#L57) and external config files for the keystore informations. [Tim Roes](https://github.com/timroes) wrote a very informative blog post about [handling signing configs](https://www.timroes.de/2013/09/22/handling-signing-configs-with-gradle/) from which the example below was derived.

Note that property _Secludedness.signing_ has to point to the __folder__ of your keystores and not to the filename of the keystore file!

```groovy
android {
  signingConfigs {
    release {
      storeFile file(project.property("Secludedness.signing") + "/release.keystore")
      storePassword "KEYSTORE_PASSWORD"
      keyAlias "KEY_ALIAS_RELEASE"
      keyPassword "KEY_PASSWORD"
    }

    debug {
      storeFile file(project.property("Secludedness.signing") + "/debug.keystore")
      storePassword "android"
      keyAlias "androiddebugkey"
      keyPassword "android"
    }
  }
 
  buildTypes {
    release {
      signingConfig signingConfigs.release
    }

	debug {
      signingConfig signingConfigs.debug
    }
  }
}
```

License
-------------
Libgdx and Secludedness are licensed under the [Apache 2 License](LICENSE). The amazing [music](https://github.com/dbaelz/Secludedness/tree/master/Secludedness-android/assets/music/ozzed) is from [Ozzed](http://ozzed.net) and licensed under [Creative Commons BY-SA 3.0](http://creativecommons.org/licenses/by-sa/3.0).
