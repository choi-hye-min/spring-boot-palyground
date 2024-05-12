package com.example.testweb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.text.ParseException;

@Slf4j
public class MessageFormatTest {
    @DisplayName("메세지 포맷 파서 테스트")
    @Test
    void mesageFormatParseTest() throws ParseException {
        String format = "이름 : {0}, 나이 : {1}";
        String message = "이름 : 홍길동, 나이 : 20";
        MessageFormat messageFormat = new MessageFormat(format);
        Object[] messageArguments = messageFormat.parse(message);

        Assertions.assertAll(() -> {
            Assertions.assertEquals(messageArguments[0].toString(), "홍길동");
            Assertions.assertEquals(messageArguments[1].toString(), "20");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"일", "이", "삼"})
    void shouldMatchString(String s) throws ParseException {
        String format = "숫자는 - {0}";

        String bindString = MessageFormat.format(format, s);
        Object[] parse = new MessageFormat(format).parse(bindString);

        Assertions.assertEquals(s, parse[0].toString());
    }
}