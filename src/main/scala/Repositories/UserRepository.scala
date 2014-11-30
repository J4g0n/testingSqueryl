package Repositories

import Database.UserSchema
import Models.User
import org.squeryl.PrimitiveTypeMode._

/**
 * Created by sandman on 11/29/14.
 */
object UserRepository {
  def addUser(name: String, email: String) = {
    transaction {
      val newUser = new User(name = name, email = email)
      UserSchema.users.insert(newUser)
      println("Inserted user")
    }
  }
}
