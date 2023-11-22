# trello-automation - java-automation

---
## API module
Command to run api features

```shell
gradle clean apiFeatures
```
Command to run api features with tag

```shell
gradle clean apiFeatures -Ptags="@Board"
```

Allure Report commands

Place yourself on path that allure-results exists.

Command to generate report and serve it using tmp path
```shell
allure serve
```
Command to generate allure-report folder that contains html file of the report

```shell
Allure generate
```


Upload apk to browserstack

```shell
curl --location 'https://api-cloud.browserstack.com/app-automate/upload' \
--header 'Authorization: Basic ' \
--form 'file=@"<path>/trello-automation/trello-mobile/src/test/resources/apps/ApiDemos.apk"'
```
---
## Mobile module


Command to run mobile trello test
```shell
gradle mobileFeatures -Ptags="@trelloMo"
```
**Note:** Make sure to set env to Android and apiDemos to false in gradle.properties

Command to run mobile apiDemos test
```shell
gradle mobileFeatures -Ptags="@apiDemos"
```
**Note:** Make sure to set env to Android and apiDemos to true in gradle.properties

Command to run mobile apiDemos test in browserStack
```shell
gradle mobileFeatures -Ptags="@apiDemos"
```
**Note:** Make sure to set env to Android_BrowserStack and apiDemos to true, also 
update browserStackUserName and browserStackUserKey in gradle.properties

