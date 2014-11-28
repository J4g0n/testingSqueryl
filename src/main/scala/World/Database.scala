package World

import org.squeryl.{Schema, KeyedEntity, Session, SessionFactory}
import org.squeryl.adapters.MySQLAdapter
import java.sql.Timestamp
import org.squeryl.PrimitiveTypeMode._

/**
 * Created by Simon on 28/11/2014.
 */

// adapted from: http://www.srirangan.net/2011-03-getting-started-scala-persistence-with-squeryl

class BaseEntity extends KeyedEntity[Long] {
  val id:Long = 0
  var lastModified = new Timestamp(System.currentTimeMillis)
}

case class User (name: String, email: String) extends BaseEntity { def this() = this("", "")}

object UserSchema extends Schema {
  val users = table[User]

  on(users)(user => declare(
    user.id is (autoIncremented),
    user.email is (unique)
  ))
}

object Database {
  val databaseUsername = "root"
  val databasePassword = "root"
  val databaseConnection = "jdbc:mysql://localhost:3306/test2"

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

  def addUser(name: String, email: String) = {
    transaction {
      val newUser = new User(name = name, email = email)
      UserSchema.users.insert(newUser)
      println("Inserted user")
    }
  }
}
