package com.example.demo.services.impls;

import com.example.demo.common.MessageFormatter;
import com.example.demo.domains.Product;
import com.example.demo.domains.UserModel;
import com.example.demo.dtos.requests.AddCategoryRequestDto;
import com.example.demo.dtos.requests.AddProductRequestDto;
import com.example.demo.dtos.requests.UpdateCategoryRequestDto;
import com.example.demo.dtos.requests.UpdateProductRequestDto;
import com.example.demo.dtos.responses.CategoryResponseDto;
import com.example.demo.dtos.responses.ProductResponseDto;
import com.example.demo.dtos.responses.UserResponseDto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    private Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        MessageFormatter.formatProductNotFound(id)));
    }

    @Override
    public ProductResponseDto getProduct(Long pdId) {
        Product product = getProductById(pdId);
        return mapper.map(product, ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> listProducts() {
        return null;
    }

    @Override
    public ProductResponseDto addProduct(AddProductRequestDto dto) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Long prdId) {
        getProductById(prdId);
        productRepository.deleteById(prdId);
        return true;
    }

    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto dto) {
        return null;
    }

}
