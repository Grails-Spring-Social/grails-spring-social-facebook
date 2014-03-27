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
package grails.plugins.springsocial.facebook

import grails.plugins.springsocial.UserConnection
import org.springframework.social.connect.ConnectionKey

import org.springframework.social.facebook.api.Facebook

class SpringSocialFacebookController {
  def facebook
  def connectionRepository

  def beforeInterceptor = [action: this.&auth, except: 'login']

  def index = {
    def model = ["profile": facebook.userOperations().getUserProfile()]
    render(view: pluginConfig.page.profile, model: model)
  }
  def profilePhoto = {
    response.outputStream << facebook.userOperations().getUserProfileImage()
  }
  def feed = {
    def model = ['feed': facebook.feedOperations().getFeed()]
    render(view: pluginConfig.page.feed, model: model)
  }

  def update = {
    def message = params.id ?: params.message
    facebook.feedOperations().updateStatus(message);
    redirect(action: feed)
  }

  def friends = {
    def model = ["friends": facebook.friendOperations().getFriendProfiles()]
    render(view: pluginConfig.page.friends, model: model)
  }

  def albums = {
    def model = ["albums": facebook.mediaOperations().getAlbums()]
    render(view: pluginConfig.page.albums, model: model)
  }

  def album = {
    def albumId = params.id ?: params.albumId
    def model = [:]
    model.album = facebook.mediaOperations().getAlbum(albumId)
    model.photos = facebook.mediaOperations().getPhotos(albumId)

    render(view: pluginConfig.page.album, model: model)
  }

  def login = {
    render(view: pluginConfig.page.connect)
  }

  def auth() {
    if (!isConnected()) {
      redirect(action: 'login')
      return false
    }
  }

  Boolean isConnected() {
    connectionRepository.findPrimaryConnection(Facebook)
  }
}