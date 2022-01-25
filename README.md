# HTTP4K Contract Example

In this example, the aim is to have the response in the Swagger doc
to include:

```
{
  "info": ...
  ...
  "paths": {
    "": {
      "get": {
        ...
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/vnd.api+json": {
                ...
```

Instead, it has `application/json` in the last line shown.
This is in spite of App.kt containing:
```kotlin
produces += ContentType("application/vnd.api+json")
```

To run the example, run `gradlew run`. Stop with Ctrl-C.

Navigate to http://localhost:8080/ to see the app in action.
The response header Content-Type can be seen in Developer Tools as
`application/json;charset=utf-8`

Navigate to http://localhost:8080/docs/swagger.json to see the Swagger doc
