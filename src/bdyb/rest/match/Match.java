package bdyb.rest.match;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;

public class Match {

	private int iD;
	private int iDTabeli;
	private int iDDom;
	private int iDWyj;
	private int bramkiDom;
	private int bramkiWyj;
	private Date dataMeczu;
	
	@Override
	public String toString() {
		return String.format("Match [iD=%s, iDTabeli=%s, iDDom=%s, iDWyj=%s, bramkiDom=%s, bramkiWyj=%s, dataMeczu=%s]",
				iD, iDTabeli, iDDom, iDWyj, bramkiDom, bramkiWyj, dataMeczu);
	}
	
	public int getiD() {
		return iD;
	}
	public int getiDTabeli() {
		return iDTabeli;
	}
	public int getiDDom() {
		return iDDom;
	}
	public int getiDWyj() {
		return iDWyj;
	}
	public int getBramkiDom() {
		return bramkiDom;
	}
	public int getBramkiWyj() {
		return bramkiWyj;
	}
	public Date getDataMeczu() {
		return dataMeczu;
	}
	@XmlElement
	public void setiD(int iD) {
		this.iD = iD;
	}
	@XmlElement
	public void setiDTabeli(int iDTabeli) {
		this.iDTabeli = iDTabeli;
	}
	@XmlElement
	public void setiDDom(int iDDom) {
		this.iDDom = iDDom;
	}
	@XmlElement
	public void setiDWyj(int iDWyj) {
		this.iDWyj = iDWyj;
	}
	@XmlElement
	public void setBramkiDom(int bramkiDom) {
		this.bramkiDom = bramkiDom;
	}
	@XmlElement
	public void setBramkiWyj(int bramkiWyj) {
		this.bramkiWyj = bramkiWyj;
	}
	@XmlElement
	public void setDataMeczu(Date dataMeczu) {
		this.dataMeczu = dataMeczu;
	}
	
}
