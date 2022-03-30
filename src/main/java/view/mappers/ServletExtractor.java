package view.mappers;

import models.BaseModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@FunctionalInterface
public interface ServletExtractor<T extends BaseModel> {

    T extract(HttpServletRequest request) throws ServletException, UnsupportedEncodingException;
}
