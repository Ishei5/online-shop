package com.pankov.roadtosenior.onlineshop.dao.jdbc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryHandler {

    private static final Logger LOG = LoggerFactory.getLogger(QueryHandler.class);

    public static String handleQuery(String query, Object ... params) {
        final String searchSymbol = "?";
        if (params.length != StringUtils.countMatches(query, searchSymbol)) {
            LOG.warn("Count of provided parameters and its quantity in query is difference");
            throw new RuntimeException("Count of provided parameters and its quantity in query is difference");
        }
        StringBuilder stringBuilder = new StringBuilder(query);
        int startIndex = stringBuilder.indexOf(searchSymbol);
        int parameterIndex = 0;

        while (startIndex >= 0) {
            stringBuilder.replace(startIndex, startIndex + 1,
                    prepareParameter(String.valueOf(params[parameterIndex])));
            startIndex = stringBuilder.indexOf(searchSymbol, startIndex + 1);
            parameterIndex++;
        }

        return stringBuilder.toString();
    }

    private static String prepareParameter(String parameter) {
        return "'" + parameter.replace("'", "\"") + "'";
    }

}
