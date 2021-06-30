package type;

public enum Mensagens {
	
	RESULTADO_NAO_ENCONTRADO("Nenhum resultado encontrado.");
	
private final String mensagem;
    
	Mensagens(String mensagem){
		this.mensagem = mensagem;
    }
	
    public String getMensagem(){
        return mensagem;
    }
}
