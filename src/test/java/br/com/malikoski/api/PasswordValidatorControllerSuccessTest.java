package br.com.malikoski.api;


import br.com.malikoski.api.impl.PasswordValidatorImplController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {PasswordValidatorImplController.class})
@AutoConfigureMockMvc
public class PasswordValidatorControllerSuccessTest {

    static String PASSWORD_API = "/api/password";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Dado que seja a senha seja informada com todas as regras mínimas necessárias " +
                 " Quando efetuar a requisição de POST " +
                 " Então deve retornar 'true' ")
    public void successTest() throws Exception {

        var passwordValidate = "B1Ada902&H";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Boolean.TRUE.toString()));
    }

    @Test
    @DisplayName("Dado que seja a senha seja informada com os caracteres/símbolos especias suportados" +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'true' ")
    public void successWithAllSpecialChars() throws Exception {

        var passwordValidate = "B1Ada902H!@#$%^&*()-+";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Boolean.TRUE.toString()));
    }

    @Test
    @DisplayName("Dado que seja a senha seja informada com dígitos de 0 a 9" +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'true' ")
    public void successWithAllNumericChars() throws Exception {

        var passwordValidate = "aB@0123456789";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Boolean.TRUE.toString()));
    }

    @Test
    @DisplayName("Dado que seja a senha seja informada com caracteres de 'a - z' minúsculas" +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'true' ")
    public void successWithAllLowercaseChars() throws Exception {

        var passwordValidate = "A1@abcdefghijklmnopqrtuvxywz";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Boolean.TRUE.toString()));
    }

    @Test
    @DisplayName("Dado que seja a senha seja informada com caracteres de 'a - z' maiúsculas" +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'true' ")
    public void successWithAllUppercaseChars() throws Exception {

        var passwordValidate = "a1@ABCDEFGHIJKLMNOPQRTUVXYWZ";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Boolean.TRUE.toString()));
    }
}
