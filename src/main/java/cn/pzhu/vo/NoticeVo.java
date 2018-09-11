package cn.pzhu.vo;

public class NoticeVo {

    private Integer id;

    private String title;

    private String type;

    private String date;

    private String uploador;
    
    private String mcontent;

    public NoticeVo() {
    }

    public NoticeVo(Integer id, String title,String type, String mcontent) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.mcontent = mcontent;
	}
    
    

	public NoticeVo(Integer id, String title, String type, String date, String uploador) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.date = date;
		this.uploador = uploador;
	}

	public NoticeVo(Integer id, String title, String type, String date, String uploador, String mcontent) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.date = date;
        this.uploador = uploador;
        this.mcontent = mcontent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUploador() {
        return uploador;
    }

    public void setUploador(String uploador) {
        this.uploador = uploador;
    }

	public String getMcontent() {
		return mcontent;
	}

	public void setMcontent(String mcontent) {
		this.mcontent = mcontent;
	}
    
}
