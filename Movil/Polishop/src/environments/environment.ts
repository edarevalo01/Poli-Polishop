export const environment = {
  production: true,

  /* Productos */
  urlGetProductos: "http://192.168.54.6/Polishop/getAllProductos",
  urlGetAllProductosByDependencia: "http://192.168.54.6/Polishop/getAllProductosByDependencia",
  urlGetProductoById: "http://192.168.54.6/Polishop/getProductoById",
  urlGetAllCategorias: "http://192.168.54.6/Polishop/getAllCategorias",
  urlBusquedaProducto: "http://192.168.54.6/Polishop/busquedaProducto",
  /* Comentarios */
  urlGetProductoComentarios: "http://192.168.54.6/Polishop/getComentarioByProducto",
  urlAddComentarioProducto: "http://192.168.54.6/Polishop/addComentario",
  /* Comprador */
  urlLoginComprador: "http://192.168.54.6/Polishop/loginUsuario",
  urlGetInfoComprador: "http://192.168.54.6/Polishop/getUsuarioByCorreo",
  urlGetInfoCompradorById: "http://192.168.54.6/Polishop/getUsuarioById",
  urlSaveCarritoConCompra: "http://192.168.54.6/Polishop/saveCarritoConCompra",
  /* Compra */
  urlGetProductosCarrito: "http://192.168.54.6/Polishop/getProductosCarrito",
  urlEliminarProductoCarrito: "http://192.168.54.6/Polishop/eliminarProductoCarrito",
  urlRealizarCompra: "http://192.168.54.6/Polishop/realizarCompra"
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
