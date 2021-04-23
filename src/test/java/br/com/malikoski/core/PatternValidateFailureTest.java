package br.com.malikoski.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class PatternValidateFailureTest {

    private Pattern pattern;

    @BeforeEach
    public void init() {
        pattern = Pattern.compile(AppConstant.PATTERN_PASSWORD);
    }

    @Test
    @DisplayName("Não deve permitir vazio - Ex: ''  ")
    public void shouldInvalidForEmptyText() {
        var matcher = pattern.matcher("");
        assertThat(matcher.find()).isFalse();
    }

    @Test
    @DisplayName("Não validar caracteres repetidos sequencialmente - Ex: B1Adaa902&H")
    public void shouldInvalidForRepeteadText() {
        var matcher = pattern.matcher("B1Adaa902&H");
        assertThat(matcher.find()).isFalse();
    }

    @Test
    @DisplayName("Não validar caracteres repetidos no conjunto - Ex: B1Ada902a&H")
    public void shouldInvalidForRepeteadCharInSet() {
        var matcher = pattern.matcher("B1Ada902a&H");
        assertThat(matcher.find()).isFalse();
    }

    @Test
    @DisplayName("Não validar tamanho menor que o mínimo permitido - Ex: 12345678")
    public void shouldInvalidForLessMinimunLenght() {
        var matcher = pattern.matcher("12345678");
        assertThat(matcher.find()).isFalse();
    }

    @Test
    @DisplayName("Não validar se não conter ao menos um dígito - Ex: aBcDefGh&")
    public void shouldInvalidForDoesntLeastOneDigit() {
        var matcher = pattern.matcher("aBcDefGh&");
        assertThat(matcher.find()).isFalse();
    }

    @Test
    @DisplayName("Não validar se não conter ao menos um caracter especial(!@#$%^&*()-+) - Ex: aBcDefGh1")
    public void shouldInvalidForDoesntLeastOneSpecialChar() {
        var matcher = pattern.matcher("aBcDefGh1");
        assertThat(matcher.find()).isFalse();
    }

    @Test
    @DisplayName("Não validar se não conter ao menos um caracter minúsculo - Ex: ABCDEFG1%")
    public void shouldInvalidForDoesntLeastOneUppercaseChar() {
        var matcher = pattern.matcher("ABCDEFG1%");
        assertThat(matcher.find()).isFalse();
    }

    @Test
    @DisplayName("Não validar se não conter ao menos um caracter maísculo - Ex: abcdefg1%")
    public void shouldInvalidForDoesntLeastOneLowercaseChar() {
        var matcher = pattern.matcher("abcdefg1%");
        assertThat(matcher.find()).isFalse();
    }

    @Test
    @DisplayName("Não validar se conter espaços - Ex: Abc efg1%")
    public void shouldInvalidForSpaces() {
        var matcher = pattern.matcher("Abc efg1%");
        assertThat(matcher.find()).isFalse();
    }

}
