# Ecommerce - LumaStore Test Automation Framework (Selenium + Java)

Automates the complete **end-to-end user journey** of the [Magento Luma Store](https://magento.softwaretestingboard.com) using **Selenium WebDriver**, **TestNG**, and **ExtentReports**.

---

## 🚀 Key Highlights

| Feature | Description |
|--------|-------------|
| 🔧 Modular Design | Clean Page Object Model, utilities, and constants |
| 📜 Extent Reports | HTML reports with system info & screenshots |
| 💬 Logging & Debugging | `log4j2` integrated with rolling file strategy |
| 🧪 E2E Coverage | Tests simulate real-world checkout flow |
| ⚙️ Configurable | Centralized `config.properties` using type-safe enums |
| 🚀 CI-Ready | Timestamped reports & headless execution compatibility |

---
## 🧠 Design Patterns

-   **Page Object Model (POM)** for UI abstractions.
    
-   **Factory / Singleton** for WebDriver instantiation.
    
-   **Listener interfaces** for screenshots and test hooks.
    
-   **Config-driven**: externalize environments via `config.properties`.

---

## 📁 Project Structure


```text
com.magneto.lumastore_V1
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── LumaStore/
│   │   │       ├── base/
│   │   │       │   └── TestBase.java
│   │   │       ├── pageobjects/
│   │   │       │   └── [IndexPage.java, LoginPage.java, ...]
│   │   │       ├── utilConstants/
│   │   │       │   ├── ConfigProp.java
│   │   │       │   └── FilePaths.java
│   │   │       └── utils/
│   │   │           ├── DriverUtils.java
│   │   │           ├── ReadProperty.java
│   │   │           └── ReportListeners.java
│   ├── test/
│   │   ├── java/
│   │   │   └── LumaStore/
│   │   │       ├── testCases/
│   │   │       │   └── [TC01_IndexPageTest.java, ...]
│   │   │       └── testSuites/
│   │   │           └── [FST_suite.xml, LoginTestSuite.xml]
│   └── resources/
│       ├── config.properties
│       ├── log4j2.xml
│       └── report-config.json
│
├── extent-reports/
│   ├── failed-screenshots/
│   ├── reports/
│   └── spark/
│
├── logs/
│   └── logfile.log (auto-rotated)
├── target/
│   └── test-output/
├── pom.xml
└── LumaStore_Report.html
```  


---

## ✅ 1. Core Framework Source (`src/main/java/LumaStore/`)

### 📦 `base/`
#### 🔹 `TestBase.java`

**Purpose**: Superclass that initializes the entire framework.

**Responsibilities**:
- WebDriver initialization (ChromeDriver, maximized window)
- Loads `config.properties` for test URL
- WebDriverWait setup ( max of 10s)
- Initializes Extent Report via `ExtentSparkReporter`
- Loads report configuration from `spark/report-config.json`
- Adds system/browser metadata to report
- Handles teardown with driver quit
- Supports timestamped or static report paths for CI use (e.g., Jenkins)

✅ *Robust error handling*: By accessing the WebDriver only through `getDriver()`, you **prevent NullPointerExceptions** and get a **clear, custom error** if the driver hasn’t been initialized properly.

---

### 📦 `pageobjects/`

Contains **Page Object Model (POM)** classes representing each page in the Magento Luma Store.

Each class:
- Uses `PageFactory.initElements()` to bind WebElements
- Cleanly separates:
  - 🧩 **Page Elements** (with `@FindBy`)
  - ⚙️ **Page Methods** (only actions — no validations)

**Example pages**:
- `LoginPage.java`, `HomePage.java`, `ShoppingPage.java`, etc.

---

### 📦 `utils/`

### 🔹 `DriverUtils.java`
**Purpose**: WebDriver utility methods for common actions.

Includes:
- Explicit waits (visibility, clickability)
- Scrolling, hovering, dropdown selection
- Window/tab switching
- Time utility for timestamps

### 🔹 `ReadProperty.java`
**Purpose**: Centralized config loader *(singleton-style).*

- Loads `config.properties` once at startup
- Provides:
  - Type-safe value retrieval via `ConfigProp` enum
  - Null-check + exception for missing keys (fail-fast)

### 🔹 `ReportListeners.java`
**Purpose**: Custom `ITestListener` for Extent reporting.

**Hooks Implemented**:
- `onTestStart`: Initialize test metadata
- `onTestSuccess`: Mark pass
- `onTestFailure`: Log error, capture + attach screenshot
- `onTestSkipped`: Log skip
- `onFinish`: Flush report

**Extra Features**:
- Logs custom info with `logMessage()`
- Screenshots:
  - `captureScreenshot()` — for inline Base64 (pass/info)
  - `captureScreenshot(ITestResult)` — for file-based (failures)
- Screenshot naming uses dynamic timestamp + method name

---

### 📦 `utilConstants/`

### 🔹 `ConfigProp.java`
- Enum representing keys from `config.properties`
- Type-safe usage for:
  - `URL`, `USERNAME`, `PASSWORD`, `DEVICE`, etc.
- Prevents typos, improves IDE autocomplete
- Constructor validates non-null, non-empty keys

### 🔹 `FilePaths.java`
- Enum for centralized file path templates

Supports:
- Config file
- Reports (`report_{timestamp}.html`)
- Screenshots (`{methodName}_{timestamp}.png`)

Provides methods:
- `getPath()`
- `getPath(String timestamp)`
- `getFormattedPath(String method, String timestamp)`

---
## 🧪 2. Test Layer (`src/test/java/LumaStore/`)

### 📦 `testCases/`

Each class represents a user journey step — all extending `TestBase`.

**Files**:
- `TC01_IndexPageTest.java`
- `TC02_LoginPageTest.java`
- ...
- `TC08_CheckoutPageTest.java`

**Pattern**:
- `@BeforeMethod`: Page object instantiation
- `@Test`: Performs actions + assertions (e.g., validate login)
- Uses `logger` for detailed step logging
- Uses `Assert` for final validations

✅ E2E sequence aligned with real-world checkout flow

---

### 📦 `testSuites/`

#### TestNG XMLs for desired execution:
- `FST_suite.xml`:  Full System Test suite that executes the complete end-to-end regression flow.
- `LoginTestSuite.xml`: Focused test suite covering login functionality and its edge cases.
- `EnvTestSuite.xml`: Lightweight sanity suite to verify environment readiness (e.g., portal accessibility).

**All suites**:
- Use `<listeners>` to plug in `ReportListeners`
- Maintain test order with `preserve-order="true"`

---

## 📚 3. Resources (`src/test/resources/`)

- `config.properties`: Stores env-level data (URL, username, etc.)
- `log4j2.xml`: Logging config with **RollingFile appender**
  - Rotates logs using size-based strategy (`1KB`)
  - Output path set to `./logs`
- `report-config.json`: Spark Report styling and layout

---

## 📊 4. Reporting & Logs

- `extent-reports/`: Base folder for reports
- `failed-screenshots/`: Stores screenshots captured on test failure
- `reports/`: Timestamped reports stored using `FilePaths.REPORT`
- `logs/`: Stores `logfile.log` and rotated logs
- `spark/`: Contains report design JSON (`report-config.json`)

---

## 📦 5. Dependencies (`pom.xml`)

Includes:
-   Selenium Java    
-   TestNG    
-   ExtentReports (`aventstack`)    
-   log4j2    
-   Apache POI (for future data-driven testing)    
-   Commons IO    
-   Lombok (optional)    
-   SLF4J
---
## 🏁 Running the Tests

### 🔧 Setup & Installation
**Clone the repo**
`git clone https://github.com/naveenkumarqa/com.magneto.lumastore_V1.git
cd com.magneto.lumastore_V1`

**Install dependencies**
`mvn clean install`

**Run all tests**
`mvn test`

---
### To Run in local
1.  **Configure Test Data**  
    Update credentials and URLs in:    
    `src/test/resources/config.properties` 
    
2.  **Run via TestNG Suite**      
    `src/test/java/LumaStore/testSuites/FST_suite.xml` 
    
3.  **View Report**  
    `extent-reports/reports/LumaTest-report_<timestamp>.html` 
----------

## 🎮 CI-Friendly Configs

-   Supports static `LumaStore_Report.html` path for Jenkins (uncomment in `TestBase`)    
-   Screenshot and report paths generated dynamically via enums    
-   Log folder automatically rotates on file size limit    

----------

## 🧠 Future Enhancements

-   Cross-browser config support 
-   Parallel execution using TestNG `parallel` attribute    
-   Integrate Allure or GitHub Actions CI pipeline    
-   Extend test data using Excel + Apache POI    
-   Add API layer automation for service-level validation   

----------

## 🙌 Author
**Naveen Kumar** 
SDET II | QA Automation Engineer
[GitHub](https://github.com/naveenkumarqa) | [LinkedIn](https://linkedin.com/in/naveenkumarqa) | [Portfolio](https://bento.me/naveenkumarqa)
