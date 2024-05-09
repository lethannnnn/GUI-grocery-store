package da;

import domain.Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.User;

public class SecurityDao {

    private Connection con;

    public SecurityDao(Connection con) {
        super();
        this.con = con;
    }

    public boolean saveSecurity(Security security) {
        boolean flag = false;

        try {
            String query = "insert into security(security_question,security_answer) values(?, ?)";
            PreparedStatement psmt = this.con.prepareStatement(query);
            psmt.setString(1, security.getSecurityQuestion());
            psmt.setString(2, security.getSecurityAnswer());

            psmt.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    
}
