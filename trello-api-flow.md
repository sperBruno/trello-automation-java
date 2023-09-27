# Trello API FLOW

---

1. Create Board
   ```
   curl --location --request POST 'https://api.trello.com/1/boards/?name=at-08-80d837b0-33af-42de-9bff-5855a2257b93&key=26ebc752cd312203f270b108dcdbb2a7&token=ATTAd2a8f2774558a756223921453b3196116625784c7062f1daaf548eca4dc970e6F5FFC541' \
   --header 'Cookie: dsc=e0c10ee33d104e3ed2bc9ff35ba12754eedc935d35a15f873445765fd40c229e; preAuthProps=s%3A5fdafe3c9c859f5237a355ee%3AisEnterpriseAdmin%3Dfalse.wuNDL2ztEBuWPsgBm6kKamLwXqTpTHsGkwQKX3U9oYQ'
   ```

2. Update Board
   ```
   curl --location --request PUT 'https://api.trello.com/1/boards/6513979fe2876a07748a06cb?key=26ebc752cd312203f270b108dcdbb2a7&token=ATTAd2a8f2774558a756223921453b3196116625784c7062f1daaf548eca4dc970e6F5FFC541&name=BOARD%20Updated%20ffa960ce-a266-4966-95f1-f6c784b0ef6c' \
   --header 'Accept: application/json' \
   --header 'Cookie: dsc=e0c10ee33d104e3ed2bc9ff35ba12754eedc935d35a15f873445765fd40c229e; preAuthProps=s%3A5fdafe3c9c859f5237a355ee%3AisEnterpriseAdmin%3Dfalse.wuNDL2ztEBuWPsgBm6kKamLwXqTpTHsGkwQKX3U9oYQ'
   ```

3. Create List
    ```
   curl --location --request POST 'https://api.trello.com/1/boards/651392a63dd6d4deb99898c3/lists?name=TODO&key=26ebc752cd312203f270b108dcdbb2a7&token=ATTAd2a8f2774558a756223921453b3196116625784c7062f1daaf548eca4dc970e6F5FFC541' \
    --header 'Accept: application/json' \
    --header 'Cookie: dsc=e0c10ee33d104e3ed2bc9ff35ba12754eedc935d35a15f873445765fd40c229e; preAuthProps=s%3A5fdafe3c9c859f5237a355ee%3AisEnterpriseAdmin%3Dfalse.wuNDL2ztEBuWPsgBm6kKamLwXqTpTHsGkwQKX3U9oYQ'
    ```
   
3. Create Card
    ```
   curl --location --request POST 'https://api.trello.com/1/cards?key=26ebc752cd312203f270b108dcdbb2a7&token=ATTAd2a8f2774558a756223921453b3196116625784c7062f1daaf548eca4dc970e6F5FFC541&idList=651393fc8b7b4015f68d2539&name=AT-08%20card&desc=Est%20carta%20se%20utilizara%20para%20realizar%20test%20case%20' \
    --header 'Accept: application/json' \
    --header 'Cookie: dsc=e0c10ee33d104e3ed2bc9ff35ba12754eedc935d35a15f873445765fd40c229e; preAuthProps=s%3A5fdafe3c9c859f5237a355ee%3AisEnterpriseAdmin%3Dfalse.wuNDL2ztEBuWPsgBm6kKamLwXqTpTHsGkwQKX3U9oYQ'
    ```
   
4. Get Card
    ```
   curl --location 'https://api.trello.com/1/cards/651397adc5d06418952b0196?key=26ebc752cd312203f270b108dcdbb2a7&token=ATTAd2a8f2774558a756223921453b3196116625784c7062f1daaf548eca4dc970e6F5FFC541' \
   --header 'Accept: application/json' \
   --header 'Cookie: dsc=e0c10ee33d104e3ed2bc9ff35ba12754eedc935d35a15f873445765fd40c229e; preAuthProps=s%3A5fdafe3c9c859f5237a355ee%3AisEnterpriseAdmin%3Dfalse.wuNDL2ztEBuWPsgBm6kKamLwXqTpTHsGkwQKX3U9oYQ'
    ```
   
5. Delete Board
    ```
   curl --location --request DELETE 'https://api.trello.com/1/boards/651396e440b4972cdceb468b?key=26ebc752cd312203f270b108dcdbb2a7&token=ATTAd2a8f2774558a756223921453b3196116625784c7062f1daaf548eca4dc970e6F5FFC541' \
    --header 'Cookie: dsc=e0c10ee33d104e3ed2bc9ff35ba12754eedc935d35a15f873445765fd40c229e; preAuthProps=s%3A5fdafe3c9c859f5237a355ee%3AisEnterpriseAdmin%3Dfalse.wuNDL2ztEBuWPsgBm6kKamLwXqTpTHsGkwQKX3U9oYQ'
    ```

Test examples:
--

```
postman.setEnvironmentVariable("boardID", pm.response.json().id);

pm.test("resultado esperado", function () {
pm.response.to.have.status(200);
});

pm.test("Status code is 200", () => {
  pm.expect(pm.response.code).to.eql(200);
});

pm.test("Check board name is updated", function () {
    pm.expect(pm.response.json().name).contains("BOARD Updated");
});

pm.test("validate cardID matches", function () {
    pm.expect(pm.response.json().id).to.eql(pm.environment.get("cardID"))
});
```
