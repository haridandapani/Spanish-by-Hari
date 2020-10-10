package hari.spanish.gui;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class HomeGUI implements TemplateViewRoute {
  @Override
  public ModelAndView handle(Request request, Response response) throws Exception {
    Map<String, Object> variables = ImmutableMap.of();// ImmutableMap.<String,
                                                      // Object>builder().put().build();
    return new ModelAndView(variables, "query.ftl");
  }

}
