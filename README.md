# license-maven-plugin

This is the [license-maven-plugin](http://www.mojohaus.org/license-maven-plugin/).

[![Maven Central](https://img.shields.io/maven-central/v/org.bitbucket.cpointe.mojohaus/license-maven-plugin.svg?label=Maven%20Central)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.bitbucket.cpointe.mojohaus%22%20a%3A%license-maven-plugin%22)
[![The GNU Lesser General Public License, Version 3.0](https://img.shields.io/badge/license-LGPL3-blue.svg)](http://www.gnu.org/licenses/lgpl-3.0.txt)

## Releasing

* Make sure `gpg-agent` is running.
* Execute `mvn -B release:prepare release:perform`

For publishing the site do the following:

```
cd target/checkout
mvn verify site -DperformRelease scm-publish:publish-scm
```
