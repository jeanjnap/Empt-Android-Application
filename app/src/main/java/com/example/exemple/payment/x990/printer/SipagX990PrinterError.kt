package com.example.exemple.payment.x990.printer

enum class SipagX990PrinterError(val code: Int, val message: String) {

    ERROR_DEFINE_BASE(1, "Erro Base Definido"),
    ERROR_NOT_INIT(2, "Erro: Impressora não inicializada"),
    ERROR_PARAM(3, "Erro: Parâmetro inválido"),
    ERROR_BMBLACK(4, "Erro: Cabeça de impressão bloqueada"),
    ERROR_BUFOVERFLOW(5, "Erro: Overflow do buffer"),
    ERROR_BUSY(6, "Erro: Impressora ocupada"),
    ERROR_COMMERR(7, "Erro de comunicação"),
    ERROR_CUTPOSITIONERR(8, "Erro: Posição de corte inválida"),
    ERROR_HARDERR(9, "Erro de hardware"),
    ERROR_LIFTHEAD(10, "Erro: Cabeça de impressão levantada"),
    ERROR_LOWTEMP(11, "Erro: Temperatura muito baixa"),
    ERROR_LOWVOL(12, "Erro: Baixa voltagem"),
    ERROR_MOTORERR(13, "Erro no motor"),
    ERROR_NOBM(14, "Erro: Nenhum banner detectado"),
    ERROR_OVERHEAT(15, "Erro: Superaquecimento da impressora"),
    ERROR_PAPERENDED(16, "Erro: Papel acabado"),
    ERROR_PAPERENDING(17, "Erro: Papel quase acabando"),
    ERROR_PAPERJAM(18, "Erro: Papel enroscado"),
    ERROR_PENOFOUND(19, "Erro: Caneta não encontrada"),
    ERROR_WORKON(20, "Erro: Impressora está em uso"),
    ERROR_CUT_PAPER_FAILURE(21, "Erro: Falha no corte do papel"),
    ERROR_OPENCOVER(22, "Erro: Tampa aberta"),
    ERROR_PAPER_OR_CUTTER_CLEAN(23, "Erro: Limpeza de papel ou cortador necessária"),
    ERROR_CUTTER_FAILURE(24, "Erro: Falha no cortador"),
    ERROR_TIMEOUT(25, "Erro: Timeout de comunicação"),
    ERROR_PRINT_NOT_SUPPORTED(26, "Erro: Impressão não suportada"),
    ERROR_PRINTER_OPEN_FAILED(27, "Erro: Falha ao abrir a impressora"),
    ERROR_NOT_CONNECTED(-100, "Erro: Impressora não conectada"),
    ERROR_SERVICE_DISCONNECTED(-101, "Erro: Serviço desconectado"),

    UNKNOWN(-1, "Erro desconhecido");

    companion object {
        fun fromCode(code: Int?) = entries.firstOrNull { it.code == code } ?: UNKNOWN
    }
}
