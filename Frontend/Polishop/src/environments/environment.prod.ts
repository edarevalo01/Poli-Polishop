export const environment = {
  production: false,

  //Productos
  urlGetProductoById: "http://192.168.54.6/Polishop/getProductoById",
  urlGetAllProductos: "http://192.168.54.6/Polishop/getAllProductos",
  urlGetAllProductosByDependencia: "http://192.168.54.6/Polishop/getAllProductosByDependencia",
  urlGetAllCategorias: "http://192.168.54.6/Polishop/getAllCategorias",
  urlUpdateProducto: "http://192.168.54.6/Polishop/updateProducto",
  urldeleteProducto: "http://192.168.54.6/Polishop/deleteProducto",
  urlGetProductosByVendedor: "http://192.168.54.6/Polishop/getProductosVendedor",
  urlAddProducto: "http://192.168.54.6/Polishop/addProducto",
  urlAddImagenProducto: "http://192.168.54.6/Polishop/saveImagenesProducto",
  urlBusquedaProducto: "http://192.168.54.6/Polishop/busquedaProducto",

  //Comentarios
  urlAddComentarioProducto: "http://192.168.54.6/Polishop/addComentario",
  urlGetProductoComentarios: "http://192.168.54.6/Polishop/getComentarioByProducto",

  //Usuarios
  urlLoginComprador: "http://192.168.54.6/Polishop/loginUsuario",
  urlLoginVendedor: "http://192.168.54.6/Polishop/loginVendedor",
  urlLoginAdmin: "http://192.168.54.6/Polishop/loginAdmin",
  urlGetInfoComprador: "http://192.168.54.6/Polishop/getUsuarioByCorreo",
  urlGetInfoCompradorById: "http://192.168.54.6/Polishop/getUsuarioById",
  urlGetInfoVendedor: "http://192.168.54.6/Polishop/getVendedorByCorreo",
  urlGetInfoVendedorById: "http://192.168.54.6/Polishop/getVendedorById",
  urlSaveCarritoConCompra: "http://192.168.54.6/Polishop/saveCarritoConCompra",
  urlAddVendedor: "http://192.168.54.6/Polishop/addVendedor",

  //compra
  urlGetProductosCarrito: "http://192.168.54.6/Polishop/getProductosCarrito",
  urlEliminarProductoCarrito: "http://192.168.54.6/Polishop/eliminarProductoCarrito",
  urlGetCompraByVendedor: "http://192.168.54.6/Polishop/getByComprador",
  urlGetHistorial: "http://192.168.54.6/Polishop/getHistorial",
  urlRealizarCompra: "http://192.168.54.6/Polishop/realizarCompra"
};
