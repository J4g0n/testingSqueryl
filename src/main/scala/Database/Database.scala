package Database

import Models.User
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.adapters.MySQLAdapter
import org.squeryl.{Schema, Session, SessionFactory}


object UserSchema extends Schema {
  val users = table[User]

  on(users)(user => declare(
    user.id is (autoIncremented),
    user.email is (unique)
  ))
}

object Database {
  val databaseUsername = "root"
  val databasePassword = "batterie"
  val databaseConnection = "jdbc:mysql://localhost:3306/test"

  def startDatabaseSession = {
    Class.forName("com.mysql.jdbc.Driver")
    SessionFactory.concreteFactory = Some(() => {
        val session = Session.create(
          java.sql.DriverManager.getConnection(databaseConnection, databaseUsername, databasePassword),
          new MySQLAdapter)
        print(s"${session}")
        session
      }
    )
  }

  def createSchema = {
    transaction {
      UserSchema.create
      println("Created the schema")
    }
  }

}
