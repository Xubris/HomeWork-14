package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        Product product1 = new Product("Хлеб", 50);
        Product product2 = new Product("Молоко", 80);
        Product product3 = new Product("Сыр", 300);
        Product product4 = new Product("Мясо", 400);
        Product product5 = new Product("Рыба", 250);
        Product product6 = new Product("Яйца", 100);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        basket.addProduct(product6);

        System.out.println("=== Содержимое корзины ===");
        basket.printBasket();

        System.out.println("Стоимость корзины: " + basket.getTotalPrice());
        System.out.println("Поиск 'Сыр': " + basket.containsProductByName("Сыр"));
        System.out.println("Поиск 'Яйца': " + basket.containsProductByName("Яйца"));

        basket.clearBasket();

        System.out.println("=== После очистки ===");
        basket.printBasket();

        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice());
        System.out.println("Поиск 'Хлеб' в пустой корзине: " + basket.containsProductByName("Хлеб"));
    }
}