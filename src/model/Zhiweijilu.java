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
@Table(name="t_Zhiweijilu")
public class Zhiweijilu implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private Date createtime;
	
	private int zhiweijilulock;
	
	private String xingming;
	
	private String xueli;
	
	private String lianxidianhua;
	
	private String addresss;
	
	private String xingbie;
	
	private Zhiwei zhiwei;
	
	private String yingpingjieguo;//应聘结果
	


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

	public int getZhiweijilulock() {
		return zhiweijilulock;
	}

	public void setZhiweijilulock(int zhiweijilulock) {
		this.zhiweijilulock = zhiweijilulock;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public String getXueli() {
		return xueli;
	}

	public void setXueli(String xueli) {
		this.xueli = xueli;
	}

	public String getLianxidianhua() {
		return lianxidianhua;
	}

	public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
	}

	public String getAddresss() {
		return addresss;
	}

	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}

	public String getXingbie() {
		return xingbie;
	}

	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}

	
	@ManyToOne
	@JoinColumn(name="zhiweiid")
	public Zhiwei getZhiwei() {
		return zhiwei;
	}

	public void setZhiwei(Zhiwei zhiwei) {
		this.zhiwei = zhiwei;
	}

	public String getYingpingjieguo() {
		return yingpingjieguo;
	}

	public void setYingpingjieguo(String yingpingjieguo) {
		this.yingpingjieguo = yingpingjieguo;
	}

	

	

	

	
	
	
}
