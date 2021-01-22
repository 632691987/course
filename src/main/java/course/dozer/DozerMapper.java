package course.dozer;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

public class DozerMapper {

    private final Mapper mapper;

    public DozerMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public <T> List<T> map(List<?> source, Class<T> destinationClass) {
        final List<T> target = new ArrayList<>();

        for (final Object element : source)
            target.add(mapper.map(element, destinationClass));

        return target;
    }

    public <T> T map(Object source, Class<T> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    public <T> T map(Object source, Class<T> destinationClass, String mapId) {
        return mapper.map(source, destinationClass, mapId);
    }

    public void map(Object source, Object destination) {
        mapper.map(source, destination);
    }

    public void map(Object source, Object destination, String mapId) {
        mapper.map(source, destination, mapId);
    }

}