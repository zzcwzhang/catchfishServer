package com.fuyun.server.db.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
 * 发送者的id(senderId)int
 * 接收者的id(receiverId)int
 * 标题（title）String
 * 内容（content）String
 * 发送时间 (createTime) date
 * 领取时间(receivedTime) date
 * 是否领取 (received) byte 0-未领取 1-已领取
 * 是否读取（read)byte 0-未读取 1-已读取
 * 附件  （atms ）Set<Attachment>
 * @author Administrator
 *
 */

@Entity
@Table(name="mail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Mail {
	
	private int id;
	private int senderId;
	private int receiverId;
	private String title;
	private String content;
	private Date createTime;
	private Date receivedTime;
	private byte received;
	private byte read;
	
	private Attachment atm;

	
	public Mail() {
		super();
	}
	
	


	public Mail(int id) {
		super();
		this.id = id;
	}




	public Mail(int senderId, String title, String content, Date createTime) {
		super();
		this.senderId = senderId;
		this.title = title;
		this.content = content;
		this.createTime = createTime;
	}

	@ManyToOne(fetch=FetchType.EAGER)//LAZY就是XML中的select,EAGER就表示XML中的join 默认是EAGER延时加载会失效
	@JoinColumn(name="atmId")
	public Attachment getAtm() {
		return atm;
	}

	public void setAtm(Attachment atm) {
		this.atm = atm;
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

	@Column(name="senderId",columnDefinition = "int(8) NOT  NULL  COMMENT ' 发送者的id'")
	public int getSenderId() {
		return senderId;
	}


	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	@Column(name="receiverId",columnDefinition = "int(8) NOT  NULL  COMMENT '接收者的id'")
	public int getReceiverId() {
		return receiverId;
	}


	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	@Column(name="title",columnDefinition = "tinytext  NOT  NULL  COMMENT '标题'")
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="content",columnDefinition = "text NOT  NULL  COMMENT '内容'")
	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="createTime",columnDefinition = "datetime NOT NULL  COMMENT '创建时间'")
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="receivedTime",columnDefinition = "datetime  COMMENT '领取时间'")
	public Date getReceivedTime() {
		return receivedTime;
	}


	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	@Column(name="received",columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '领取'")
	public byte getReceived() {
		return received;
	}


	public void setReceived(byte received) {
		this.received = received;
	}

	@Column(name="readed",columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '读取'")
	public byte getRead() {
		return read;
	}


	public void setRead(byte read) {
		this.read = read;
	}

	

	@Override
	public String toString() {
		return "Mail [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", title=" + title
				+ ", content=" + content + ", createTime=" + createTime + ", receivedTime=" + receivedTime
				+ ", received=" + received + ", read=" + read + "]";
	}
	
	
	
	
	
	
	
	
}
