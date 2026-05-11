package isi.shoppingCart.usecases.services;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.entities.Cart;


public class VaciarCarritoUseCase {
    private CartRepository cartRepository;

    public VaciarCarritoUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public OperationResult execute() {
        Cart cart = cartRepository.getCart();
        cart.ClearAll();
        return OperationResult.ok("Carrito Borrado");
    }
}
