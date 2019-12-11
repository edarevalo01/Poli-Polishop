export const environment = {
	production: false,

	//Productos
	urlGetProductoById: "http://localhost:8080/PolishopBack/getProductoById",
	urlGetAllProductos: "http://localhost:8080/PolishopBack/getAllProductos",
	urlGetAllProductosByDependencia: "http://localhost:8080/PolishopBack/getAllProductosByDependencia",
	urlGetAllCategorias: "http://localhost:8080/PolishopBack/getAllCategorias",
	urlUpdateProducto: "http://localhost:8080/PolishopBack/updateProducto",
	urldeleteProducto: "http://localhost:8080/PolishopBack/deleteProducto",
	urlGetProductosByVendedor: "http://localhost:8080/PolishopBack/getProductosVendedor",
	urlAddProducto: "http://localhost:8080/PolishopBack/addProducto",
	urlAddImagenProducto: "http://localhost:8080/PolishopBack/saveImagenesProducto",
	urlBusquedaProducto: "http://localhost:8080/PolishopBack/busquedaProducto",

	//Comentarios
	urlAddComentarioProducto: "http://localhost:8080/PolishopBack/addComentario",
	urlGetProductoComentarios: "http://localhost:8080/PolishopBack/getComentarioByProducto",

	//Usuarios
	urlLoginComprador: "http://localhost:8080/PolishopBack/loginUsuario",
	urlLoginVendedor: "http://localhost:8080/PolishopBack/loginVendedor",
	urlLoginAdmin: "http://localhost:8080/PolishopBack/loginAdmin",
	urlGetInfoComprador: "http://localhost:8080/PolishopBack/getUsuarioByCorreo",
	urlGetInfoCompradorById: "http://localhost:8080/PolishopBack/getUsuarioById",
	urlGetInfoVendedor: "http://localhost:8080/PolishopBack/getVendedorByCorreo",
	urlGetInfoVendedorById: "http://localhost:8080/PolishopBack/getVendedorById",
	urlSaveCarritoConCompra: "http://localhost:8080/PolishopBack/saveCarritoConCompra",
	urlAddVendedor: "http://localhost:8080/PolishopBack/addVendedor",
	urlGetAllVendedor: "http://localhost:8080/PolishopBack/getAllVendedores",
	urlDeleteVendedor: "http://localhost:8080/PolishopBack/deleteVendedor",

	//compra
	urlGetProductosCarrito: "http://localhost:8080/PolishopBack/getProductosCarrito",
	urlEliminarProductoCarrito: "http://localhost:8080/PolishopBack/eliminarProductoCarrito",
	urlGetCompraByVendedor: "http://localhost:8080/PolishopBack/getByComprador",
	urlGetHistorial: "http://localhost:8080/PolishopBack/getHistorial",
	urlRealizarCompra: "http://localhost:8080/PolishopBack/realizarCompra"
};
