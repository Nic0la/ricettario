package com.exercise.uno.mapper;

import java.util.IdentityHashMap;
import java.util.Map;
@Deprecated
public class CycleAvoidingMappingContext {

    private Map<Object, Object>knownInstances = new IdentityHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getMappedInstance(Object source, Class<T> targetType) {
        return (T) knownInstances.get(source);
    }

    public void storeMappedInstance(Object source, Object target) {
        knownInstances.put(source, target);
    }
}
