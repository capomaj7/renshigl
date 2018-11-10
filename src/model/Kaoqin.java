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
@Table(name="t_Kaoqin")
public class Kaoqin implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private int kaoqinlock;
	
	private String leixing;//上班登记，下班登记，请假登记，出差登记，外出登记
	
	private String beizhu;//备注
	
	private Date createtime;

	private Yuangong yuangong;
	
	
	@ManyToOne
	@JoinColumn(name="yuangongid")
	public Yuangong getYuangong() {
		return yuangong;
	}

	public void setYuangong(Yuangong yuangong) {
		this.yuangong = yuangong;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getKaoqinlock() {
		return kaoqinlock;
	}

	public void setKaoqinlock(int kaoqinlock) {
		this.kaoqinlock = kaoqinlock;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	

	

	

	
	
	
}
