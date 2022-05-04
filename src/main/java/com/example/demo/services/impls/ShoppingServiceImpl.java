package com.example.demo.services.impls;

import com.example.demo.domains.Cart;
import com.example.demo.domains.Order;
import com.example.demo.domains.UserModel;
import com.example.demo.dtos.requests.dtos.OrderRequestDto;
import com.example.demo.dtos.requests.dtos.ShoppingProductRequestDto;
import com.example.demo.dtos.requests.dtos.UpdateCartRequestDto;
import com.example.demo.dtos.responses.CartResponseDto;
import com.example.demo.dtos.responses.OrderResponseDto;
import com.example.demo.exceptions.ForbiddenRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.services.ShoppingService;
import com.example.demo.services.UserService;
import com.example.demo.utils.AuthenticationUtil;
import com.example.demo.utils.MessageFormatter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingServiceImpl implements ShoppingService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    private final UserService userService;
    private final AuthenticationUtil authenticationUtil;


    @Override
    public CartResponseDto updateCart(UpdateCartRequestDto dto) {
       return null;
    }

    @Override
    public CartResponseDto getCart(Long cartId) {
        UserModel user = authenticationUtil.getUserClaim();
        Cart cart = cartRepository.findCartByIdAndFetchShoppingProducts(cartId)
                .orElseThrow(() -> new NotFoundException(MessageFormatter.formatUserCartNotFound(cartId)));
        if(!user.getId().equals(cart.getUserModel().getId())){
            throw new ForbiddenRequestException(MessageFormatter.formatUnprivilegedRequest("Get others cart!"));
        }
        return mapper.map(cart, CartResponseDto.class);
    }

    @Override
    public CartResponseDto getCart() {
        UserModel user = authenticationUtil.getUserClaim();
        Cart cart = cartRepository.findCartByUserModelIdAndFetchShoppingProducts(user.getId())
                .orElseThrow(() -> new NotFoundException(MessageFormatter.formatUserCartNotFound(user.getId())));
        return mapper.map(cart, CartResponseDto.class);
    }

    @Override
    public OrderResponseDto getOrder(Long orderId) {
        UserModel user = authenticationUtil.getUserClaim();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(MessageFormatter.formatUserOrdersNotFound(user.getId())));
        if(!user.getId().equals(order.getUserModel().getId())){
            throw new ForbiddenRequestException(MessageFormatter.formatUnprivilegedRequest("Get others order!"));
        }
        return mapper.map(order, OrderResponseDto.class);
    }

    @Override
    public List<OrderResponseDto> getOrders() {
        UserModel user = authenticationUtil.getUserClaim();
        List<Order> orders = orderRepository.findListOrderByUserModelIdAndFetchShoppingProducts(user.getId())
                .orElseThrow(() -> new NotFoundException(MessageFormatter.formatUserOrdersNotFound(user.getId())));
        return orders.stream()
                .map(entity -> mapper.map(entity, OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartResponseDto addProductToCart(ShoppingProductRequestDto dto) {
        return null;
    }

    @Override
    public OrderResponseDto orderProducts(OrderRequestDto dto) {
        return null;
    }
}
