package fi.relex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class SampleSet {

    public final List<Sample> samples;
    public final Map<String, Integer> sampleMapping;

    public SampleSet(List<Sample> samples, Map<String, Integer> sampleMapping) {
        this.samples = Collections.unmodifiableList(new ArrayList<>(samples));
        this.sampleMapping = Collections.unmodifiableMap(new HashMap<>(sampleMapping));
    }
}
