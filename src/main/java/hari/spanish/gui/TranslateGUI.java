package hari.spanish.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class TranslateGUI implements TemplateViewRoute {
  @Override
  public ModelAndView handle(Request request, Response response) throws Exception {
    QueryParamsMap vars = request.queryMap();
    String texter = vars.value("texter");
    Map<String, Object> variables = ImmutableMap.<String, Object>builder()
        .put("value", makeOutputString(texter)).build();
    return new ModelAndView(variables, "translate.ftl");
  }

  private String makeOutputString(String texter) {
    List<String> finalOutput = new ArrayList<>();
    String[] enterSplit = texter.split("\n");
    for (String stringer : enterSplit) {

      while (stringer.indexOf("  ") != -1) {
        stringer = stringer.replaceAll("  ", " ");
      }

      String[] spaceSplit = stringer.split(" ");
      List<String> outputter = new ArrayList<>();

      for (String s : spaceSplit) {
        StringBuilder temper = new StringBuilder();
        temper.append("<a href = \"https://www.spanishdict.com/translate/");
        temper.append(s);
        temper.append("?langFrom=es\" target = \"blank\" onmouseover=\"getTranslation(this);\">");
        temper.append(s);
        temper.append("</a>");
        outputter.add(temper.toString());
      }

      finalOutput.add(listToString(outputter));
      finalOutput.add("<br>");
    }
    return listToString(finalOutput);
  }

  private String listToString(List<String> lister) {
    StringBuffer sb = new StringBuffer();

    for (String s : lister) {
      sb.append(s);
      sb.append(" ");
    }
    return sb.toString();
  }
}
