import java.util.List;

public interface UserDao {

    public List<User> select();

    public User select(int id);

    public User select(String username, String password);

    public void insert(User u);

    public void delete(User u);

    public void update(User u);
}
