package com.fijimf.deepfij.model

import java.time.LocalDateTime

case class Game(id: Long, homeTeam: Team, awayTeam: Team, date: LocalDateTime, location: String, result: Option[Result])

case class Result(id: Long, homeScore: Int, awayScore: Int)

case class Team(id: Long, name: String, nickname: String, logoUrl: String)

case class User(id: Long, name: String, balance: Int)

case class Bet(id: Long, homeBettor: User, awayBettor: User, game: Game, doubleSpread: Int, amount: Int)

case class Offer(id: Long, bettor: User, amount: Int)

case class Book(id: Long, game: Game, offers: List[Offer], bets: List[Bet])

sealed trait HomeAway

case object Home extends HomeAway

case object Away extends HomeAway