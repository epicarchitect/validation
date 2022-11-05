# Validation
The essence of this library is that only implementations of validators can pass Correct and Incorrect object wrappers, because their constructor is marked internal. This ensures that our data is always validated by the validator.

### Add the JitPack repository
```Kotlin
repositories {
    maven("https://jitpack.io")
}
```

### Add the dependency
```Kotlin
dependencies {
    implementation("com.github.epicarchitect:validation:1.0.0")
}
```
