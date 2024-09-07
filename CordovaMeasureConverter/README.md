This project uses Android SDK+Cordova to demonstrate a Hybrid Mobile version of Measure Converter application.

1. Download and install Android Studio with SDK
2. Set up PATH for Android ADK and Gradle and Android build-tools (the below paths may vary)
   SET DEV_HOME=D:/DevApps
   
   SET JAVA_HOME=%DEV_HOME%/jdk1.8.0_144
   
   SET ANDROID_HOME=%DEV_HOME%/Android/sdk
   
   SET GRADLE_HOME=%DEV_HOME%/Android/Android Studio/gradle/gradle-3.2
   
   SET PATH=%PATH%;%JAVA_HOME%/bin;%JAVA_HOME%/jre/bin;%DEV_HOME%/nodejs;%DEV_HOME%/Android/Android Studio/bin;%ANDROID_HOME%/platform-tools;%ANDROID_HOME%/tools;%ANDROID_HOME%/tools/bin;%ANDROID_HOME%/build-tools/26.0.1;%GRADLE_HOME%/bin
   
3. npm install -g cordova
   - NOTE: if you want to install a specific Cordova version, use npm install -g cordova@4.1.1; However, by using a lower version, your hybrid app build may not be running properly on a newer device.
4. cordova create MeasureConverter org.freecode.demo.hybrid.meansureConverter MeasureConverter
5. goto the new Cordova project folder, and run: cordova platform add android
   - if you want to install a particular Android version, use cordova platform add android@5.0.0; However, by using a lower version of android, your hybrid app may look funky on a device running with higher version of OS or with a high resolution.
6. create Single Page Application (with web resources) and put them in the www folder of the project
7. modify config.xml in the project folder based on the application (e.g. contect src, icons, access origin and etc.)
8. cordova build android (Note: if you change your Android SDK versions etc, you may need to do "cordova platform remove/add android" again)
   - NOTE: Gradle must be configured, and ANDROID_HOME must point to the Android SDK folder.
   - NOTE: you need to accept all the sdk licenses before build. use sdkmanager --licenses to review and accept licenses
   - For all screen sizes, you may need to modify AndroidManifest.xml to set for large/medium/small/any.
     eg <supports-screens android:anyDensity="true" android:largeScreens="true" android:normalScreens="true" android:resizeable="true" android:smallScreens="true" android:xlargeScreens="true" />
   Also, you may try to set minifyEnabled, shrinkResources to true and specify proguardFiles to reduce the .apk size.
		...
		buildTypes {
            release {
                signingConfig signingConfigs.release
				minifyEnabled true
                shrinkResources true
				proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            }
        }
		...
   cordova build --release android
   - generate an unsigned release apk

9. use keytool from JDK to generate keystore (https://stackoverflow.com/questions/26449512/how-to-create-a-signed-apk-file-using-cordova-command-line-interface)
   keytool -genkey -v -keystore MeasureConvertor.keystore -alias MeasureConvertor -keyalg RSA -keysize 2048 -validity 10000

    keystore password? : xxxxxxx
    What is your first and last name? :  xxxxxx
    What is the name of your organizational unit? :  xxxxxxxx
    What is the name of your organization? :  xxxxxxxxx
    What is the name of your City or Locality? :  xxxxxxx
    What is the name of your State or Province? :  xxxxx
    What is the two-letter country code for this unit? :  xxx   

10. copy the keystore to the apk folder under <project>\platforms\android\app\build\outputs\apk\release and then run jarsigner from JDK to sign the .apk file
    jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore MeasureConvertor.keystore app-release-unsigned.apk MeasureConvertor
	
11. cordova run android 
   - NOTE: Android Virtual Device must be ready. Creating an AVD from Android Studio is easier; or try the command line(packages may vary): android create avd -n testAVD --package "system-images;android-25;google_apis;x86"

Optional commands:
List emulators: 
	android.exe list avd
Start emulator:
	emulator -avd <emulator name>
Install application package to emulator (need to start the emulator first): NOTE: -r for replacing the existing application from the emulator
	adb install -r <package name.apk>
Stop emulator: (not work??)
	adb kill-server
	adb -s <emulator name> emu kill
Connect to Emulator shell (NOTE: need to start the emulator first)
    adb shell
Copy file from PC to emulator, vice versa (emulator needs to be running)
    adb push c:/temp/test.apk /data/local/tmp/test.apk
	adb pull /data/local/tmp/test.apk c:/temp/test.apk

12. for local web server, the ip address defaults to 10.0.2.2
