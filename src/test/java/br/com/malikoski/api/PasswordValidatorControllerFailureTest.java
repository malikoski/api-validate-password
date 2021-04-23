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
public class PasswordValidatorControllerFailureTest {

    static String PASSWORD_API = "/api/password";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Dado que seja a senha seja informada menor do que a quantidade mínima de caracteres" +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'false' ")
    public void failureForLessMininumTest() throws Exception {

        var passwordValidate = "B1Ada92&";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Boolean.FALSE.toString()));
    }

    @Test
    @DisplayName("Dado que não seja informado nenhum caractere/símbolo especial suportado " +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'false' ")
    public void failureWithoutSpecialChar() throws Exception {

        var passwordValidate = "B1Ada92gh";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Boolean.FALSE.toString()));
    }

    @Test
    @DisplayName("Dado que não seja informado nenhum dígito " +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'false' ")
    public void failureWithoutDigitChar() throws Exception {

        var passwordValidate = "B&Adakmgh";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Boolean.FALSE.toString()));
    }

    @Test
    @DisplayName("Dado que não seja informado nenhum caracter minúsculo " +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'false' ")
    public void failureWithoutLowercaseChar() throws Exception {

        var passwordValidate = "B&AD8KMGH";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Boolean.FALSE.toString()));
    }

    @Test
    @DisplayName("Dado que não seja informado nenhum caracter maiúsculo " +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'false' ")
    public void failureWithoutUppercaseChar() throws Exception {

        var passwordValidate = "b&ad8kmgh";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Boolean.FALSE.toString()));
    }

    @Test
    @DisplayName("Dado que seja informado espaço(s) " +
            " Quando efetuar a requisição de POST " +
            " Então deve retornar 'false' ")
    public void failureWithSpaces() throws Exception {

        var passwordValidate = "b&ad8 kmgh";

        this.mockMvc.perform(post(PASSWORD_API)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .param("password", passwordValidate))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Boolean.FALSE.toString()));
    }
}
