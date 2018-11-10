package model;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.Init;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_Peixun")
public class Peixun implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String peixunjihua;//培训计划
	
	private String peixunneirong;//培训内容
	
	private String peixunzhouqi;//培训周期
	
	private String peixundidian;//培训地点

	
	private Date createtime;
	
	private int peixunlock;
	
	private Integer grade;
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

	public String getPeixunjihua() {
		return peixunjihua;
	}

	public void setPeixunjihua(String peixunjihua) {
		this.peixunjihua = peixunjihua;
	}

	public String getPeixunneirong() {
		return peixunneirong;
	}

	public void setPeixunneirong(String peixunneirong) {
		this.peixunneirong = peixunneirong;
	}

	public String getPeixunzhouqi() {
		return peixunzhouqi;
	}

	public void setPeixunzhouqi(String peixunzhouqi) {
		this.peixunzhouqi = peixunzhouqi;
	}

	public String getPeixundidian() {
		return peixundidian;
	}

	public void setPeixundidian(String peixundidian) {
		this.peixundidian = peixundidian;
	}

	public int getPeixunlock() {
		return peixunlock;
	}

	public void setPeixunlock(int peixunlock) {
		this.peixunlock = peixunlock;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	

	

	
	
	
}
