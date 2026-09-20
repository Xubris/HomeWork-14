package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {

        Product product1 = new Product("Хлеб", 50);
        Product product2 = new Product("Молоко", 80);
        Product product3 = new Product("Сыр", 300);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);

        System.out.println("Общая стоимость: " + basket.getTotalPrice());
    }
}