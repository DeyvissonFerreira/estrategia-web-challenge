package type;

public enum URL {
	
	APLICACAO("https://www.estrategiaconcursos.com.br/");
	
private final String url;
    
	URL(String url){
		this.url = url;
    }
	
    public String getURL(){
        return url;
    }
}