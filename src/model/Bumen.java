package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_Bumen")
public class Bumen implements Serializable{

	private static final long serialVersionUID = -7141419035239709511L;

	private long id;
	
	private String name;
	
	private int bianhao;
	
	private int bumenlock;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBumenlock() {
		return bumenlock;
	}

	public void setBumenlock(int bumenlock) {
		this.bumenlock = bumenlock;
	}

	public int getBianhao() {
		return bianhao;
	}

	public void setBianhao(int bianhao) {
		this.bianhao = bianhao;
	}

	


	
	
	
}
