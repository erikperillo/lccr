package itens;

/*Interface para a classe Itens*/
public interface IEquipments {			
	/*Retorna o nome do equipamento*/
	public String GetName();
	
	/*Metodo get para retornar o poder do equipamento*/
	public int GetPower();
	
	/*Metodo get para retornar o tipo (arma ou armadura) do equipamento*/
	public String GetType();
	
	/*Metodo get para retornar o estado (equipado ou não equipado) do equipamento*/
	public boolean GetStatus();
	
	/*metodo que imprimira todos os dados do item na seguinte forma:
	 * "Nome"
	 * Tipo: "Tipo"
	 * "Descricao"
	 * (Se for uma arma)
	 * Ataque +/- Poder
	 * (Se for uma armadura)
	 * Defesa +/- Poder
	 * 
	 * Estado: (equipado ou desequipado)*/
	
	public void Data();
}







