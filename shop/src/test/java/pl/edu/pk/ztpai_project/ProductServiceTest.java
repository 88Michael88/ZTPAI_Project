package pl.edu.pk.ztpai_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pk.ztpai_project.model.Product;
import pl.edu.pk.ztpai_project.repository.ProductRepository;
import pl.edu.pk.ztpai_project.service.ProductService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldReturnAllProducts() {
        List<Product> products = List.of(
                new Product(1L, "A", 10.0),
                new Product(2L, "B", 20.0)
        );

        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getName());

        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnProductById() {
        Product product = new Product(1L, "A", 10.0);

        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = service.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("A", result.get().getName());

        verify(repository).findById(1L);
    }

    @Test
    void shouldReturnEmptyWhenProductNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<Product> result = service.getById(1L);

        assertTrue(result.isEmpty());

        verify(repository).findById(1L);
    }

    @Test
    void shouldCreateProduct() {
        Product product = new Product(null, "A", 10.0);
        Product saved = new Product(1L, "A", 10.0);

        when(repository.save(product)).thenReturn(saved);

        Product result = service.createProduct(product);

        assertNotNull(result.getId());
        assertEquals("A", result.getName());

        verify(repository).save(product);
    }

    @Test
    void shouldDeleteProductById() {
        doNothing().when(repository).deleteById(1L);

        service.deleteProductById(1L);

        verify(repository).deleteById(1L);
    }
}