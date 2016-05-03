package com.fijimf.deepfij.scrape.request

import scala.xml.Node

trait HtmlScrapeRequest[T] {
  def url: String

  def scrape(n: Node): T
}