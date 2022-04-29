package com.example.demo.services.impls;

import com.example.demo.domains.Category;
import com.example.demo.filters.search.SearchCriterion;
import com.example.demo.filters.search.SearchSpecificationFilter;
import com.example.demo.utils.MessageFormatter;
import com.example.demo.domains.Product;
import com.example.demo.domains.Rating;
import com.example.demo.domains.UserModel;
import com.example.demo.dtos.requests.*;
import com.example.demo.dtos.responses.ProductResponseDto;
import com.example.demo.dtos.responses.RatingResponseDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ConflictRequestException;
import com.example.demo.exceptions.ForbiddenRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.RatingRepository;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import com.example.demo.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;
    private final ModelMapper mapper;
    private final CategoryService categoryService;
    private final AuthenticationUtil authenticationUtil;


    private final Map<String, Object> acceptedFilterField = Map.ofEntries(
            Map.entry("name", String.class),
            Map.entry("price", Double.class),
            Map.entry("brand", String.class),
            Map.entry("color", String.class),
            Map.entry("category", Category.class),
            Map.entry("updatedDate", Date.class));

    private Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        MessageFormatter.formatProductNotFound(id)));
    }

    private Rating getRating(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        MessageFormatter.formatRatingNotFound(id)));
    }

    private Boolean isRatingExist(Long id) {
        return ratingRepository.existsById(id);
    }

    private boolean isProductExist(Long productId) {
        return productRepository.existsById(productId);
    }

    private boolean isRated(Long userId, Long productId) {
        return ratingRepository.existsRatingByUserModelIdAndProductId(userId, productId);
    }

    private Boolean isValidCriteria(List<SearchCriterion> criteria) {
        for (var element : criteria) {
            var field = acceptedFilterField.get(element.getField());
            if(field == null || !element.getValue().getClass().equals(field.getClass())){
                throw new BadRequestException(MessageFormatter.formatInvalidRequestInput(element.getField()));
            }
        }
        return true;
    }

    @Override
    public ProductResponseDto getProduct(Long pdId) {
        Product product = getProductById(pdId);
        return mapper.map(product, ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> listProducts(ListProductRequestDto dto) {
        isValidCriteria(dto.getCriteria());

        SearchSpecificationFilter<Product> filter = new SearchSpecificationFilter<>(dto.getCriteria());
        Specification<Product> specification = filter.build();
        List<Product> products = productRepository.findAll(specification);

        return products.stream()
                .map(entity -> mapper.map(entity, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto addProduct(AddProductRequestDto dto) {
        boolean isCatExist = categoryService.getCategory(dto.getCategoryId()) != null;
        if (isCatExist) {
            Product product = mapper.map(dto, Product.class);
            product.setCreatedDate();
            productRepository.save(product);
            return mapper.map(product, ProductResponseDto.class);
        }
        throw new NotFoundException(MessageFormatter.formatCategoryNotFound(dto.getCategoryId()));
    }

    @Override
    public Boolean deleteProduct(Long prdId) {
        getProductById(prdId);
        productRepository.deleteById(prdId);
        return true;
    }

    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto dto) {
        getProductById(dto.getId());
        boolean isCatExist = categoryService.getCategory(dto.getCategoryId()) != null;
        if (isCatExist) {
            Product update = mapper.map(dto, Product.class);
            update.setUpdatedDate();
            productRepository.save(update);
            return mapper.map(update, ProductResponseDto.class);
        }
        throw new NotFoundException(MessageFormatter.formatCategoryNotFound(dto.getCategoryId()));
    }

    @Override
    public RatingResponseDto addRatingProduct(AddRatingRequestDto dto) {
        if (0 <= dto.getRating() && dto.getRating() <= 5) {
            throw new BadRequestException(MessageFormatter.formatInvalidRequestInput("rating"));
        }
        UserModel user = authenticationUtil.getUserClaim();
        Product product = getProductById(dto.getProductId());
        if (isRated(user.getId(), dto.getProductId())) {
            throw new ConflictRequestException(MessageFormatter.formatUserAlreadyRatingProduct(user.getId(), dto.getProductId()));
        }

        Rating rating = mapper.map(dto, Rating.class);
        rating.setUserModel(user);
        rating.setProduct(product);
        rating.setCreatedDate();
        ratingRepository.save(rating);
        return mapper.map(rating, RatingResponseDto.class);
    }

    @Override
    public RatingResponseDto updateRatingProduct(UpdateRatingRequestDto dto) {
        if (0 <= dto.getRating() && dto.getRating() <= 5) {
            throw new BadRequestException(MessageFormatter.formatInvalidRequestInput("rating"));
        }
        Rating rating = getRating(dto.getId());
        UserModel user = authenticationUtil.getUserClaim();
        if (!user.getId().equals(rating.getUserModel().getId())) {
            throw new ForbiddenRequestException(MessageFormatter.formatUnprivilegedRequest("update rating"));
        }
        Rating update = mapper.map(dto, Rating.class);
        update.setUpdatedDate();
        ratingRepository.save(update);
        return mapper.map(update, RatingResponseDto.class);
    }

    @Override
    public Boolean deleteRatingProduct(Long id) {
        Rating rating = getRating(id);
        UserModel user = authenticationUtil.getUserClaim();
        if (!user.getId().equals(rating.getUserModel().getId())) {
            throw new ForbiddenRequestException(MessageFormatter.formatUnprivilegedRequest("delete rating"));
        }
        ratingRepository.delete(rating);
        return true;
    }

    @Override
    public List<RatingResponseDto> listRatingByProductId(Long productId) {
        List<Rating> ratings = ratingRepository.findAllByProductId(productId)
                .orElseThrow(() -> new NotFoundException(MessageFormatter.formatProductNotFound(productId)));
        return ratings.stream().
                map(entity -> mapper.map(entity, RatingResponseDto.class))
                .collect(Collectors.toList());
    }

}
