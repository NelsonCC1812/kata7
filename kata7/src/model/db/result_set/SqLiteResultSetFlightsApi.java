package model.db.result_set;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

import model.db.schema.FlightField;

public class SqLiteResultSetFlightsApi {
    private String url;
    private Connection conn;
    private Statement stmt;

    public SqLiteResultSetFlightsApi(String url) {
        this.url = url;
    }

    private void connect() throws SQLException {

        this.conn = DriverManager.getConnection(url);
        this.stmt = conn.createStatement();

    }

    public void disconnect() throws SQLException {
        this.conn.close();
    }

    private ResultSet rs(String query) throws SqLiteFlightsApiException {
        try {
            connect();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new SqLiteFlightsApiException(e.getMessage());
        }
    }

    public static class SqLiteFlightsApiException extends Exception {
        private String msg;

        public SqLiteFlightsApiException(String msg) {
            this.msg = msg;
        }

        public String getMessage() {
            return msg;
        }

        public String message() {
            return msg;
        }
    }

    // *=> API

    public ResultSet findAllFlights() throws SqLiteFlightsApiException {
        return rs("select * from flights;");

    }

    public ResultSet find(Map<FlightField, Integer> filters) throws SqLiteFlightsApiException {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from flights where ");

        Iterator<FlightField> itr = filters.keySet().iterator();

        while (itr.hasNext()) {
            FlightField f = itr.next();
            sb.append(String.format("%s=%d", f.name(), filters.get(f)));
            if (itr.hasNext())
                sb.append(" AND ");
        }
        sb.append(";");

        return rs(sb.toString());
    }
}
