package es.uji.control.domain.people;

import java.util.Date;

public class Photo {

	private long perId;
	private Date date;
	private byte[] image;
	
	public long getPerId() {
		return perId;
	}
	
	public void setPerId(long perId) {
		this.perId = perId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
