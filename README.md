# BEKA Cookware - Test Automation

UI Test automation for [BEKA Cookware](https://www.beka-cookware.com/), covering multilanguage (English + NL), 
functional validation, negative and edge cases

Built with Selenium (Java), Cucumber, TestNG and Maven following the page object model

---

## Tech Stack

| Concern   | Choice   |
|-----------|----------|
| Language  | Java 21  |
| Browser Automation | Selenium 4.x |
| BDD | Cucumber 7.x |
| Test Runner | TestNG via AbstractTestNGCucumberTests |
| Build | Maven |

---

## Prerequisites
- JDK 21+ ('java -version')
- Maven 3.9+ ('mvn -version')
- Google Chrome and Edge are supported

## Setup
git clone <https://github.com/kanchangupta12-tech/Beka-Cookware.git>
cd Beka-Cookware
mvn clean install -DskipTests

