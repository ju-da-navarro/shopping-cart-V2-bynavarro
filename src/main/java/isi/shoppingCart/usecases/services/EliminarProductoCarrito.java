package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;
import isi.shoppingCart.usecases.dto.OperationResult;

public class EliminarProductoCarrito {

    private ProductRepository productRepository;
    private CartRepository cartRepository;

    public EliminarProductoCarrito(ProductRepository productRepository,
                                           CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public OperationResult execute(CartItem cartItem) {

        if (cartItem == null) {
            return OperationResult.fail("Producto no encontrado.");
        }

        Cart cart = cartRepository.getCart();
        int quantityInCart = cartItem.getQuantity();

        //Restamos cantidad presente en el carro
        for( int i=0; i<quantityInCart; i++ ){
            cart.deleteProduct(cartItem.getProduct());
        }
        //Aumentamos cantidad presnete en el carro al catalogo
        Product product= productRepository.findById(cartItem.getProduct().getId());
        product.increaseAvailableQuantity(quantityInCart);


        cartRepository.save(cart);
        return OperationResult.ok("Producto Eliminado del  carrito.");
    }
}
