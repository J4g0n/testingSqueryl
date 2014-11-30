package Models

/**
 * Created by sandman on 11/29/14.
 */
case class User (name: String, email: String) extends BaseEntity { def this() = this("", "")}
