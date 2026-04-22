package com.example.exemple.payment.othrer;

public enum ClassificacaoTipoPagamentoEnum {
	TP_DINHEIRO(1, "Dinheiro"),
	TP_PIX(2, "Pix"),
	TP_CARTAO(3, "Cartão"),
	TP_BOLETO(4, "Boleto"),
	TP_CHEQUE(5, "Cheque"),
	TP_TEF(6, "TEF"),
	TP_ON_CREDIT(7, "On credit"),
	TP_LINK(9, "Link de pagamento"),
	TP_OUTROS(99, "Outros");

	private Integer value;
	private String descricao;

	ClassificacaoTipoPagamentoEnum(Integer value, String descricao) {
		this.value = value;
		this.descricao = descricao;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
