package fi.relex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 */
public class DataLoader {

    private final static String DATE = "date";
    private final static String QTY = "sales_quantity";

    static SampleSet loadData(InputStream inputStream) throws IOException {
        try (Reader r = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
             BufferedReader br = new BufferedReader(r)){
            CSVParser parser = new CSVParser(br, CSVFormat.DEFAULT.withHeader());
            Map<String, Integer> headerMap = parser.getHeaderMap();
            if (!headerMap.containsKey(DATE) || !headerMap.containsKey(QTY)) throw new IOException("Corrupted csv, no date column found");
            Map<String, Integer> outputMapping = new HashMap<>();
            List<Sample> samples = new ArrayList<>();
            headerMap.keySet().stream().filter(k -> !(DATE.equals(k) || QTY.equals(k))).forEach(new Consumer<String>() {
                int index = 0;
                @Override
                public void accept(String s) {
                    outputMapping.put(s, index++);
                }
            });
            for (CSVRecord record : parser) {
                if (!record.isConsistent()) {
                    throw new IOException("Row " + record.toString() + " is inconsistent");
                }
                LocalDate date = null;
                double qty = 0.0;
                double[] data = new double[outputMapping.size()];
                for (String header : headerMap.keySet()) {
                    String value = record.get(header);
                    if (DATE.equals(header)) {
                        date = LocalDate.parse(value);
                    } else {
                        double num = Double.parseDouble(value);
                        if (QTY.equals(header)) {
                            qty = num;
                        } else {
                            data[outputMapping.get(header)] = num;
                        }
                    }
                }
                samples.add(new Sample(qty, data, date));
            }
            return new SampleSet(samples, outputMapping);
        }
    }

}
