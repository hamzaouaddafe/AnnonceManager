
import java.util.ArrayList;
import java.util.List;

public class UserDaoDb implements UserDao {

	private List<User> users;

	public UserDaoDb() {
		users = new ArrayList<User>();
		users.add(new User(1, "user1", "user1", "user1@test.com", "0624141145"));
		users.add(new User(2, "user2", "user2", "user2@test.com", "0652200241"));
		users.add(new User(3, "user3", "user3", "user3@test.com", "0600000025"));
		users.add(new User(4, "user4", "user4", "user4@test.com", "0600000026"));
		users.add(new User(5, "user5", "user5", "user5@test.com", "0652000014"));
	}

	public List<User> select() {
		return users;
	}

	public User select(int id) {

		for (User user : users) {
			if (user.getId() == id) {

				return user;
			}
		}

		return null;
	}

	public User select(String username, String password) {

		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

				return user;
			}
		}

		return null;
	}

	public void insert(User u) {

		users.add(u);
	}

	public void delete(User u) {

		users.remove(u);
	}

	public void update(User u) {

		if (users.contains(u)) {

			User user = select(u.getId());
			user = u;
			delete(user);
			insert(u);
		}
	}

}
