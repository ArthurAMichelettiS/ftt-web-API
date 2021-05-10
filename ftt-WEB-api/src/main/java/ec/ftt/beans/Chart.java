package ec.ftt.beans;

import java.util.Objects;

//JavaBean >> Serializable, Geters Seters, default contructor, [toString, equals, hash]
//POJO - Plain Old Java Objects

//https://www.devmedia.com.br/use-a-serializacao-em-java-com-seguranca/29012
//https://sites.google.com/site/sureshdevang/java-bean-v-s-pojo
//https://pt.wikipedia.org/wiki/Plain_Old_Java_Objects
//https://pt.wikipedia.org/wiki/JavaBeans


public class Chart {

	private long id;
	private long qtdHappy,
	               qtdSad,
	               qtdConfused;
	
	public Chart() {
		
	}
	public Chart(String id, String qtdHappy, String qtdSad, String qtdConfused) {
		super();
		setId(id);
		setqtdHappy(qtdHappy);
		setqtdSad(qtdSad);
		setqtdConfused(qtdConfused);
	}
	
	public Chart(long id, long qtdHappy, long qtdSad, long qtdConfused) {
		super();
		setId(id);
		setqtdHappy(qtdHappy);
		setqtdSad(qtdSad);
		setqtdConfused(qtdConfused);
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param id the id to set
	 */
	public void setqtdHappy(String id) {
		
		if (id.length()==0)
			setqtdHappy(0);
		else
			setqtdHappy(Long.valueOf(id));
	}
	public void setqtdSad(String id) {
		
		if (id.length()==0)
			setqtdSad(0);
		else
			setqtdSad(Long.valueOf(id));
	}
public void setqtdConfused(String id) {
		
		if (id.length()==0)
			setqtdConfused(0);
		else
			setqtdConfused(Long.valueOf(id));
	}
public void setId(String id) {
	
	if (id.length()==0)
		setId(0);
	else
		setId(Long.valueOf(id));
}
	/**
	 * @return the qtdHappy
	 */
	public long getqtdHappy() {
		return qtdHappy;
	}
	/**
	 * @param qtdHappy the qtdHappy to set
	 */
	public void setqtdHappy(long qtdHappy) {
		this.qtdHappy = qtdHappy;
	}
	/**
	 * @return the qtdSad
	 */
	public long getqtdSad() {
		return qtdSad;
	}
	/**
	 * @param qtdSad the qtdSad to set
	 */
	public void setqtdSad(long qtdSad) {
		this.qtdSad = qtdSad;
	}
	/**
	 * @return the qtdConfused
	 */
	public long getqtdConfused() {
		return qtdConfused;
	}
	/**
	 * @param qtdConfused the qtdConfused to set
	 */
	public void setqtdConfused(long qtdConfused) {
		this.qtdConfused = qtdConfused;
	}

	@Override
	public String toString() {
		return "Chart [id=" + id + ", qtdHappy=" + qtdHappy + ", qtdSad=" + qtdSad + ", qtdConfused=" + qtdConfused + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(qtdConfused, qtdSad, id, qtdHappy);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		Chart other = (Chart) obj;
		return Objects.equals(qtdConfused, other.qtdConfused) && Objects.equals(qtdSad, other.qtdSad) && id == other.id
				&& Objects.equals(qtdHappy, other.qtdHappy);
	}
	
	
	
	
}
