package br.com.malikoski.api;

import br.com.malikoski.core.AppConstant;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Pattern;

public interface PasswordValidatorController {

    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Erro no servidor"),
            @ApiResponse(code = 404, message = "Serviço não encontrado"),
            @ApiResponse(code = 200, message = "Senha validada com sucesso",
                    response = Boolean.class)})
    @ApiOperation(value = "Verifica se uma senha passada, é válida", notes = "\n" +
            "    Regras de validações:\n\n" +
            "    - Nove ou mais caracteres\n" +
            "    - Ao menos 1 dígito\n" +
            "    - Ao menos 1 letra minúscula\n" +
            "    - Ao menos 1 letra maiúscula\n" +
            "    - Ao menos 1 caractere especial: !@#$%^&*()-+\n" +
            "    - Não possuir caracteres repetidos dentro do conjunto\n")
    public String checkPasswordValid(@RequestParam("password")
                                         @Pattern(regexp = AppConstant.PATTERN_PASSWORD, message = "false")
                                                 String password);

}
