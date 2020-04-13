
import java.util.List;
import java.util.stream.Stream;

public class Action {
	private User connectedUser;

	private Services services;
	private ConsolePrinter consolePrinter;
	private ServerThread serverThread;

	public Action() {
		consolePrinter = new ConsolePrinter();
		services = ServicesFactory.getServices();
	}

	public Action(ServerThread serverThread) {
		this.serverThread = serverThread;
		consolePrinter = new ConsolePrinter();
		services = ServicesFactory.getServices();
	}

	public String handleRequest(String request) {

		if (!isBadRequest(request)) {

			if ("connected".equals(request)) {
				return consolePrinter.index();
			} else if (request.startsWith("connect")) {

				return login(request);
			} else if (request.startsWith("create user")) {

				return createUser(request);
			} else if (request.startsWith("add")) {
				return addAnnonce(request);
			} else if (request.startsWith("delete")) {
				return deleteAnnonce(request);
			} else if (request.startsWith("annonce")) {
				if (request.equals("annonce")) {

					return "request [" + request + "] not found ! you have to add arguments";
				} else if (request.contains(":")) {
					return addAnnonce(request);
				} else {
					String username = request.split(" ")[1];
					return consolePrinter.annonces(services.selectAnnoncesByUsername(username), "ID", "Déscription",
							"Prix", "Type", "E-mail auteur de l'annonce");
				}
			} else if (request.startsWith("myannonce")) {
				return consolePrinter.annonces(services.selectAnnoncesByUser(this.connectedUser), "ID", "Déscription",
						"Prix", "Type", "E-mail auteur de l'annonce");
			}
		}
		return "request [" + request + "] not found !";
	}

	private boolean isBadRequest(String request) {
		int space = 0;
		for (int i = 0; i < request.length(); i++) {

			if (" ".equals(request.charAt(i) + "")) {
				space++;
			}
		}

		return (space > 2);
	}

	private int countSeparator(String request) {

		int counter = 0;
		for (int i = 0; i < request.length(); i++) {

			if (request.charAt(i) == ':') {

				counter++;
			}
		}

		return counter;
	}

	private String handleBadRequest(String request, String sep, int numberOfSep) {
		StringBuffer buffer = new StringBuffer("PASS");

		int counter = countSeparator(request);
		if (numberOfSep != counter) {
			buffer = new StringBuffer("Erreur : ");
			buffer.append("BAD REQUEST");
		}

		System.out
				.println("handleBadRequest(" + request + ", " + sep + ", " + numberOfSep + ") => " + buffer.toString());
		return buffer.toString();
	}

	private String deleteAnnonce(String request) {
		String badRequest = handleBadRequest(request, ":", 0);
		if (!badRequest.equals("PASS")) {

			return badRequest;
		}

		String t = request.split(" ")[1];
		if (t != null && !"".equals(t)) {
			int id = Integer.parseInt(t);
			Annonce annonce = null;
			try {
				annonce = services.selectAnnonce(id);
			} catch (Exception e) {

				System.out.println("Error : " + e.getMessage());
			}
			if (annonce != null) {
				if (this.connectedUser.getId() == annonce.getUser().getId()) {

					services.deleteAnnonce(annonce);
				} else
					return "BAD REQUEST : Cette annonce ne vous appartient pas pour la supprimer !";
			} else
				return "BAD REQUEST : Cette annonce n'existe pas";
		}
		return consolePrinter.annonces(services.selectAnnoncesByUser(this.connectedUser), "ID", "Déscription", "Prix",
				"Type", "E-mail auteur de l'annonce");
	}

	private String createUser(String request) {

		String badRequest = handleBadRequest(request, ":", 3);
		if (!badRequest.equals("PASS")) {

			return badRequest;
		}
		String t[] = request.split(" ")[2].split(":");
		String username = t[0];
		String password = t[1];
		String phone = t[2];
		String email = t[3];

		services.insertUser(new User(username, password, email, phone));
		return consolePrinter.index();
	}

	private String addAnnonce(String request) {
		String badRequest = handleBadRequest(request, ":", 2);
		if (!badRequest.equals("PASS")) {

			return badRequest;
		}

		String t[] = request.split(" ")[1].split(":");
		String price = t[0];
		String description = t[1];
		String type = t[2];

		Annonce annonce = new Annonce(new Domain(type), connectedUser, Integer.parseInt(price), description);
		services.insertAnnonce(annonce);

		return consolePrinter.annonces(services.selectAnnonces(), "Déscription", "Prix", "Type",
				"E-mail auteur de l'annonce");
	}

	public String login(String request) {
		String badRequest = handleBadRequest(request, ":", 1);
		if (!badRequest.equals("PASS")) {

			return badRequest;
		}

		String t[] = request.split(" ")[1].split(":");
		String username = t[0];
		String password = t[1];

		User user = services.selectUser(username, password);

		if (user != null) {
			connectedUser = user;
			return annonces(services.selectAnnonces(), "Déscription", "Prix", "Type", "E-mail auteur de l'annonce");
		} else
			return consolePrinter.index();
	}

	private String annonces(List<Annonce> annonces, String... titles) {
		return consolePrinter.list(annonces, titles);
	}
}
