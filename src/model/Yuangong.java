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
@Table(name="t_Yuangong")
public class Yuangong implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String truename;//真实姓名
	
	private String yuangonghao;//员工号
	
	private String lianxidianhua;//联系电话
	
	private String dizhi;//地址
	
	private String xueli;//学历
	
	private String jiguan;//籍贯
	
	private String ruzhishijian;//入职时间
	
	private Bumen bumen;
	
	private Bumen prebumen;
	
	private int yuangonglock;
	
	private Date createtime;//添加时间
	
	
	private String xingbie;//性别
	
	private String beizhu;//备注
	
	private int zhiwu;//0表示普通员工，1表示部门负责人
	
	private Menmian menmian;
	



	public int getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(int zhiwu) {
		this.zhiwu = zhiwu;
	}

	public String getXingbie() {
		return xingbie;
	}

	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getYuangonghao() {
		return yuangonghao;
	}

	public void setYuangonghao(String yuangonghao) {
		this.yuangonghao = yuangonghao;
	}

	public String getLianxidianhua() {
		return lianxidianhua;
	}

	public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
	}

	public String getDizhi() {
		return dizhi;
	}

	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}

	public String getXueli() {
		return xueli;
	}

	public void setXueli(String xueli) {
		this.xueli = xueli;
	}

	public String getJiguan() {
		return jiguan;
	}

	public void setJiguan(String jiguan) {
		this.jiguan = jiguan;
	}

	public String getRuzhishijian() {
		return ruzhishijian;
	}

	public void setRuzhishijian(String ruzhishijian) {
		this.ruzhishijian = ruzhishijian;
	}

	@ManyToOne
	@JoinColumn(name="bumenid")
	public Bumen getBumen() {
		return bumen;
	}

	public void setBumen(Bumen bumen) {
		this.bumen = bumen;
	}

	public int getYuangonglock() {
		return yuangonglock;
	}

	public void setYuangonglock(int yuangonglock) {
		this.yuangonglock = yuangonglock;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@ManyToOne
	@JoinColumn(name="menmianid")
	public Menmian getMenmian() {
		return menmian;
	}

	public void setMenmian(Menmian menmian) {
		this.menmian = menmian;
	}
	@ManyToOne
	@JoinColumn(name="prebumenid")
	public Bumen getPrebumen() {
		return prebumen;
	}

	public void setPrebumen(Bumen prebumen) {
		this.prebumen = prebumen;
	}

	

	
	
}
