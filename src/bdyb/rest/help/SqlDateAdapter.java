package bdyb.rest.help;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SqlDateAdapter extends XmlAdapter<Long, java.sql.Date> {

	SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Override
	public Long marshal(Date v) throws Exception {
		
		return v.getTime();
	}

	@Override
	public Date unmarshal(Long v) throws Exception {
		return new Date(v);
	}
	

}
