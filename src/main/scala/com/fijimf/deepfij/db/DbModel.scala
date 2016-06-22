package com.fijimf.deepfij.db

import java.time.{LocalDateTime, ZoneOffset}

import slick.driver.{H2Driver, JdbcDriver}
import slick.jdbc.JdbcBackend
import slick.lifted.ForeignKeyQuery

import scala.slick.driver

class DbModel(val driver: JdbcDriver) {

  import driver.api._

  implicit val JavaLocalDateTimeMapper = MappedColumnType.base[LocalDateTime, Long](
    ldt => ldt.toEpochSecond(ZoneOffset.UTC),
    long => LocalDateTime.ofEpochSecond(long, 0, ZoneOffset.UTC)
  )

  class Teams(tag: Tag) extends Table[(Long, String, String)](tag, "TEAMS") {
    def id = column[Long]("ID", O.PrimaryKey)

    def name = column[String]("NAME")

    def logoUrl = column[String]("LOGO")

    def * = (id, name, logoUrl)
  }

  class Games(tag: Tag) extends Table[(Long, Long, Long, LocalDateTime, Option[Long], String)](tag, "GAMES") {


    def id = column[Long]("ID", O.PrimaryKey)

    def homeTeamId = column[Long]("HOME_TEAM_ID")

    def awayTeamId = column[Long]("AWAY_TEAM_ID")

    def date = column[LocalDateTime]("DATE")

    def result = column[Option[Long]]("RESULT_ID")

    def location = column[String]("LOCATION")

    def * = (id, homeTeamId, awayTeamId, date, result, location)

    def homeTeam: ForeignKeyQuery[Teams, (Long, String, String)] =
      foreignKey("HT_FK", homeTeamId, TableQuery[Teams])(_.id)

    def awayTeam: ForeignKeyQuery[Teams, (Long, String, String)] =
      foreignKey("AT_FK", homeTeamId, TableQuery[Teams])(_.id)
  }

  class Results(tag: Tag) extends Table[(Long, Int, Int)](tag, "RESULTS") {
    //(id: Long, homeScore: Int, awayScore: Int)
    def id = column[Long]("ID", O.PrimaryKey)

    def homeScore = column[Int]("HOME_SCORE")

    def awayScore = column[Int]("AWAY_SCORE")

    def * = (id, homeScore, awayScore)
  }


  class Users(tag: Tag) extends Table[(Long, String, Int)](tag, "USERS") {
    //   (id: Long, name: String, balance: Double)
    def id = column[Long]("ID", O.PrimaryKey)

    def name = column[String]("NAME")

    def balance = column[Int]("BALANCE")

    def * = (id, name, balance)
  }

  class Bets(tag: Tag) extends Table[(Long, Long, Long, Long, Int, Int)](tag, "BETS") {
    //   (id: Long, homeBettor: User, awayBettor: User, game: Game, doubleSpread: Int, amount: Int)
    def id = column[Long]("ID", O.PrimaryKey)

    def homeBettorId = column[Long]("HOME_BETTOR_ID")

    def awayBettorId = column[Long]("AWAY_BETTOR_ID")

    def gameId = column[Long]("GAME_ID")

    def doubleSpread = column[Int]("SPREAD")

    def amount = column[Int]("AMOUNT")

    def * = (id, homeBettorId, awayBettorId, gameId, doubleSpread, amount)

    def homeBettor: ForeignKeyQuery[Users, (Long, String, Int)] =
      foreignKey("HB_FK", homeBettorId, TableQuery[Users])(_.id)

    def awayBettor: ForeignKeyQuery[Users, (Long, String, Int)] =
      foreignKey("HB_FK", awayBettorId, TableQuery[Users])(_.id)

    def game: ForeignKeyQuery[Games, (Long, Long, Long, LocalDateTime, Option[Long], String)] =
      foreignKey("GG_FK", gameId, TableQuery[Games])(_.id)
  }

  class Offers(tag: Tag) extends Table[(Long, Long, Long, Boolean, Int, Int)](tag, "OFFERS") {
    //   (id: Long, bettor: User, game:Game, amount: Double)
    def id = column[Long]("ID", O.PrimaryKey)

    def bettorId = column[Long]("USER_ID")

    def gameId = column[Long]("GAME_ID")

    def isHomeBet = column[Boolean]("IS_HOME_BET")

    def spread = column[Int]("SPREAD")

    def amount = column[Int]("AMOUNT")

    def * = (id, bettorId, gameId, isHomeBet, spread, amount)

    def bettor: ForeignKeyQuery[Users, (Long, String, Int)] =
      foreignKey("BB_FK", bettorId, TableQuery[Users])(_.id)

    def game: ForeignKeyQuery[Games, (Long, Long, Long, LocalDateTime, Option[Long], String)] =
      foreignKey("GG_FK", gameId, TableQuery[Games])(_.id)
  }


  object DAO {
    val bets = TableQuery[Bets]
    val games = TableQuery[Games]
    val offers = TableQuery[Offers]
    val results = TableQuery[Results]
    val teams = TableQuery[Teams]
    val users = TableQuery[Users]

    val schema = users.schema ++ teams.schema ++ games.schema ++ results.schema ++ bets.schema ++ offers.schema

    def showCreate() = schema.createStatements.foreach(println(_))

    def showDrop() = schema.createStatements.foreach(println(_))

    def create(db: Database) = {
      db.run(DBIO.seq(schema.create))
    }

    def drop(db: Database) = {
      db.run(DBIO.seq(schema.drop))
    }
  }

}

/*

object Main {
  def main(args: Array[String]) {
    print("Hello")
    val dbModel: DbModel = new DbModel(H2Driver)
    dbModel.DAO.showCreate()
    dbModel.DAO.create(JdbcBackend.Database.forURL("jdbc:h2:mem:test1", driver = "org.h2.Driver"))
  }
}

*/



