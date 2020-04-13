
import java.util.ArrayList;
import java.util.List;

public class AnnonceDaoDb implements AnnonceDao {

	private List<Annonce> annonces = new ArrayList<Annonce>();
	private UserDao userDao;

	public AnnonceDaoDb(UserDao userDao) {
		this.userDao = userDao;
		annonces.add(new Annonce(1, new Domain(1, "Telephone"), this.userDao.select(1), 1000, "Iphone X"));
		annonces.add(new Annonce(2, new Domain(2, "Voiture"), this.userDao.select(2), 4500, "BMW"));
		annonces.add(new Annonce(3, new Domain(3, "Telephone"), this.userDao.select(3), 700, "Iphone 8"));
		annonces.add(new Annonce(4, new Domain(4, "Telephone"), this.userDao.select(2), 150, "Samsung s6"));
		annonces.add(new Annonce(5, new Domain(5, "Telephone"), this.userDao.select(4), 900, "Iphone X"));
	}

	public List<Annonce> select() {

		return annonces;
	}

	public Annonce select(int id) {

		for (Annonce annonce : annonces) {
			if (annonce.getId() == id) {
				return annonce;
			}
		}
		return null;
	}

	public int insert(Annonce a) {

		annonces.add(a);
		return 1;
	}

	public int delete(Annonce a) {

		annonces.remove(a);
		return 1;
	}

	public int update(Annonce a) {
		if (annonces.contains(a)) {
			Annonce ann = select(a.getId());

			Annonce tmp = ann;
			ann = a;
			annonces.remove(tmp);
			annonces.add(ann);
		}

		return 1;
	}

	public List<Annonce> selectByUser(User user) {

		List<Annonce> list = new ArrayList<Annonce>();
		for (Annonce annonce : annonces) {
			if (annonce.getUser().getId() == user.getId()) {
				list.add(annonce);
			}
		}
		return list;
	}

	public List<Annonce> selectByUser(String username) {
		List<Annonce> list = new ArrayList<Annonce>();
		for (Annonce annonce : annonces) {
			if (annonce.getUser().getUsername().equals(username)) {
				list.add(annonce);
			}
		}
		return list;
	}

}
