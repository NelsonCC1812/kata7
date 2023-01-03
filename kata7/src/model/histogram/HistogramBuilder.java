package model.histogram;

import model.db.schema.Loader;

public class HistogramBuilder<T> {

    public HistogramBuilder() {

    }

    public Histogram<T> build(Loader<T> ld) throws Exception {
        Histogram<T> histogram = new Histogram<>();

        while (ld.next())
            histogram.increment(ld.get());

        return histogram;
    }
}
