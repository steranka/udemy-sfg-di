package guru.springframework.sfgdi.datasource;

/**
 * Created by jt on 2/27/21.
 */
public class FakeDataSource {

    private String username;
    private String password;
    private String jdbcurl;

    public FakeDataSource() {
        System.out.println("FakeDataSource.<init> NO ARGS constructor called");
    }

    public FakeDataSource(String username, String password, String jdbcurl) {
        this.username = username;
        this.password = password;
        this.jdbcurl = jdbcurl;
        System.out.println("FakeDataSource.<init> as " + this.toString());

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("FakeDataSource.setUsername: " + username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcurl() {
        return jdbcurl;
    }

    public void setJdbcurl(String jdbcurl) {
        this.jdbcurl = jdbcurl;
    }

    @Override
    public String toString() {
        return "FakeDataSource{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", jdbcurl='" + jdbcurl + '\'' +
                '}';
    }
}
