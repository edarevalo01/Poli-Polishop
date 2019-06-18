import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-busqueda',
  templateUrl: './busqueda.component.html',
  styleUrls: ['./busqueda.component.css']
})
export class BusquedaComponent implements OnInit {

  textRoute: string = 'texto temporal :v';

  constructor(private activeRoute: ActivatedRoute, private router: Router) {
    activeRoute.queryParams.subscribe(
      retorno => {
        this.textRoute = retorno['object']
      }
    );
  }

  ngOnInit() {
  }

}
