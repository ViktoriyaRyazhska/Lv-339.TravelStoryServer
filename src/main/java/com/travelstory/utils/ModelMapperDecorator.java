package com.travelstory.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperDecorator {

    private ModelMapper modelMapper;

    @Autowired
    public ModelMapperDecorator(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param source
     *            object to map from
     * @param destination
     *            object to map to
     */
    public <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

    /**
     * <p>
     * Note: outClass object must have default constructor with no arguments
     * </p>
     *
     * @param <D>
     *            type of result object.
     * @param <E>
     *            type of source object to map from.
     * @param entity
     *            entity that needs to be mapped.
     * @param outClass
     *            class of result object.
     * @return new object of <code>outClass</code> type.
     */
    public <D, E> D map(final E entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /**
     * <p>
     * Note: outClass object must have default constructor with no arguments
     * </p>
     *
     * @param sourceList
     *            list of entities that needs to be mapped
     * @param outClass
     *            class of result list element
     * @param <D>
     *            type of objects in result list
     * @param <S>
     *            type of entity in <code>entityList</code>
     * @return list of mapped object with <code><D></code> type.
     */
    public <D, S> List<D> mapAll(final List<S> sourceList, Class<D> outClass) {
        return sourceList.stream().map(source -> map(source, outClass)).collect(Collectors.toList());
    }
}
