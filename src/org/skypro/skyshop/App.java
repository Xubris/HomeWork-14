package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        SimpleProduct product1 = new SimpleProduct("Хлеб", 50);
        SimpleProduct product2 = new SimpleProduct("Молоко", 80);
        DiscountedProduct product3 = new DiscountedProduct("Сыр", 300, 20);
        FixPriceProduct product4 = new FixPriceProduct("Мясо");
        DiscountedProduct product5 = new DiscountedProduct("Рыба", 250, 10);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        basket.printBasket();

        System.out.println("Стоимость корзины: " + basket.getTotalPrice());
        System.out.println("Поиск 'Сыр': " + basket.containsProductByName("Сыр"));
        System.out.println("Поиск 'Яйца': " + basket.containsProductByName("Яйца"));

        basket.clearBasket();

        basket.printBasket();

        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice());
        System.out.println("Поиск 'Хлеб' в пустой корзине: " + basket.containsProductByName("Хлеб"));
    }
}