package ucles.weblab.common.webapi.resource;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A wrapper around a list of resources which allows links to be added.
 *
 * @deprecated Use {@link org.springframework.hateoas.CollectionModel} instead
 */
public class ResourceListWrapper<T> extends RepresentationModel<ResourceListWrapper<T>> {
    private final List<T> list;

    ResourceListWrapper(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public static <T> ResourceListWrapper<T> wrap(List<T> list) {
        return new ResourceListWrapper<>(list);
    }

    public static <T> Collector<T, ?, ResourceListWrapper<T>> toResourceList() {
        return Collectors.collectingAndThen(Collectors.<T>toList(), ResourceListWrapper::new);
    }
}
