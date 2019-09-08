export const environment = {
  production: false,
  //Productos
  urlGetProductoById: 'http://3.15.84.246:8080/Polishop/getProductoById',
  urlGetAllProductos: 'http://3.15.84.246:8080/Polishop/getAllProductos',
  urlGetAllProductosByDependencia: 'http://3.15.84.246:8080/Polishop/getAllProductosByDependencia',
  urlGetAllCategorias: 'http://3.15.84.246:8080/Polishop/getAllCategorias',
  urlUpdateProducto: 'http://3.15.84.246:8080/Polishop/updateProducto',
  urldeleteProducto: 'http://3.15.84.246:8080/Polishop/deleteProducto',
  urlGetProductosByVendedor: 'http://3.15.84.246:8080/Polishop/getProductosVendedor',
  urlAddProducto: 'http://3.15.84.246:8080/Polishop/addProducto',
  urlAddImagenProducto: 'http://3.15.84.246:8080/Polishop/saveImagenesProducto',
  urlBusquedaProducto: 'http://3.15.84.246:8080/Polishop/busquedaProducto',
  
  //Comentarios
  urlAddComentarioProducto: 'http://3.15.84.246:8080/Polishop/addComentario',
  urlGetProductoComentarios: 'http://3.15.84.246:8080/Polishop/getComentarioByProducto',

  //Usuarios
  urlLoginComprador: 'http://3.15.84.246:8080/Polishop/loginUsuario',
  urlLoginVendedor: 'http://3.15.84.246:8080/Polishop/loginVendedor',
  urlLoginAdmin: 'http://3.15.84.246:8080/Polishop/loginAdmin',
  urlGetInfoComprador: 'http://3.15.84.246:8080/Polishop/getUsuarioByCorreo',
  urlGetInfoCompradorById: 'http://3.15.84.246:8080/Polishop/getUsuarioById',
  urlGetInfoVendedor: 'http://3.15.84.246:8080/Polishop/getVendedorByCorreo',
  urlGetInfoVendedorById: 'http://3.15.84.246:8080/Polishop/getVendedorById',
  urlSaveCarritoConCompra: 'http://3.15.84.246:8080/Polishop/saveCarritoConCompra',
  urlAddVendedor: 'http://3.15.84.246:8080/Polishop/addVendedor',

  //compra
  urlGetProductosCarrito: 'http://3.15.84.246:8080/Polishop/getProductosCarrito',
  urlEliminarProductoCarrito: 'http://3.15.84.246:8080/Polishop/eliminarProductoCarrito',
  urlGetCompraByVendedor: 'http://3.15.84.246:8080/Polishop/getByComprador',
  urlGetHistorial: 'http://3.15.84.246:8080/Polishop/getHistorial',
};
