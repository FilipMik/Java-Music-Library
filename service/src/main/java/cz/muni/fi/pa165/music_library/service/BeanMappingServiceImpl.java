package cz.muni.fi.pa165.music_library.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Filip Mik on 14. 11. 2018
 */

@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    private final Mapper dozer;

    @Autowired
    public BeanMappingServiceImpl(Mapper dozer) {
        this.dozer = dozer;
    }

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object obj : objects) {
            mappedCollection.add(dozer.map(obj, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return dozer.map(u, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return dozer;
    }
}
