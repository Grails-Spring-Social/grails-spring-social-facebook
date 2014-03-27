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
package grails.plugins.springsocial.config.facebook

import org.grails.plugin.platform.config.PluginConfiguration

import javax.inject.Inject

import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionFactory
import org.springframework.social.connect.ConnectionRepository

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.impl.FacebookTemplate
import org.springframework.social.facebook.connect.FacebookConnectionFactory
import org.springframework.util.Assert


@Configuration
class FacebookConfig {
  @Inject
  ConnectionRepository connectionRepository
  @Inject
  PluginConfiguration pluginConfiguration



  @Bean
  ConnectionFactory facebookConnectionFactory() {
    println "Configuring SpringSocial Facebook"
    ConfigObject config =  pluginConfiguration.getPluginConfig("springsocialFacebook")
    String clientId = config.clientId ?: ""
    String clientSecret = config.clientSecret ?: ""
    Assert.hasText(clientId, "The Facebook clientId is necessary, please add to the Config.groovy as follows: plugin.springsocialFacebook.clientId='yourClientId'")
    Assert.hasText(clientSecret, "The Facebook clientSecret is necessary, please add to the Config.groovy as follows: plugin.springsocialFacebook.clientId='yourClientSecret'")
    new FacebookConnectionFactory(clientId, clientSecret)
  }

  @Bean
  @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
  public Facebook facebook() {
    Connection<Facebook> facebook = connectionRepository.findPrimaryConnection(Facebook)
    facebook != null ? facebook.getApi() : new FacebookTemplate()
  }
}
