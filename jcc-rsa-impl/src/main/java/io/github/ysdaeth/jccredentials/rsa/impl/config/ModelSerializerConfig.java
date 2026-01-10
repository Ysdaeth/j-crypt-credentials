package io.github.ysdaeth.jccredentials.rsa.impl.config;

import io.github.ysdaeth.jccredentials.jcc.common.converter.BasicTypeConverter;
import io.github.ysdaeth.jccredentials.jcc.common.converter.TypeConverter;
import io.github.ysdaeth.jccredentials.jcc.common.parser.McfParser;
import io.github.ysdaeth.jccredentials.jcc.common.parser.Parser;
import io.github.ysdaeth.jccredentials.jcc.common.serializer.SerializerConfiguration;

public final class ModelSerializerConfig implements SerializerConfiguration {
    @Override
    public TypeConverter typeConverter() {
        return new BasicTypeConverter();
    }
    @Override
    public Parser parser() {
        return new McfParser();
    }
}
