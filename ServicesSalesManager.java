
import java.util.List;

public class ServicesSalesManager implements Services {

	private AnnonceDao annonceDao;
	private UserDao userDao;

	public ServicesSalesManager(AnnonceDao annonceDao, UserDao userDao) {

		this.annonceDao = annonceDao;
		this.userDao = userDao;
	}

	public List<Annonce> selectAnnonces() {

		return this.annonceDao.select();
	}

	public Annonce selectAnnonce(int id) {

		return this.annonceDao.select(id);
	}

	public void insertAnnonce(Annonce a) {

		this.annonceDao.insert(a);
	}

	public void deleteAnnonce(Annonce a) {

		this.annonceDao.delete(a);
	}

	public void updateAnnonce(Annonce a) {

		this.annonceDao.update(a);
	}

	public List<User> selectUsers() {

		return this.userDao.select();
	}

	public User selectUser(int id) {

		return this.userDao.select(id);
	}

	public User selectUser(String username, String password) {

		return this.userDao.select(username, password);
	}

	public void insertUser(User u) {

		this.userDao.insert(u);
	}

	public void deleteUser(User u) {

		this.userDao.delete(u);
	}

	public void updateUser(User u) {

		this.userDao.update(u);
	}

	public List<Annonce> selectAnnoncesByUser(User user) {
		return this.annonceDao.selectByUser(user);
	}

	public List<Annonce> selectAnnoncesByUsername(String username) {
		return this.annonceDao.selectByUser(username);
	}

}
