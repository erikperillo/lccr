package itens;

public interface IConsumables {
	/*metodo para pegar o valor que o item recuperda a vida*/
	public int getHP();
	
	/*metodo para pegar o valor que o item aumenta o ataque*/
	public int getAttack();
	
	/*metodo para pegar o valor que o item aumenta a defesa*/
	public int getDefense();
	
	/*metodo para pegar a quantidade de usos restantes do item*/
	public int getQuantity();
	
	/*metodo para pegar a duracao dos efeitos do item consumivel (se eles modificam o ataque e/ou defesa)*/
	public int getDuration();
	
	/*Metodo para guardar uma quantidade do item que já existe no inventorio do personagem*/	
	public void StockIten (int quantidade);
			
	/*metodo que imprimira todos os dados do item na seguinte forma:
	 * "Nome"
	 * Tipo: "Tipo"
	 * "Descricao"
	 * HP: +/- recuperacao da vida
	 * Ataque: +/- aumento do poder de ataque
	 * Defesa: +/- aumento do poder de defesa
	 * 
	 * Quantidade de usos*/
	public void Data();

}
