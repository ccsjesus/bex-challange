package br.com.challange.bex.bexchallange.util;

import java.util.HashMap;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeResult {

  private Result result;

  @Data
  public class Result {

    @ApiModelProperty("Código de retorno")
    private int cdRetorno;

    @ApiModelProperty("Mensagem técnica contendo exceções")
    private String msgTecnica;

    @ApiModelProperty("Mensagem informativa para o usuário")
    private String msgUsuario = new String();

    @ApiModelProperty("Objeto de retorno")
    private Object dados = new HashMap<>();

  }

}