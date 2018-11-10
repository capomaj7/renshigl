package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_Zhiwei")
public class Zhiwei implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private Date createtime;
	
	private int zhiweilock;
	
	private String zhiweimingchen;//职位名称 
	
	private String zhaopinrenshi;//招聘人数
	
	private String beizhu;//备注
	
	
	
	

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

	public int getZhiweilock() {
		return zhiweilock;
	}

	public void setZhiweilock(int zhiweilock) {
		this.zhiweilock = zhiweilock;
	}

	public String getZhiweimingchen() {
		return zhiweimingchen;
	}

	public void setZhiweimingchen(String zhiweimingchen) {
		this.zhiweimingchen = zhiweimingchen;
	}

	public String getZhaopinrenshi() {
		return zhaopinrenshi;
	}

	public void setZhaopinrenshi(String zhaopinrenshi) {
		this.zhaopinrenshi = zhaopinrenshi;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}


	

	

	
	
	
}
