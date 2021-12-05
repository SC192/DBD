// Aqui se insertan las clases que se tienen en el back
export class Moneda {
  constructor(public codigoMoneda: string,
              public nombre: string) {}
}

export class Presupuesto {
  constructor(public codigoPresupuesto: string,
              public costoEstimado: number,
              public moneda: Moneda) {}
}

export class Proyecto {
  constructor(public codigoProyecto: string,
              public nombre: string,
              public estado: string,
              public fechaFinReal: string,
              public fechaFinEstimada: string,
              public fechaInicioReal: string,
              public fechaInicioEstimada: string,
              public Presupuesto: Presupuesto) {}
}
