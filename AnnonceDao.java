
import java.util.List;

public interface AnnonceDao {

	public List<Annonce> select();
	public Annonce select(int id);
	public int insert(Annonce a);
	public int delete(Annonce a);
	public int update(Annonce a);
	public List<Annonce> selectByUser(User user);
	public List<Annonce> selectByUser(String username);
}
