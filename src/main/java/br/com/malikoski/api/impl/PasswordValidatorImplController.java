package br.com.malikoski.api.impl;

import br.com.malikoski.api.PasswordValidatorController;
import br.com.malikoski.core.AppConstant;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/password")
@Validated
@Api("PasswordValidator API")
@Slf4j
public class PasswordValidatorImplController implements PasswordValidatorController {

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String checkPasswordValid(@RequestParam("password")
                                     @Pattern(regexp = AppConstant.PATTERN_PASSWORD, message = "false")
                                             String password) {
        log.info("I=checkPasswordValid, M=Senha validada com sucesso!");
        return Boolean.TRUE.toString();
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        log.info("I=handleConstraintViolation_checkPasswordValid, M=Senha inválida!");

        var violation = ex.getConstraintViolations().iterator().next();
        return new ResponseEntity<Object>(violation.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
