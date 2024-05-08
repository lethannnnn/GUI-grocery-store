package da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.User;

public class UserDao {

	private Connection con;

	public UserDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean saveUser(User user) {
		boolean flag = false;

		try {
			String query = "insert into Customer(userName, userEmail, userPassword, userPhone, userGender, userAddress, userCity, userPincode, userState) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getUserEmail());
			psmt.setString(3, user.getUserPassword());
			psmt.setString(4, user.getUserPhone());
			psmt.setString(5, user.getUserGender());
			psmt.setString(6, user.getUserAddress());
			psmt.setString(7, user.getUserCity());
			psmt.setString(8, user.getUserPincode());
			psmt.setString(9, user.getUserState());

			psmt.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public User getUserByEmailPassword(String userEmail, String userPassword) {
		User user = null;
		try {
                        String query = "select * from Customer where useremail = ? and userpassword = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, userEmail);
			psmt.setString(2, userPassword);

			ResultSet set = psmt.executeQuery();
			while (set.next()) {
				user = new User();

				user.setUserId(set.getInt("userId"));
				user.setUserName(set.getString("userName"));
				user.setUserEmail(set.getString("userEmail"));
				user.setUserPassword(set.getString("userPassword"));
				user.setUserPhone(set.getString("userPhone"));
				user.setUserGender(set.getString("userGender"));
				user.setDateTime(set.getTimestamp("dateTime"));
				user.setUserAddress(set.getString("userAddress"));
				user.setUserCity(set.getString("userCity"));
				user.setUserPincode(set.getString("userPincode"));
				user.setUserState(set.getString("userState"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		try {
			String query = "select * from Customer";
			Statement statement = this.con.createStatement();
			ResultSet set = statement.executeQuery(query);
			while (set.next()) {
				User user = new User();
				user.setUserId(set.getInt("userId"));
				user.setUserName(set.getString("userName"));
				user.setUserEmail(set.getString("userEmail"));
				user.setUserPassword(set.getString("userPassword"));
				user.setUserPhone(set.getString("userPhone"));
				user.setUserGender(set.getString("userGender"));
				user.setDateTime(set.getTimestamp("dateTime"));
				user.setUserAddress(set.getString("userAddress"));
				user.setUserCity(set.getString("userCity"));
				user.setUserPincode(set.getString("userPincode"));
				user.setUserState(set.getString("userState"));
				
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void updateUserAddress(User user) {
		try {
			String query = "update Customer set userAddress = ?, userCity = ?, userPincode = ?, userState = ? where userId = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, user.getUserAddress());
			psmt.setString(2, user.getUserCity());
			psmt.setString(3, user.getUserPincode());
			psmt.setString(4, user.getUserState());
			psmt.setInt(5, user.getUserId());

			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateUserPasswordByEmail(String userPassword, String userEmail) {
		try {
			String query = "update Customer set userPassword = ? where userEmail = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, userPassword);
			psmt.setString(2, userEmail);
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		try {
			String query = "update Customer set userName = ?, userEmail = ?, userPhone = ?, userGender = ?, userAddress = ?, userCity = ?, userPincode = ?, uesrState = ? where userId = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getUserEmail());
			psmt.setString(3, user.getUserPhone());
			psmt.setString(4, user.getUserGender());
			psmt.setString(5, user.getUserAddress());
			psmt.setString(6, user.getUserCity());
			psmt.setString(7, user.getUserPincode());
			psmt.setString(8, user.getUserState());
			psmt.setInt(9, user.getUserId());

			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUserAddress(int userId) {
		String userAddress = "";
		try {
			String query = "select userAddress, userCity, userPincode, userState from Customer where userId = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, userId);

			ResultSet rs = psmt.executeQuery();
			rs.next();
			userAddress = rs.getString(1) + ", " + rs.getString(2) + "-" + rs.getString(3) + ", " + rs.getString(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userAddress;
	}
	public String getUserName(int userId) {
		String userName = "";
		try {
			String query = "select userName from Customer where userId = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, userId);
			
			ResultSet rs = psmt.executeQuery();
			rs.next();
			userName = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}
	public String getUserEmail(int userId) {
		String userEmail = "";
		try {
			String query = "select userEmail from Customer where userId = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, userId);
			
			ResultSet rs = psmt.executeQuery();
			rs.next();
			userEmail = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userEmail;
	}
	public String getUserPhone(int userId) {
		String userPhone = "";
		try {
			String query = "select userPhone from Customer where userId = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, userId
                        );
			
			ResultSet rs = psmt.executeQuery();
			rs.next();
			userPhone = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userPhone;
	}
	public void deleteUser(int userId) {
		try {
			String query = "delete from Customer where userId = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, userId);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<String> getAllEmail() {
		List<String> list = new ArrayList<>();
		try {
			String query = "select userEmail from Customer";
			Statement statement = this.con.createStatement();
			ResultSet set = statement.executeQuery(query);
			while (set.next()) {
				list.add(set.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
