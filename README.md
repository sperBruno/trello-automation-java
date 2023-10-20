# trello-automation

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