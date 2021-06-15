# TingTong
A ping pong game play the game [here](https://hdfsyu.itch.io/tingtong)
# Compiling/Running/Modding
to compile download the project and in the root of the project type `gradlew.bat desktop:dist` or on linux its gonna be `./gradlew desktop:dist`
to run it go to desktop/build/libs/desktop-1.0.jar
to mod it go into the code and modify it (there should be comments to guide u) then go inside the jar that got compiled and put the "splash.png" file in the META-INF folder and add this to the MANIFEST.MF file: `SplashScreen-Image: splash.png` then start the jar.
