# Screeps

My scripts for screeps

Made with Screeps Kotlin Starter

### How to run

Create a `gradle.properties` settings file (see: [options](https://github.com/exaV/screeps-kotlin-starter)).

Usage:

    ./gradlew deploy

Watch:

    ./gradlew -t deploy


### Run scripts for interactive developmnt of algorithms

```
npx nodemon --exec kotlinc -script scripts/{SCRIPT}.kts
```

### Troubleshooting

#### Gradle says it deployed correctly, but my code doesnt show up.
  Make sure you deployed to a branch which exists on the Server, if not it will fail silently.

#### Imports
Make sure to `import screeps.api.*` as it includes many useful extension functions which are otherwise hard to find
