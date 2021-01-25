package Entites;

public class CommandLine {

	private int idCdeLine;
	private int idProduct;
	private int quantite;
	private int idCde;
	
	public CommandLine(int idCdeLine, int idProduct, int quantite, int idCde) {
		super();
		this.idCdeLine = idCdeLine;
		this.idProduct = idProduct;
		this.quantite = quantite;
		this.idCde = idCde;
	}

	public CommandLine(int idProduct, int quantite) {
		super();
		this.idProduct = idProduct;
		this.quantite = quantite;
	}

	public int getIdCdeLine() {
		return idCdeLine;
	}

	public void setIdCdeLine(int idCdeLine) {
		this.idCdeLine = idCdeLine;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getIdCde() {
		return idCde;
	}

	public void setIdCde(int idCde) {
		this.idCde = idCde;
	}

	@Override
	public String toString() {
		return "Produit commandé : " + idProduct + "\n qte : " + quantite;
	}
	
	
}
