Selenium Page Object Tests

## Selenium Library

#### 1. Purpose and Contents

The 'selenium' library contains a set of java helper classes that support browser and mobile app testing by leveraging Selenium and Appium.

The helper classes are split into 2 packages 'core' and 'execution'.

The 'core' package includes a set of helper classes and methods that wrap around some of the Selenium objects (WebElement, WebDriverWait, Select, Actions) providing the most commonly used selenium functions whilst resolving some of the issues typically encountered in test projects (e.g. timeouts and stale element exceptions).

The 'execution' package includes a driver factory which manages the driver lifecycle, parallel test execution (multi-threaded) and the webdriver *desired capabilities* and *driver options*.  The factory supports:

- multi-browser testing (chrome, firefox, internet explorer, edge, safari, htmlunit, phantomJS)
- local execution, selenium grid execution, cloud execution (saucelabs / browserstack)

The factory enables parallel execution in 2 dimensions of 'tests' x 'tech stack'.  For example running 3 tests against a tech stack of 'chrome+linux' and 'internet explorer+windows' would generate 6 parallel threads of execution for accelerated test execution times.

Parallel execution within a test project requires simple configuration files only and multiple configurations can be pre-defined and called upon at runtime.

Local, grid, cloud based execution within a test project requires simple configuration files only and multiple configurations can be pre-defined and called upon at runtime.

Test projects can choose to make use of the 'core' package only and manage their own driver lifecycles, however, even if a project is not planning to run parallel testing, it is strongly recommended that projects re-use the driver factory provided by the library as it greatly simplifies the test project code. 

It is also highly recommended that a test project adopts the page object pattern.  As detailed in the table below a BasePO class is included in the 'execution' package that will be the super class for each page object in the test project.

##### 1.1 Package: Core
 

| Class                     | Purpose                                                      |
| ------------------------- | ------------------------------------------------------------ |
| Constants                 | list of directory references used by the library classes relative to the test project |
| Element                   | provides a set of methods commonly used for finding and interacting with individual elements on a browser or mobile app.  When finding elements automatic waits are performed for element availability, and scroll to element is performed before performing any selenium/appium action.  In addition automatic retries for stale element exceptions. |
| Locator                   | provides enum + method to derive dynamic element locators    |
| Page                | provides a set of page level methods that generally return the Element class when performing find operations.  Also includes methods that perform page level waits for DOM loading, JQuery and Angular readiness. |
| Screenshot                | provides helper methods for taking screenshots and writing to file |

