---
layout: post
title: "Installing to the Local Maven Repo With Gradle"
date: 2011-11-04 11:34
comments: true
categories: 
- Java
- Maven
- Gradle
---

I've been playing around with different build tools for my Java projects recently, having never been very happy with [Maven][M]. Probably the best that I've found is [Gradle][G]: it has an easy to use build file format, and seems pretty flexible if you need to do something a little differently.

Unfortunately the documentation isn't as comprehensive as it could be, and one of the areas where it's not too great is in it's interaction with the Maven repository system. So, here's the magic incantation that you have to add to your build file in order to have *gradle install* install things correctly to your local repository:

```groovy
apply plugin 'maven'
configure(install.repositories.mavenInstaller) {
    pom.project {
        groupId 'com.example'
        artifactId 'project-name'
        inceptionYear '2011'
        packaging 'jar'
        licenses {
            license {
                name 'Eclipse Public License (Version 1.0)'
                url 'http://www.eclipse.org/legal/epl-v10.html'
                distribution 'repo'
            }
        }
    }
}
```

this will install the project binaries, to also install source and JavaDocs (which *every* project should really do) then you'll also need to add:

```groovy
task sourcesJar(type: Jar, dependsOn:classes) {
     classifier = 'sources'
     from sourceSets.main.allSource
}
 
task javadocJar(type: Jar, dependsOn:javadoc) {
     classifier = 'javadoc'
     from javadoc.destinationDir
}
 
artifacts {
     archives sourcesJar
     archives javadocJar
}
```

[M]: http://maven.apache.org/ "Apache Maven"
[G]: http://www.gradle.org/ "Gradle"
