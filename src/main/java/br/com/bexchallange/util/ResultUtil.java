package br.com.bexchallange.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.bexchallange.util.EntidadeResult.Result;

@Component
public class ResultUtil {

  // Mensagens de retorno
  public static final String MENSAGEM_SUCESSO = "Operação realizada com sucesso";
  public static final String MENSAGEM_ERRO = "Não foi possível realizar a operação";

  public ResponseEntity<EntidadeResult> resultSucesso(HttpStatus httpStatus, String msgUsuario) {
    EntidadeResult result = entidadeResult(httpStatus.value(), "", msgUsuario, new HashMap<>());
    return ResponseEntity.status(httpStatus).body(result);
  }

  public ResponseEntity<EntidadeResult> resultSucesso(HttpStatus httpStatus, String msgUsuario, Object dados) {
    EntidadeResult result = entidadeResult(httpStatus.value(), "", msgUsuario, dados);
    return ResponseEntity.status(httpStatus).body(result);
  }

  public ResponseEntity<EntidadeResult> resultErro(HttpStatus httpStatus, String msgUsuario) {
    return resultErro(httpStatus, "", msgUsuario);
  }

  public ResponseEntity<EntidadeResult> resultErro(HttpStatus httpStatus, Throwable msgTecnica, String msgUsuario) {
    return resultErro(httpStatus, msgTecnica.toString(), msgUsuario);
  }

  private ResponseEntity<EntidadeResult> resultErro(HttpStatus httpStatus, String msgTecnica, String msgUsuario) {
    EntidadeResult result = entidadeResult(httpStatus.value(), msgTecnica, msgUsuario, null);
    return ResponseEntity.status(httpStatus).body(result);
  }

  private EntidadeResult entidadeResult(int cdRetorno, String msgTecnica, String msgUsuario, Object dados) {
    Result result = new EntidadeResult().new Result();
    result.setCdRetorno(cdRetorno);

    if (msgUsuario != null) {
      result.setMsgUsuario(msgUsuario);
    }

    if (msgTecnica != null) {
      result.setMsgTecnica(msgTecnica);
    }

    if (dados != null) {
      if (isArrayList(dados)) {
        dados = obtemDadosArrayList((ArrayList<?>)dados);
      }
      result.setDados(dados);
    }

    return new EntidadeResult(result);
  }

  private boolean isArrayList(Object object) {
    return (object instanceof ArrayList);
  }

  private Object obtemDadosArrayList(ArrayList<?> arrayList) {
    Object dados = new HashMap<>();

    // Verifica se o objeto possui conteúdo
    if (arrayList.size() > 0) {
      dados = arrayList;
    }

    return dados;
  }

  // Linhas Afetadas
  public Map<String, Integer> linhasAfetadas(Integer quantidade) {
    Map<String, Integer> linhasAfetadas = new HashMap<>();
    linhasAfetadas.put("linhasAfetadas", quantidade);
    return linhasAfetadas;
  }

}