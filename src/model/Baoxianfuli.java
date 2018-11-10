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
@Table(name="t_Baoxianfuli")
public class Baoxianfuli implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String mingchen;
	
	private String yuefen;
	
	private String gongzi;
	
	private Date createtime;
	
	private int baoxianfulilock;
	
	private Yuangong yuangong;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	@ManyToOne
	@JoinColumn(name="yuangongid")
	public Yuangong getYuangong() {
		return yuangong;
	}

	public void setYuangong(Yuangong yuangong) {
		this.yuangong = yuangong;
	}

	public String getYuefen() {
		return yuefen;
	}

	public void setYuefen(String yuefen) {
		this.yuefen = yuefen;
	}

	public String getGongzi() {
		return gongzi;
	}

	public void setGongzi(String gongzi) {
		this.gongzi = gongzi;
	}

	public String getMingchen() {
		return mingchen;
	}

	public void setMingchen(String mingchen) {
		this.mingchen = mingchen;
	}

	public int getBaoxianfulilock() {
		return baoxianfulilock;
	}

	public void setBaoxianfulilock(int baoxianfulilock) {
		this.baoxianfulilock = baoxianfulilock;
	}

	

	

	

	
	
	
}
