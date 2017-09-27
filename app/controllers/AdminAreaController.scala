package app.controllers

import javax.inject.Inject

import scala.concurrent.Future
import play.api.i18n.I18nSupport
import play.api.mvc.{ AbstractController, AnyContent, ControllerComponents }
import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.{ LogoutEvent, Silhouette }
import controllers.AssetsFinder

import app.services.ldap.LDAPService
import utils.auth.DefaultEnv

class AdminAreaController @Inject() (
  components: ControllerComponents,
  silhouette: Silhouette[DefaultEnv]
)(
  implicit
  assets: AssetsFinder
) extends AbstractController(components) with I18nSupport {

  //TODO: Only administrator accessible
  def index = silhouette.SecuredAction.async { implicit request: SecuredRequest[DefaultEnv, AnyContent] =>
    //TODO: Exception handling
    Future.successful(Ok(views.html.admin.index(request.identity)))
  }

}
