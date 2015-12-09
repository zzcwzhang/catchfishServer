package com.fuyun.server.db.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 索引id
 * 类型 (atmType) int
 * 个数(atmCount) int
 * @author Administrator
 *
 */
@Entity
@Table(name="attachment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Attachment {

	private int id;
	private int atmType;
	private int atmCount;
	
	private Set<Mail> mails;
	
	public Attachment() {
		super();
	}

	public Attachment(int atmType, int atmCount) {
		super();
		this.atmType = atmType;
		this.atmCount = atmCount;
	}
	
	@OneToMany(mappedBy="atm")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@Fetch(FetchMode.SUBSELECT)//@BatchSize(size = 16) //@Fetch(FetchMode.SUBSELECT)
	public Set<Mail> getMails() {
		return mails;
	}

	public void setMails(Set<Mail> mails) {
		this.mails = mails;
	}

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="atmType",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '奖励类型'")
	public int getAtmType() {
		return atmType;
	}

	public void setAtmType(int atmType) {
		this.atmType = atmType;
	}
	@Column(name="atmCount",columnDefinition = "int(8) NOT NULL DEFAULT 0 COMMENT '奖励个数'")
	public int getAtmCount() {
		return atmCount;
	}

	public void setAtmCount(int atmCount) {
		this.atmCount = atmCount;
	}

	@Override
	public String toString() {
		return "attachment [id=" + id + ", atmType=" + atmType + ", atmCount=" + atmCount + "]";
	}
	
	
}
