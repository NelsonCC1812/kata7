package model.db.schema.result_set;

import java.sql.ResultSet;

public interface ResultSetFlightsApi {
    public ResultSet findAllFlights();

    public ResultSet findFlightsBydayOfWeek(int dayOfWeek);

    public ResultSet findFlightsByDepTime(int depTime);

    public ResultSet findFlightByDepDelay(int depDelay);

    public ResultSet findFlightByArrTime(int arrTime);

    public ResultSet findFlightByArrDelay(int arrDelay);

    public ResultSet findFlightByCancelled(int cancelled);

    public ResultSet findFlightByDiverted(int diverted);

    public ResultSet findFlightByAirTime(int airTime);

    public ResultSet findFlightByDistance(int distance);
}
