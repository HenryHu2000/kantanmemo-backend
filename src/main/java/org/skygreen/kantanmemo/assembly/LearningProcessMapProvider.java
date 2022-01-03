package org.skygreen.kantanmemo.assembly;

import org.skygreen.kantanmemo.data.LearningProcess;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class LearningProcessMapProvider {
    private final Map<Long, LearningProcess> learningProcessMap = Collections.synchronizedMap(new HashMap<>());

    @Produces
    public Map<Long, LearningProcess> learningProcessMap(InjectionPoint injectionPoint) {
        return learningProcessMap;
    }

}
