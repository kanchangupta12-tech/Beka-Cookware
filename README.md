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
- JDK 21+ (`java -version`)
- Maven 3.9+ (`mvn -version`)
- Google Chrome and Edge are supported

## Setup
```bash
git clone <https://github.com/kanchangupta12-tech/Beka-Cookware.git>
cd Beka-Cookware
mvn clean install -DskipTests
```

## Running tests
```bash
mvn test
```
---

## Multi-language design
The test suite support English and Nederlands. The user can use **language=en** or **language=nl**
in config.properties to run the test suite is either English or NL respectively.

## Test Scenarios Covered
1. **HomePage** - validated logo, navigation, footer, page title in English and Dutch and language switching functionality.
2. **Navigation & Categories** - validated product grid, Applied filters + dynamic updates.
3. **Product Detail** - validated product name, price, description, images, euro price format & Test image gallery behavior.
4. **Cart** - validated add product, cart count, subtotal, quantity update, recalculation and removal
5. **Search** - validated products search in grid, invalid product search in grid
6. **Negative & Edge cases** - validated cart quantity zero removes item, large quantity cart recalculation, special character search results

## Reports
Cucumber reports are generated under `target/cucumber-reports/`

- `report.html` - human-readable report