export const environment = {
  production: false,

  /* Productos */
  urlGetProductos: "http://localhost:8080/getAllProductos",
  urlGetAllProductosByDependencia: "http://localhost:8080/getAllProductosByDependencia",
  urlGetProductoById: "http://localhost:8080/getProductoById",
  urlGetAllCategorias: 'http://localhost:8080/getAllCategorias',
  urlBusquedaProducto: 'http://localhost:8080/busquedaProducto',
  /* Comentarios */
  urlGetProductoComentarios: "http://localhost:8080/getComentarioByProducto",
  urlAddComentarioProducto: 'http://localhost:8080/addComentario',
  /* Comprador */
  urlLoginComprador: "http://localhost:8080/loginUsuario",
  urlGetInfoComprador: "http://localhost:8080/getUsuarioByCorreo",
  urlGetInfoCompradorById: "http://localhost:8080/getUsuarioById",
  urlSaveCarritoConCompra: 'http://localhost:8080/saveCarritoConCompra',
  /* Compra */
  urlGetProductosCarrito: 'http://localhost:8080/getProductosCarrito',
  urlEliminarProductoCarrito: 'http://localhost:8080/eliminarProductoCarrito',
};
