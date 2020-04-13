
import java.util.List;

public interface Services {

	public List<Annonce> selectAnnonces();

	public Annonce selectAnnonce(int id);

	public void insertAnnonce(Annonce a);

	public void deleteAnnonce(Annonce a);

	public void updateAnnonce(Annonce a);

	public List<User> selectUsers();

	public User selectUser(int id);

	public User selectUser(String username, String password);

	public void insertUser(User u);

	public void deleteUser(User u);

	public void updateUser(User u);

	public List<Annonce> selectAnnoncesByUser(User connectedUser);

	public List<Annonce> selectAnnoncesByUsername(String username);
}
