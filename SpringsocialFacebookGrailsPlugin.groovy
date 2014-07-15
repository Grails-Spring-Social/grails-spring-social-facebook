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
class SpringsocialFacebookGrailsPlugin {
  def version = "0.2.0.BUILD-SNAPSHOT"
  def grailsVersion = "1.3 > *"
  def dependsOn = [:]
  // resources that are excluded from plugin packaging
  def pluginExcludes = [
      "grails-app/views/error.gsp",
      "web-app/js",
      "web-app/images/**",
      "web-app/images/skin/**",
      "grails-app/conf/Config.groovy",
      "grails-app/conf/DataSource.groovy",
      "grails-app/i18n/**",
      'docs/**',
      'src/docs/**'
  ]

  // TODO Fill in these fields
  def author = "Domingo Suarez Torres"
  def authorEmail = "domingo.suarez@gmail.com"
  def title = "Spring Social Facebook"
  def description = 'Spring Social Facebook plugin.'

  // URL to the plugin's documentation
  def documentation = "http://grails.org/plugin/spring-social-facebook"

  def license = "APACHE"
  def organization = [name: "Sindicato Source", url: "http://sindica.to"]
  def developers = [[name: "Domingo Suarez Torres", email: "domingo.suarez@gmail.com"]]
  def scm = [url: "https://github.com/synergyj/grails-spring-social-facebook"]
  def issueManagement = [system: "GITHUB", url: "https://github.com/synergyj/grails-spring-social-facebook/issues"]


  def doWithSpring = {
    xmlns context: "http://www.springframework.org/schema/context"
    context.'component-scan'('base-package': "grails.plugins.springsocial.config.facebook")
  }

  def doWithConfig = { config ->
    springsocialFacebook {
      page.connect = "/springsocial/facebook/connect"
      page.profile = "/springsocial/facebook/index"
      page.connectedHome = "/springSocialFacebook/index"
      page.feed = "/springsocial/facebook/feed"
      page.friends = "/springsocial/facebook/friends"
      page.albums = "/springsocial/facebook/albums"
      page.album = "/springsocial/facebook/album"

    }
    def doWithConfigOptions = {
      clientId(type: String)
      clientSecret(type: String)
    }

  }

}
