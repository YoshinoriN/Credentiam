package app.services

import javax.inject.Inject
import scala.concurrent.{ ExecutionContext, Future }
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.IdentityService
import app.models.{ UserDAO, UserIdentify }
import app.utils.types.UserId

trait UserServiceTrait extends IdentityService[UserIdentify] {

  def retrieve(id: UserId): Future[Option[UserIdentify]]

  def save(user: UserIdentify): Future[UserIdentify]

}

/**
 * Handles actions to users.
 */
class UserService @Inject() (userDAO: UserDAO)(implicit ex: ExecutionContext) extends UserServiceTrait {

  def retrieve(id: UserId) = userDAO.find(id)

  def retrieve(loginInfo: LoginInfo): Future[Option[UserIdentify]] = userDAO.find(loginInfo)

  def save(user: UserIdentify) = userDAO.save(user)

}

