export const environment = {
	production: false,

	//Productos
	urlGetProductoById: "http://localhost:8080/getProductoById",
	urlGetAllProductos: "http://localhost:8080/getAllProductos",
	urlGetAllProductosByDependencia: "http://localhost:8080/getAllProductosByDependencia",
	urlGetAllCategorias: "http://localhost:8080/getAllCategorias",
	urlUpdateProducto: "http://localhost:8080/updateProducto",
	urldeleteProducto: "http://localhost:8080/deleteProducto",
	urlGetProductosByVendedor: "http://localhost:8080/getProductosVendedor",
	urlAddProducto: "http://localhost:8080/addProducto",
	urlAddImagenProducto: "http://localhost:8080/saveImagenesProducto",
	urlBusquedaProducto: "http://localhost:8080/busquedaProducto",

	//Comentarios
	urlAddComentarioProducto: "http://localhost:8080/addComentario",
	urlGetProductoComentarios: "http://localhost:8080/getComentarioByProducto",

	//Usuarios
	urlLoginComprador: "http://localhost:8080/loginUsuario",
	urlLoginVendedor: "http://localhost:8080/loginVendedor",
	urlLoginAdmin: "http://localhost:8080/loginAdmin",
	urlGetInfoComprador: "http://localhost:8080/getUsuarioByCorreo",
	urlGetInfoCompradorById: "http://localhost:8080/getUsuarioById",
	urlGetInfoVendedor: "http://localhost:8080/getVendedorByCorreo",
	urlGetInfoVendedorById: "http://localhost:8080/getVendedorById",
	urlSaveCarritoConCompra: "http://localhost:8080/saveCarritoConCompra",
	urlAddVendedor: "http://localhost:8080/addVendedor",
	urlGetAllVendedor: "http://localhost:8080/getAllVendedores",

	//compra
	urlGetProductosCarrito: "http://localhost:8080/getProductosCarrito",
	urlEliminarProductoCarrito: "http://localhost:8080/eliminarProductoCarrito",
	urlGetCompraByVendedor: "http://localhost:8080/getByComprador",
	urlGetHistorial: "http://localhost:8080/getHistorial",
	urlRealizarCompra: "http://localhost:8080/realizarCompra"
};
