/* Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
  // inherit Grails' default dependencies
  inherits("global") {
    // uncomment to disable ehcache
    // excludes 'ehcache'
  }
  log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
  legacyResolve true
  repositories {
    grailsPlugins()
    grailsHome()
    grailsCentral()

    // uncomment the below to enable remote dependency resolution
    // from public Maven repositories
    mavenLocal()
    mavenCentral()

    mavenRepo "http://maven.springframework.org/release"
    mavenRepo "http://maven.springframework.org/snapshot"
    mavenRepo "http://maven.springframework.org/milestone"

    mavenRepo "http://repo.sindica.to/nexus/content/repositories/snapshots/"
    grailsRepo "http://grails.org/plugins"

  }
  dependencies {
    def springSocialVersion = "1.1.0.RC1"
    compile("org.springframework.social:spring-social-facebook:${springSocialVersion}")

   // compile("org.codehaus.jackson:jackson-mapper-asl:1.9.2")
  }
  plugins {
    compile(":spring-social-core:0.2.0.BUILD-SNAPSHOT")
    compile ":platform-core:1.0.RC6"

    /*
    This validation is for prevent load the following plugins in previous Grails versions.
    I some Grails versions from 1.3.* the 'export = false' does not work. For Grails 2.* works properly
    */
    if (grailsVersion.startsWith('2')) {
      test(":code-coverage:1.2.7") { export = false }
      build(":release:3.0.1") { export = false }
      build(":rest-client-builder:1.0.2") { export = false }
    }
  }
}

grails.project.repos.sindicato.url = "http://repo.sindica.to/nexus/content/repositories/snapshots/"
grails.project.repos.default = "sindicato"

grails.release.scm.enabled = false
//grails.project.repos.default = "grailsCentral"


coverage {
  exclusions = [
      "DefaultSpringSocialConfig*",
      "SpringSocialCoreDefaultConfig*"
  ]
  enabledByDefault = true
}