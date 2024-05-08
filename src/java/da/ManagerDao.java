package da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import domain.Manager;

public class ManagerDao {
	
	private Connection con;
	
	public ManagerDao(Connection con) {
		super();
		this.con = con;
	}
        
	public Manager getManagerByPassword(String password) {
		Manager manager = null;
		try {
			String query = "select * from manager where password = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, password);

			ResultSet set = psmt.executeQuery();
			while (set.next()) {
				manager = new Manager();
				manager.setPassword(set.getString("password"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return manager;
	}
	public List<Manager> getAllManager(){
		List<Manager> list = new ArrayList<Manager>();
		try {
			
			String query = "select * from manager";
			Statement statement = this.con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Manager manager = new Manager();
				manager.setPassword(rs.getString("password"));
				
				list.add(manager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
