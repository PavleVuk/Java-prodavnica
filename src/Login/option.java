package Login;

public enum option {
    Administrator, Zaposleni;

    private option(){ }

    public String value(){

        return name();
    }
    public static option fromvalue(String v)
    {
        return valueOf(v);
    }
}
