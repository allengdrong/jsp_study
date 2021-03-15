package com.webjjang.message.vo;

public class MessageVO {

	// 글번호, 제목, 내용, 작성자, 작성일, 조회수
	private long no;
	private String content;
	private String sender;
	private String sendName;
	private String sendDate;
	private String accepter;
	private String acceptName;
	private String acceptDate;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getAccepter() {
		return accepter;
	}
	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}
	public String getAcceptName() {
		return acceptName;
	}
	public void setAcceptName(String acceptName) {
		this.acceptName = acceptName;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	
	@Override
	public String toString() {
		return "MessageVO [no=" + no + ", content=" + content + ", sender=" + sender + ", sendName=" + sendName
				+ ", sendDate=" + sendDate + ", accepter=" + accepter + ", acceptName=" + acceptName + ", acceptDate="
				+ acceptDate + "]";
	}
	
}
