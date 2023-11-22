# trello-automation - java-automation

---

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
--form 'file=@"/C:/Users/User/Documents/bruno/qacademy/repos/trello-automation/trello-mobile/src/test/resources/apps/ApiDemos.apk"'```