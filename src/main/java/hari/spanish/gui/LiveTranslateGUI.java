package hari.spanish.gui;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class LiveTranslateGUI implements Route {

  private static final int CUTOFF = 200;

  @Override
  public Object handle(Request request, Response response) throws Exception {
    QueryParamsMap qm = request.queryMap();
    String word = qm.value("word");
    String translation = getTranslation(word);
    return new Gson().toJson(ImmutableMap.of("word", translation));
  }

  private String getTranslation(String word) {
    String url = "https://www.spanishdict.com/translate/" + word;
    String output = "";
    try {
      Document doc = Jsoup.connect(url).get();
      Element spanish = doc.getElementById("quickdef1-es");
      Element english = doc.getElementById("headword-es");
      if (spanish != null && english != null) {
        output = english.text() + ": " + spanish.text();
      } else {
        if (word.length() < CUTOFF) {
          output = "<a href = \"" + url + "?langFrom=es\" target = \"blank\" ><u>" + word
              + "</u></a>";
        } else {
          output = "<a href = \"" + url + "?langFrom=es\" target = \"blank\" ><u>"
              + word.substring(0, CUTOFF) + "</u></a>";
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      output = "Could not generate live translation for: " + word;
      output = output.substring(0, CUTOFF);
    }
    return output;
  }

}
