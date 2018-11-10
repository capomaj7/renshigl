package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Hetong")
public class Hetong implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String beizhu;
	
	private String path;
	
	private String name;
	
	private String num;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	private Date createtime;
	
	private int hetonglock;
	
	private Yuangong yuangong;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getHetonglock() {
		return hetonglock;
	}

	public void setHetonglock(int hetonglock) {
		this.hetonglock = hetonglock;
	}

	@ManyToOne
	@JoinColumn(name="yuangongid")
	public Yuangong getYuangong() {
		return yuangong;
	}

	public void setYuangong(Yuangong yuangong) {
		this.yuangong = yuangong;
	}

	

	
	
	
}
