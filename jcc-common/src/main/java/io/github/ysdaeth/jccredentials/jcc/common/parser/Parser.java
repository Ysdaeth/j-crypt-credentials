package io.github.ysdaeth.jccredentials.jcc.common.parser;

public interface Parser {
    String compose(Section[] sections);
    Section[] parse(String computed);
}
