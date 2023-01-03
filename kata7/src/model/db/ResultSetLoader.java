package model.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.db.schema.Loader;

public class ResultSetLoader implements Loader<ResultSet> {

    private ResultSet rs;

    private ResultSetLoader(ResultSet rs) {
        this.rs = rs;
    }

    public static ResultSetLoader from(ResultSet rs) {
        return new ResultSetLoader(rs);
    }

    @Override
    public boolean next() throws ResultSetLoaderException {

        try {
            return rs.next();
        } catch (SQLException e) {
            throw new ResultSetLoaderException(e.getMessage());
        }
    }

    @Override
    public ResultSet get() {
        return this.rs;
    }

    public static class ResultSetLoaderException extends Exception {

        private String msg;

        public ResultSetLoaderException(String msg) {
            this.msg = msg;
        }

        public String getMessage() {
            return this.msg;
        }
    }

}
