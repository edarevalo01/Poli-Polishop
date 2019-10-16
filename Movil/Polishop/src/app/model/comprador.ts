export class Comprador {
  public id: number;
  public nombres: string;
  public apellidos: string;
  public username: string;
  public correo: string;
  public contrasena: string;
  public pais: string;
  public ciudad: string;
  public urlFoto: string;
  public puntuacion: number;

  constructor() {
    this.id = 0;
    this.nombres = "";
    this.apellidos = "";
    this.username = "";
    this.correo = "";
    this.contrasena = "";
    this.pais = "";
    this.ciudad = "";
    this.urlFoto = "";
    this.puntuacion = 0;
  }
}
