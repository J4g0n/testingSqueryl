package Models

import java.sql.Timestamp

import org.squeryl.KeyedEntity

/**
 * Created by Simon on 28/11/2014.
 */

// adapted from: http://www.srirangan.net/2011-03-getting-started-scala-persistence-with-squeryl

class BaseEntity extends KeyedEntity[Long] {
  val id:Long = 0
  var lastModified = new Timestamp(System.currentTimeMillis)
}
