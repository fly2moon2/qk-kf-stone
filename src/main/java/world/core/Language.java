package world.core;

public class Language {
    public Long id;
    public String langCde;
    public String lang;

    public Language(Long id, String langCde, String lang) {
        this.id=id;
        this.langCde=langCde;
        this.lang=lang;
    }

    public Language() {
        
    }
}
