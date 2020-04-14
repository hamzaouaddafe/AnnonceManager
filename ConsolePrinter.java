import java.util.List;

public class ConsolePrinter {
    public static final String SEPARATOR = "|";
    public static final String BREAKER = System.getProperty("line.separator");

    public ConsolePrinter() {}

    public String index() {
        StringBuffer buffer = new StringBuffer(separator());

        buffer.append(SEPARATOR);
        buffer.append(
                "# Voulez vous vous connecter ? la ligne de commande est : connect <username>:<password>");
        buffer.append(SEPARATOR);
        buffer.append(
                "# Voulez vous créer un compte ? la ligne de commande est : create user <username>:<password>:<phone>:<email>");
        buffer.append(SEPARATOR);

        return buffer.toString();
    }

    public String list(List<Annonce> annonces, String... titles) {
        StringBuffer buffer = new StringBuffer(separator());
        buffer.append(SEPARATOR);
        buffer.append(annonces(annonces, titles));

        buffer.append(menu());

        return buffer.toString();
    }

    public String annonces(List<Annonce> annonces, String... titles) {

        StringBuffer buffer = new StringBuffer(titles[0]);
        for (int i = 1; i < titles.length; i++) {

            buffer.append(" * " + titles[i]);
        }
        buffer.append(SEPARATOR);
        buffer.append(tr());
        for (int i = 0; i < annonces.size(); i++) {
            buffer.append(SEPARATOR);
            Annonce annonce = annonces.get(i);
            if (titles[0].equals("ID")) {
                buffer.append(annonce.getId() + " * ");
            }
            buffer.append(
                    annonce.getDescription()
                            + " * "
                            + annonce.getPrice()
                            + " * "
                            + annonce.getDomain().getType()
                            + " * "
                            + annonce.getUser().getEmail());
            buffer.append(SEPARATOR);
            buffer.append(tr());
        }
        buffer.append(SEPARATOR);
        return buffer.toString();
    }

    private String separator() {
        return "#############################################################";
    }

    private String tr() {
        return "-------------------------------------------------------------";
    }

    public String menu() {
        StringBuffer buffer = new StringBuffer(separator());
        buffer.append(SEPARATOR);

        buffer.append(
                "# Voulez vous ajouter une annonce ? la ligne de commande est : annonce <price>:<description>:<domain>");
        buffer.append(SEPARATOR);
        buffer.append(
                "# Voulez vous lister les annonces des autre utilisateurs ? la ligne de commande est : annonce <username>");
        buffer.append(SEPARATOR);
        buffer.append("# Voulez vous lister vos annonces ? la ligne de commande est : myannonce");
        buffer.append(SEPARATOR);
        // 		buffer.append("# Voulez vous supprimer une de vos annonces ? la ligne de commande est :
        // delete <ID-annonce>");
        // 		buffer.append(SEPARATOR);

        return buffer.toString();
    }
}
