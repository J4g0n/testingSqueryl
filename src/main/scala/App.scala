package example

import Database._
import Repositories._

object App {
  def main(args: Array[String]) {
    print("Hello, this is a mysql test project!")

    Database.startDatabaseSession
    Database.createSchema
    UserRepository.addUser("Simon", "simon.andreux@gmail.com")

    print("Goodbye, folks !!")
  }
}
