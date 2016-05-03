package com.fijimf.deepfij.scrape.request

import spray.json.JsValue

trait JsonScrapeRequest[T] {
  def url: String

  def preProcessBody(s:String):String = s

  def scrape(js:JsValue): T
}