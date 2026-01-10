package io.github.ysdaeth.jccredentials.jcc.common.serializer;

import io.github.ysdaeth.jccredentials.jcc.common.parser.Section;

import java.util.List;
import java.util.function.Function;

final class McfClassSerializer {
    private final List<ModuleAccessor> modules;
    private final Function<Object,Section[]> serializer;
    private final Function<Section[],Object> deserializer;
    public McfClassSerializer(List<ModuleAccessor> modules,
                              Function<Object, Section[]> serializer,
                              Function<Section[], Object> deserializer) {
        this.modules = modules;
        this.serializer = serializer;
        this.deserializer = deserializer;
    }


    public Object deserialize(Section[] parsed) {
        if(modules.size() != parsed.length){
            throw new IllegalArgumentException("Sections length does not match modules length");
        }
        return deserializer.apply(parsed);
    }


    public Section[] serialize(Object object) {
        Section[] result = serializer.apply(object);
        if(result.length != modules.size()){
            throw new IllegalArgumentException(
                    "Deserialized fields length does not match with modules length");
        }
        return result;
    }



}
