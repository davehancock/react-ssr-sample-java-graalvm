# react-ssr-sample-java-graalvm

This project demonstrates rendering Javascript code on the server side using Java with GraalVM.


## TL;DR

The client side code consists of a small React app that uses some popular libraries such as react-router, bootstrap etc. It
features a page that has dynamic data with state inserted from the server side which can then also be later updated on the client side.

The server side code consists of a simple Spring Boot application that renders JS using GraalVM. The server side app fetches and caches
some basic postcode data available from a third party, open API - https://api.postcodes.io.

See the Run section on how to start the app locally.


## Run

This sample has been packaged as a docker container and can be ran by executing:

```
docker run -p8080:8080 daves125125/react-ssr-sample-java-graalvm
```

Navigate to `localhost:8080/` to see the sample running.


## Build / Run from source

```
yarn install && yarn build && ./gradlew clean bootRun
```

Or, via Docker:

```
yarn install && yarn build
./gradlew clean build && docker build -t test .
docker run -p8080:8080 test
```


## How this works / Areas of interest

NOTE: Refer to the build.gradle file for a Spring Boot package Gotcha.


The JS code is split into two main bundles, the client.js and server.js. These are built as independent source sets
by Webpack. Both the server.js and client.js depend upon the the main React App itself with the only difference being
that the client side component includes client side specific code such as browser routing, and the server side code includes
server side routing and injection of initial state.

The Server side uses graalvm to render only the server.js bundle which gets packaged as part of the build process. Some
Spring framework code largely abstracts the handling of the MVC layer as well as the configuration of the graalvm templating
engine.

Regarding SSR, the main files of interest are:

- react-src/client.js
- react-src/server.js
- src/main/java/com/djh/SSRController.java
- src/main/java/com/djh/SSRConfiguration.java


## Performance

The below have been collected from repeated runs using the AB testing tool. This has been ran on a MacBook Pro (Retina, 13-inch, Early 2015)

|                     | At Rest / Startup                   | Under Load  |
| ------------------- |:-----------------------------------:| -----------:|
| Render Speed (ms)   | ~32                                 | ~15         |
| Throughput (msgs/s) | ~30                                 | ~60         |
| Memory Usage (Mb)   | ~300                                | ~300        |
