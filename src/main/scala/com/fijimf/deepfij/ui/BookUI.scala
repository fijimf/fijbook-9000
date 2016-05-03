package com.fijimf.deepfij.ui

import com.fijimf.deepfij.model.User
import org.scalajs.dom
import org.scalajs.dom.html

import scalatags.JsDom.all._
import scala.scalajs.js.annotation.JSExport


@JSExport
object BookUI {
  val user = User(99, "Jim Frohnhofer", 999999)

  @JSExport
  def main(hh: html.Html) = {
    hh.appendChild(h1(user.name+ " Rules").render)
  }


}
