package io.github.ysdaeth.jccredentials.jcc.common.serializer;

import io.github.ysdaeth.jccredentials.jcc.common.converter.TypeConverter;
import io.github.ysdaeth.jccredentials.jcc.common.parser.Parser;

public interface SerializerConfiguration {
    TypeConverter typeConverter();
    Parser parser();
}
