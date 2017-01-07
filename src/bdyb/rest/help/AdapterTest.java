package bdyb.rest.help;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;


public class AdapterTest {

	SqlDateAdapter adapter;
	Date v;
	SimpleDateFormat dateFormat;
	String example;
	
	@Before
	public void setUp() {
		adapter = new SqlDateAdapter();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		example = "2010-10-10 10:10:10";
		
		try {
			v = new Date(dateFormat.parse(example).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test() {
		try {
			assertEquals(example, adapter.marshal(v));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

}
