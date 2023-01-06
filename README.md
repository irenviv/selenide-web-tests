# selenide-web-tests
Selenide (web-ui framework)

The current project includes:
- Selenide
- Java 11
- Junit 5
- Gradle
- Allure Report

test website: https://rozetka.com.ua/

The project contains tests in Selenide-style page objects with assertions inside.

Test Results are introduced by Allure Report

Run tests in different browsers:
./gradlew chrome
./gradlew firefox
./gradlew ie
./gradlew edge
./gradlew safari
or 
gradle clean test chrome

./gradlew allureServe - generate report
