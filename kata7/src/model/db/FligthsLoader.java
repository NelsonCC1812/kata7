package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import model.db.ResultSetLoader.ResultSetLoaderException;
import model.db.result_set.SqLiteResultSetFlightsApi;
import model.db.result_set.SqLiteResultSetFlightsApi.SqLiteFlightsApiException;
import model.db.schema.FlightField;
import model.db.schema.Loader;

public class FligthsLoader implements Loader<Integer> {

    private Loader<ResultSet> rs;
    private SqLiteResultSetFlightsApi api;
    private FlightField dimension;
    private Map<FlightField, Integer> filters;
    private String url;

    public static FligthsLoader from(String url) {
        return new FligthsLoader(url);
    }

    public FligthsLoader setDimension(FlightField field) {
        this.dimension = field;
        return this;
    }

    public FligthsLoader setFilters(Map<FlightField, Integer> filters) {
        this.filters = filters;
        return this;
    }

    public void disconnect() throws FligthsLoaderException {
        try {
            api.disconnect();
        } catch (SQLException e) {
            throw new FligthsLoaderException(e.getMessage());
        }
    }

    public FligthsLoader(String url) {
        this.url = url;
    }

    public void connect() throws FligthsLoaderException {
        try {
            this.api = new SqLiteResultSetFlightsApi(url);
            rs = filters.keySet().size() > 0 ? ResultSetLoader.from(api.find(filters))
                    : ResultSetLoader.from(api.findAllFlights());
        } catch (SqLiteFlightsApiException e) {
            throw new FligthsLoaderException(e.getMessage());
        }
    }

    public boolean next() throws Exception {
        try {
            return this.rs.next();
        } catch (SQLException e) {
            throw new FligthsLoaderException(e.getMessage());
        }
    }

    public Integer get() throws Exception {

        try {
            return this.rs.get().getInt(this.dimension.name());
        } catch (SQLException | ResultSetLoaderException e) {
            throw new FligthsLoaderException(e.getMessage());
        }
    }

    public static class FligthsLoaderException extends Exception {
        private String msg;

        FligthsLoaderException(String msg) {
            this.msg = msg;
        }

        public String getMessage() {
            return this.msg;
        }
    }

}
