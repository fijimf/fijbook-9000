package example

import org.scalajs.dom
import org.scalajs.dom.html

import scalatags.JsDom.all._
import scala.scalajs.js.annotation.JSExport


@JSExport
object JimJS {
  @JSExport
  def main(hh:html.Html) = {
    hh.appendChild(h1("Fridge Rules").render)
  }

}
