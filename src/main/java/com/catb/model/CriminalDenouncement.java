package com.catb.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "criminal_denouncement")
public class CriminalDenouncement implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "content")
	private String content;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "sent_date")
	private Date sentDate;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "reply_content")
	private String replyContent;

	public CriminalDenouncement() {
		
	}

	public CriminalDenouncement(Integer id, String name, String title,
			String email, String content, Date sentDate, Boolean status,
			String replyContent) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.email = email;
		this.content = content;
		this.sentDate = sentDate;
		this.status = status;
		this.replyContent = replyContent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
}
