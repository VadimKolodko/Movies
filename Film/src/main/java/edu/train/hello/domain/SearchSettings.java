package edu.train.hello.domain;

public class SearchSettings {

    private String filtr;
    private String sort;
    private String rat;

    public SearchSettings()
    {

    }

    public SearchSettings(String filtr, String sort, String rat)
    {
        this.filtr = filtr;
        this.sort = sort;
        this.rat = rat;
    }

    public String getFiltr()
    {
        return this.filtr;
    }

    public String getSort()
    {
        return this.sort;
    }

    public String getRat() {
        return rat;
    }

    public void setFiltr(String filtr)
    {
        this.filtr = filtr;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }
    public void setRat(String rat){ this.rat = rat;}
}
