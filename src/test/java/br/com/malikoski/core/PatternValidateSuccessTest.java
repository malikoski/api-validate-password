package br.com.malikoski.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class PatternValidateSuccessTest {

    private Pattern pattern;

    @BeforeEach
    public void init() {
        pattern = Pattern.compile(AppConstant.PATTERN_PASSWORD);
    }

    @Test
    @DisplayName("Deve validar por estar de acordo com todas as regras - Ex: AbTp9!fok")
    public void shouldValidateWithAllRules() {
        var matcher = pattern.matcher("AbTp9!fok");
        assertThat(matcher.find()).isTrue();
    }

    @Test
    @DisplayName("Deve validar por conter mais caracteres do que o mínimo permitido - Ex: AbTp9!fokG&%l")
    public void shouldValidateWhenMoreThanAllowedMin() {
        var matcher = pattern.matcher("AbTp9!fokG&%l");
        assertThat(matcher.find()).isTrue();
    }
}
