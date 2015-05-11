public class Luta implements IEvento
{
	public String descr;
	public Personagem p1,p2;

	public Evento(String descr);

	public void rotina();
	public void setup(Personagem p1, Personagem p2);
}
