# Fork Documentation

This is the [license-maven-plugin](http://www.mojohaus.org/license-maven-plugin/).

[![Maven Central](https://img.shields.io/maven-central/v/org.technologybrewery.mojohaus/license-maven-plugin.svg?label=Maven%20Central)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.technologybrewery.mojohaus%22%20a%3A%license-maven-plugin%22)
[![The GNU Lesser General Public License, Version 3.0](https://img.shields.io/badge/license-LGPL3-blue.svg)](http://www.gnu.org/licenses/lgpl-3.0.txt)

## What is in this fork?
- Single year copyright support (levering Maven `inceptionYear` value)
- Removes default execution on `json` files
- Adds `R` file licensing

## What create a fork?
We would normally commit these back to the host repository, but it appears to be
no longer maintained.

# Original Documentation

## Releasing

* Make sure `gpg-agent` is running.
* Execute `mvn -B release:prepare release:perform`

For publishing the site do the following:

```
cd target/checkout
mvn verify site -DperformRelease scm-publish:publish-scm
```
