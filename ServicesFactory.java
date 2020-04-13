
public class ServicesFactory {
	private static Services services;
	static {

		UserDao userDao = new UserDaoDb();
		AnnonceDao annonceDao = new AnnonceDaoDb(userDao);

		services = new ServicesSalesManager(annonceDao, userDao);
	}

	public static Services getServices() {
		return services;
	}
}
