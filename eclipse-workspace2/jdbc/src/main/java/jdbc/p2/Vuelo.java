package jdbc.p2;

public class Vuelo 
{
	
	int idVuelo;
	String origen;
	String abrevSa;
	String fechaSa;
	String destino;
	String abrevDes;
	String fechaLle;
	String nPlazas;
	
	
	public Vuelo(int idVuelo, String origen, String abrevSa, String fechaSa, String destino, String abrevDes,
			String fechaLle, String nPlazas) {
		this.idVuelo = idVuelo;
		this.origen = origen;
		this.abrevSa = abrevSa;
		this.fechaSa = fechaSa;
		this.destino = destino;
		this.abrevDes = abrevDes;
		this.fechaLle = fechaLle;
		this.nPlazas = nPlazas;
	}


	public int getIdVuelo() {
		return idVuelo;
	}


	public void setIdVuelo(int idVuelo) {
		this.idVuelo = idVuelo;
	}


	public String getOrigen() {
		return origen;
	}


	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public String getAbrevSa() {
		return abrevSa;
	}


	public void setAbrevSa(String abrevSa) {
		this.abrevSa = abrevSa;
	}


	public String getFechaSa() {
		return fechaSa;
	}


	public void setFechaSa(String fechaSa) {
		this.fechaSa = fechaSa;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public String getAbrevDes() {
		return abrevDes;
	}


	public void setAbrevDes(String abrevDes) {
		this.abrevDes = abrevDes;
	}


	public String getFechaLle() {
		return fechaLle;
	}


	public void setFechaLle(String fechaLle) {
		this.fechaLle = fechaLle;
	}


	@Override
	public String toString() {
		return "Vuelo [idVuelo=" + idVuelo + ", origen=" + origen + ", abrevSa=" + abrevSa + ", fechaSa=" + fechaSa
				+ ", destino=" + destino + ", abrevDes=" + abrevDes + ", fechaLle=" + fechaLle + "]";
	}


	public String getnPlazas() {
		return nPlazas;
	}


	public void setnPlazas(String nPlazas) {
		this.nPlazas = nPlazas;
	}
	
	
	
		
}
