
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import dto.HistogramDTO;
import model.db.FligthsLoader;
import model.db.schema.FlightField;
import model.histogram.Histogram;
import model.histogram.HistogramBuilder;

import spark.Spark;

// TODO: tienes que crear una aplicacion que:
// [ ]: tome peticiones http
// [ ]: devuelva un array con el histograma en formato json
// [ ]: puedes tomar un dia concreto o todos los dias
// [ ]: toma los datos de la base de datos

// NOTE: String name = req.queryParams("name");
/*
 * Spark.port(80);
 * Spark.get("/flights", Main::flights);
 * 
 * private static String flights(Request eq, Response res){
 * 
 * }
 */

public class Kata7 {
    public static void main(String[] args) {

        Spark.port(80);
        HistogramBuilder<Integer> hBuilder = new HistogramBuilder<>();
        FligthsLoader ld = FligthsLoader.from("jdbc:sqlite:flights.db");
        Gson gson = new Gson();

        Spark.get("/", (req, res) -> {

            System.out.println("todo bien");
            try {
                String dimension = req.queryParams("dimension");

                Map<FlightField, Integer> map = new HashMap<>();

                for (FlightField ff : FlightField.values())
                    if (req.queryParams().contains(ff.name()))
                        map.put(ff, Integer.parseInt(req.queryParams(ff.name())));

                ld.setDimension(FlightField.valueOf(dimension));
                ld.setFilters(map);

                ld.connect();
                Histogram<Integer> histogram = hBuilder.build(ld);
                ld.disconnect();

                return gson.toJson(new HistogramDTO(dimension, map, histogram.keySet(), histogram.values()));

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return "algo";
        });

    }

}