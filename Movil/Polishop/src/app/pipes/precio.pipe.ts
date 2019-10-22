import { Pipe, PipeTransform } from "@angular/core";

@Pipe({ name: "precio" })
export class PrecioPipe implements PipeTransform {
  transform(precio: string): string {
    var salida = "";
    var ultim = "";
    var contPunt = 0;
    for (let i = precio.length - 1, j = 1; i > 0; i--, j++) {
      ultim = precio[i] + ultim;
      if (j === 3) {
        salida = "." + ultim + salida;
        ultim = "";
        j = 0;
        contPunt++;
      }
    }
    if (salida.length - contPunt < precio.length) {
      salida = precio.substring(0, precio.length - (salida.length - contPunt)) + salida;
    }
    return salida;
  }
}
