# Important:
This repository is no longer in use. SCR API is developed [here](https://github.com/NHSDigital/summary-care-record-api/)

# integration-adaptor-scr
National Integration Adaptors - Summary Care Record

## Requirements:
1. JDK 14

## How to run unit tests:
* Navigate to `service`
* Run: `./gradlew test`

## How to run integration tests:
* Navigate to `service`
* Run: `./gradlew integrationTest`

## How to use Spine Mock Service
By default service will run on `http://localhost:8081` and provide 2 endpoints:

---
* POST `/setup`

It can be used to mock any endpoint (sky is the limit). If you get HTTP 200 as reponse it means your endpoint is ready to use.
```
{
    "url": "/sample",
    "httpStatusCode": 200,
    "httpMethod": "GET",
    "responseContent": "My sample message."
}
```
---
* POST `/setup/reset` - removes all custom endpoints created by /setup.
---