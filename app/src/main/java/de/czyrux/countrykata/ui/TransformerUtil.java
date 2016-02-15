package de.czyrux.countrykata.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransformerUtil {

    private TransformerUtil() { }

    public static <T, M> List<T> transform(List<M> models, Transformer<M, T> transformer) {
        if (models == null || models.isEmpty()) {
            return Collections.emptyList();
        }

        List<T> transformedModels = new ArrayList<>(models.size());
        for (M model : models) {
            T transformed = transformer.transform(model);
            if (transformed != null) {
                transformedModels.add(transformed);
            }
        }

        return transformedModels;
    }
}
