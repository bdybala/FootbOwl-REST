package bdyb.rest.help;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SqlDateAdapter extends XmlAdapter<java.util.Date, java.sql.Date> {

	SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	public java.util.Date marshal(java.sql.Date v) throws Exception {
		java.util.Date result = new java.util.Date(v.getTime());
		
		return result;
	}

	@Override
	public java.sql.Date unmarshal(java.util.Date v) throws Exception {
		java.sql.Date result = new java.sql.Date(v.getTime());
		return result;
	}
	
	

}
