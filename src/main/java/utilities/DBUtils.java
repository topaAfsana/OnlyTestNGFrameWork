package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class DBUtils {

	CommonUtils common = new CommonUtils();
	public Logger log = Logger.getLogger(DBUtils.class.getName());
	
	public String[] runDBQuery(String Qry) {
		String[] DBInfo = new String[6];
		try {
			if(Qry != "" && Qry != null) {
				// get the property value
				DBInfo[0] = common.getProperty("dbDrvr");
				DBInfo[1] = common.getProperty("dbHost");
				DBInfo[2] = common.getProperty("dbPrtNmbr");
				DBInfo[3] = common.getProperty("dbSchmNm");
				DBInfo[4] = common.getProperty("dbUsrNm");
				DBInfo[5] = common.getProperty("dbPassword");
				
			}else {
				log.info("--------OBSERVED NOTE: DB SQL QUERY IS EMPTY/NULL--------");
			}
		}
			catch(Exception e) {
				log.info("------- OBSERVED EXCEPTION --------: in method runDBQry() \n" + e);
			}
			return DBInfo;
	}
	
	
	public ArrayList<String> dbValuesIterator(String QueryTxt){
		log.info("-------- Launching Data Base Query ---------: ");
		log.info(QueryTxt);
		String[] DBInfo = runDBQuery(QueryTxt);
		String columnValue = null;
		ArrayList<String> listQResponse = new ArrayList<String>();
		try {
			Class.forName(DBInfo[0]);
			Connection con = DriverManager.getConnection((DBInfo[1]+DBInfo[2]+DBInfo[3]),DBInfo[4],(DBInfo[5]));
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(QueryTxt);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int columnsNumber = rsmd.getColumnCount();
			log.info("---------- Data base Query Response -------- : ");
			while(rs.next()) {
				for(int i = 1; i<= columnsNumber; i++) {
					if(i>1) {
						log.info(", ");
					}
					columnValue = rs.getString(i);
					log.info(rsmd.getColumnName(i) + ": " + columnValue.toString().trim());
					if(columnValue.toString().trim().isEmpty() == true) {
						listQResponse.add(null);
					}else {
						listQResponse.add(columnValue.toString().trim());
					}
				}
				log.info("");
			}
			con.close();
		}catch(Exception e) {
			log.info("------ OBSERVED EXCEPTION -------- : " + e
					+ "in method dbValuesIterator(String QueryTxt)");
		}
		return listQResponse;
	}
	
	
	
	
	
	
	
	
	
	
}
