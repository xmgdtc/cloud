package org.xmgdtc.common.mapper;


import ma.glasnost.orika.DefaultFieldMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.TypeFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CommonMapper {
    private MapperFactory factory;

    public <T> T map(Object source, Class<T> destination, String... excludes) {
        return map(source, destination, null, excludes);
    }

    public <T> List<T> mapList(List<?> source, Class<T> destination, String... excludes) {
        return mapList(source, destination, null, excludes);
    }

    public <T> Set<T> mapSet(Set<?> source, Class<T> destination, String... excludes) {
        return mapSet(source, destination, null, excludes);
    }


    public <T> T map(Object source, Class<T> destination, Map<String, String> fieldMap, String... excludes) {
        T result = null;

        if (null != source) {
            MapperFacade mapper = this.getMapper(source, destination, fieldMap, excludes);
            result = mapper.map(source, destination);
        }
        return result;
    }

    public <T> List<T> mapList(List<?> source, Class<T> destination, Map<String, String> fieldMap, String... excludes) {
        List<T> result = Collections.emptyList();

        if (!CollectionUtils.isEmpty(source)) {
            MapperFacade mapper = this.getMapper(source.get(0).getClass(), destination, fieldMap, excludes);
            result = mapper.mapAsList(source, destination);
        }
        return result;
    }

    public <T> Set<T> mapSet(Set<?> source, Class<T> destination, Map<String, String> fieldMap, String... excludes) {
        Set<T> result = Collections.emptySet();
        if (!CollectionUtils.isEmpty(source)) {
            MapperFacade mapper = this.getMapper(source.iterator().next().getClass(), destination, fieldMap, excludes);
            result = mapper.mapAsSet(source, destination);
        }
        return result;
    }

    private MapperFacade getMapper(Object source, Class destination, Map<String, String> fieldMap, String... excludes) {
        factory = new DefaultMapperFactory.Builder().build();

        boolean flag = this.factory.existsRegisteredMapper(TypeFactory.valueOf(source.getClass()), TypeFactory.valueOf(destination), false);
        if (!flag) {
            ClassMapBuilder classMapBuilder = this.factory.classMap(source.getClass(), destination).mapNulls(false).mapNullsInReverse(false).byDefault(new DefaultFieldMapper[0]);

            //映射字段
            if (!CollectionUtils.isEmpty(fieldMap)) {
                fieldMap.forEach(classMapBuilder::field);
            }

            //排除字段
            if (excludes != null) {
                Arrays.asList(excludes).forEach(classMapBuilder::exclude);
            }

            classMapBuilder.register();

        }
        return this.factory.getMapperFacade();
    }


}