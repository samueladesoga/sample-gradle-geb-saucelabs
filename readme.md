[![Build Status](https://snap-ci.com/geb/geb-example-gradle/branch/master/build_image)](https://snap-ci.com/geb/geb-example-gradle/branch/master)

# Description

This is an example of incorporating Geb into a Gradle build. It shows the use of Spock and JUnit 4 tests.

The build is setup to work with Firefox, Chrome and PhantomJS. Have a look at the `build.gradle` and the `src/test/resources/GebConfig.groovy` files.

# Usage

The following commands will launch the test in saucelabs with chrome:

./gradlew chromeMacTest -DbrowserName=chrome -Dplatform=MAC
    


Set the following in .profile on your home folder i.e ~/.profile


 export SAUCE_USERNAME=xxxxxx
 export SAUCE_ACCESS_KEY=eb866e62-770e-40d0-a7b2-d17522d48c15

Replace `./gradlew` with `gradlew.bat` in the above examples if you're on Windows.

# Questions and issues

Please ask questions on [Geb user mailing list](http://xircles.codehaus.org/lists/user@geb.codehaus.org) and raise issues in [Geb issue tracker](https://jira.codehaus.org/browse/GEB).