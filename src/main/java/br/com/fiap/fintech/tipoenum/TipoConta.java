package br.com.fiap.fintech.tipoenum;

public enum TipoConta {
    FISICA("F"),  // "F" para pessoa física
    JURIDICA("J");  // "J" para pessoa jurídica

    private final String codigo;

    TipoConta(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
    public static TipoConta fromCodigo(String codigo) {
        for (TipoConta tipo : TipoConta.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

}
