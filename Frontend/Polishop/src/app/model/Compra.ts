export class Compra {
  public idComprador: number;
  public pais: string;
  public departamento: string;
  public ciudad: string;
  public tipoDocumento: string;
  public numeroDocumento: string;
  public nombreDestinatario: string;
  public direccionEnvio: string;
  public observaciones: string;
  public telefonoUno: string;
  public telefonoDos: string;

  constructor() {
    this.observaciones = "";
    this.telefonoDos = "";
  }
}
