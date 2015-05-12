package itens;

public class Equipments implements IEquipments {
	private String name;
	private String type;
	private String description;
	
	private int power;
	
	private boolean state;
	
    /*construtor dos equipamenos usando os metodos Set, o atributo state sempre eh inicialiado com false porque ele nunca comeca ja equipado em um personagem*/
	public Equipments(String nome, String tipo, String descricao, int poder) {		
		setName(nome);
		setType(tipo);
		setDescription(descricao);
		setPower(poder);
		
		this.state = false;
	}
	
	private void setName(String nome) {
		this.name = nome;
	}
	
	private void setType(String tipo) {
		if (!tipo.equalsIgnoreCase("Arma")&&!tipo.equalsIgnoreCase("Armadura")) {
			System.out.println("Erro: Tipo do equipamento nao é nem uma arma nem uma armadura!");
		
			return;
		}
			
		this.type = tipo;
	}
	
	private void setDescription(String descricao) {
		this.description = descricao;
	}
	
	private void setPower(int poder) {
		this.power = poder;
	}
	
	@Override
	public String GetName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int GetPower() {
		// TODO Auto-generated method stub
		return this.power;
	}

	@Override
	public String GetType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	/*Este metodo retorna true se o equipamento esta equipado ou false caso contrario*/
	@Override
	public boolean GetStatus() {
		// TODO Auto-generated method stub
		return this.state;
	}

	/*metodo que imprimira todos os dados do item na seguinte forma:
	 * "Name"
	 * Tipo: "Typo"
	 * "Description"
	 * (Se for uma arma)
	 * Ataque: +/- "Power"
	 * (Se for uma armadura)
	 * Defesa: +/- "Power"
	 * 
	 * Estado: "state"(equipado ou desequipado)*/
	@Override
	public void Data() {
		// TODO Auto-generated method stub
		System.out.println(name);
		System.out.println("Tipo: " + type);
		System.out.println(description);
		
		if(!this.type.equalsIgnoreCase("Arma")){
			System.out.println("Ataque: " + power);
		}
		
		else
			System.out.println("Defesa: " + power + "\n");
		
		if (state)
			System.out.println("Equipado");
		else
			System.out.println("Não equipado");						
		}

	}


