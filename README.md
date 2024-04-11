# LegacyHttpReproducer

Just a simple reproducer for a duplicate class definition while running `./gradlew lintDebug` with AGP 8.3.x

The issue at hand is that project invokes [useLibrary(name = "org.apache.http.legacy")](/app/build.gradle.kts#L19)

If one wants to add unit tests for [CookieStore.java](/app/src/main/java/com/demo/legacyhttpreproducer/CookieStore.java), one needs to add the [testImplementation](/app/build.gradle.kts#L45) dependency for this to work.

If one also adds this dependency for [androidTestImplementation](/main/app/build.gradle.kts#L46) (either by mistake or thinking that the same would be needed to run verification steps for a connected test), one gets a duplicate class definition while running lint. This is a breaking error and stops any build process that may want to invoke a pipeline step of `./gradlew lintDebug lintDebuUnitTest`

Researching if this is actually a bug in lint enforcement or if it is just bad coding practice.
