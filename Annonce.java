
public class Annonce {

	private int id;
	private Domain domain;
	private User user;
	private double price;
	private String description;

	public Annonce() {
	}

	public Annonce(Domain domain, User user, double price, String description) {
		this.domain = domain;
		this.user = user;
		this.price = price;
		this.description = description;
	}

	public Annonce(int id, Domain domain, User user, double price, String description) {
		this.id = id;
		this.domain = domain;
		this.user = user;
		this.price = price;
		this.description = description;
	}

	public Annonce(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "Annonce [id=" + id + ", domain=" + domain + ", user=" + user + ", price=" + price + ", description="
				+ description + "]";
	}

}
